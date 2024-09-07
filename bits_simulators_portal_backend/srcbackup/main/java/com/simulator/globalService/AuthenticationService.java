package com.simulator.globalService;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.simulator.authentification.AuthenticationRequest;
import com.simulator.authentification.AuthenticationResponse;
import com.simulator.authentification.RegisterRequest;
import com.simulator.config.GlobalVars;
import com.simulator.config.JwtService;
import com.simulator.entities.Token;
import com.simulator.entities.TokenType;
import com.simulator.entities.UserManagement;
import com.simulator.globalController.AuthenticationController;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.repository.TokenRepository;
import com.simulator.repository.UserManagementRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.Date;
import java.util.Optional;


@Data
@Service
public class AuthenticationService {
  private final UserManagementRepository repository;
  private final TokenRepository tokenRepository;
  public static PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;
  @Autowired
  UserManagementService userManagementService;

  private final TokenError tokenError = new TokenError();
  private static final Logger logger = LogManager.getLogger(AuthenticationController.class);

  @Autowired
  public AuthenticationService(UserManagementRepository repository, TokenRepository tokenRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
    this.repository = repository;
    this.tokenRepository = tokenRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwtService = jwtService;
    this.authenticationManager = authenticationManager;
  }


  public static String generatePassword(int length) {
    String lowercase = "abcdefghijklmnopqrstuvwxyz";
    String uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String digits = "0123456789";
    String symbols = "!@#$%^&*()_-+=<>?";

    String allCharacters = lowercase + uppercase + digits + symbols;

    SecureRandom random = new SecureRandom();
    StringBuilder password = new StringBuilder();

    for (int i = 0; i < length; i++) {
      int randomIndex = random.nextInt(allCharacters.length());
      char randomChar = allCharacters.charAt(randomIndex);
      password.append(randomChar);
    }

    return password.toString();
  }

  public ResponseApiJson<AuthenticationResponse> register(RegisterRequest request) {
    String idRequest = "R_" + new Date().getTime() + (Math.random() * 9999);
    request.setPassword(generatePassword(8));
    logger.info("Password is: "+request.getPassword());
    try {
      var user = UserManagement.builder()
              .userCode(request.getUserCode())
              .email(request.getEmail())
              .userBankCode(request.getUserBankCode())
              .password(passwordEncoder.encode(request.getPassword()))
              .userType(request.getUserType())
              .numberOfTries(0)
              .numberOfTriesAllowed(3)
              .ipAddress(request.getIpAddress())
              .connected("N")
              .firstConnection('Y')
              .nbrSessionAllowed(99)
              .nbrSessionConnected(0)
              .lengthPassword(request.getPassword().length())
              .complexityFlag('N')
              .expirationPassword(90)
              .dateStartPass(new Date())
              .dateEndPass(userManagementService.getDateAfter(90))
              .blockAccess('N')
              .languageCode(request.getLanguageCode())
              .mobileNumber(request.getMobileNumber())
              .status("P")
              .expiredPassword("N")
              .build();
      Optional<UserManagement> management = repository.findById(request.getUserCode());
      if (management.isEmpty()) {
        repository.save(user);
        return new ResponseApiJson<>(
                idRequest,
                GlobalVars.SUCCESS,
                "Account created successfully !"
        );
      } else {
        return new ResponseApiJson<>(
                idRequest,
                GlobalVars.ALREADYEXIST,
                "Already exist!");
      }
    }catch(Exception e){
      logger.info(e.getMessage());
      return new ResponseApiJson<>(
              idRequest,
              "001",
              "Account does not created !");
    }


  }

  public ResponseApiJson<?> authenticate(AuthenticationRequest request) {
    String idRequest = "AU_" + new Date().getTime() + (Math.random() * 9999);
    Optional<UserManagement> user = repository.findByUserCodeAndStatus(request.getUserCode(),"A");
    if (user.isEmpty()) {
      return new ResponseApiJson<>(
              idRequest,
              GlobalVars.NOTEXIST,
              "User does not exist or still not approved"
      );
    } else {
      if(user.get().getBlockAccess()=='Y'){
        return new ResponseApiJson<>(
                idRequest,
                "001",
                "User blocked please talk to your administrator"
        );
      }
      if (user.get().getExpiredPassword().equals("Y")){
        return new ResponseApiJson<>(
                idRequest,
                "001",
                "Expired Password please talk to your administrator"
        );
      }
      //TODO CHECK EXPIRATION OF USER'S PASSWORD
      if(user.get().getDateEndPass().before(new Date())){
        user.get().setExpiredPassword("Y");
        user.ifPresent(repository::save);
        return new ResponseApiJson<>(
                idRequest,
                "001",
                "Expired Password please talk to your administrator !");
      }
      try {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUserCode(),
                        request.getPassword()
                )
        );
        user.get().setNumberOfTries(0);
        repository.save(user.get());
      } catch (Exception e) {
        user.get().setNumberOfTries(user.get().getNumberOfTries()+1);
        if (user.get().getNumberOfTries()>=3)
        {
          user.get().setBlockAccess('Y');
          user.get().setNumberOfTries(0);
        }
        repository.save(user.get());
        return tokenError.issue(5);
      }


      var jwtToken = jwtService.generateToken(user.get());
      var refreshToken = jwtService.generateRefreshToken(user.get());
      //revokeAllUserTokens(request.getUserCode());
      saveUserToken(user.get(), jwtToken);


      AuthenticationResponse a =  AuthenticationResponse.builder()
              .accessToken(jwtToken)
              .refreshToken(refreshToken)
              .userId(user.get().getUserCode())
              .userName(user.get().getUsername())
              .userRole(user.get().getUserType())
              .firstConnection(user.get().getFirstConnection())
              .languageCode(user.get().getLanguageCode())
              .bankCode(user.get().getUserBankCode())
              .build();

      return new ResponseApiJson<>(
              idRequest,
              GlobalVars.SUCCESS,
              "user logged in successfully !",
              a
      );
    }
  }

  private void saveUserToken(UserManagement user, String jwtToken) {
    var token = Token.builder()
        .user(user.getUserCode())
        .token(jwtToken)
        .tokenType(TokenType.BEARER)
        .expired(false)
        .revoked(false)
        .build();
    logger.info("save user token: ["+token.getId()+"]");
    tokenRepository.save(token);
  }

  public void revokeAllUserTokens(String token) {
    //var validUserTokens = tokenRepository.findByUserAndRevokedOrExpired(user,false,false);
    var validUserTokens = tokenRepository.findByToken(token);
    if (validUserTokens.isEmpty())
      return;

    validUserTokens.get().setExpired(true);
    validUserTokens.get().setRevoked(true);

    tokenRepository.save(validUserTokens.get());
  }

  public void refreshToken(
          HttpServletRequest request,
          HttpServletResponse response
  ) throws IOException {
    final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
    final String refreshToken;
    final String userEmail;
    if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
      return;
    }
    refreshToken = authHeader.substring(7);
    userEmail = jwtService.extractUsername(refreshToken);
    if (userEmail != null) {
      var user = this.repository.findById(userEmail)
              .orElseThrow();
      if (jwtService.isTokenValid(refreshToken, user)) {
        var accessToken = jwtService.generateToken(user);
        //revokeAllUserTokens(userEmail);
        saveUserToken(user, accessToken);
        var authResponse = AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
        new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
      }
    }
  }



  public String getClientIpAddress(HttpServletRequest request) {
    String xForwardedForHeader = request.getHeader("X-Forwarded-For");
    if (xForwardedForHeader != null) {
      // If the request went through a proxy, the original client IP may be in X-Forwarded-For header
      String[] ips = xForwardedForHeader.split(",");
      return ips[0].trim(); // Return the first IP in the list
    } else {
      // If there is no proxy, use the remote address from the request
      return request.getRemoteAddr();
    }
  }
}
