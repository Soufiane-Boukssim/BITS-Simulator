package com.simulator.globalService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simulator.authentification.AuthenticationRequest;
import com.simulator.authentification.AuthenticationResponse;
import com.simulator.authentification.RegisterRequest;
import com.simulator.config.GlobalVars;
import com.simulator.config.IpAddressHelper;
import com.simulator.config.JwtService;
import com.simulator.entities.*;
import com.simulator.globalController.AuthenticationController;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.repository.TokenRepository;
import com.simulator.repository.UserActivityTrackingRepository;
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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
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

  @Autowired
  EmailService emailService;

  @Autowired
  UserActivityTrackingRepository userActivityTrackingRepository;

  @Autowired
  GlobalVars globalVars;




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



  //////*************************

  public void initAdmin() {
    var user = UserManagement.builder()
            .userCode("epsAdmin")
            .userName2("eps")
            .email("admin@gmail.com")
            .userBankCode(null)
            .password(passwordEncoder.encode("epsAdmin@@"))
            .userType(Role.valueOf("ADMIN"))
            .numberOfTries(Integer.valueOf("90"))
            .numberOfTriesAllowed(90)
            .ipAddress("000.000.0.0")
            .connected("N")
            .firstConnection('N')
            .nbrSessionAllowed(99)
            .nbrSessionConnected(0)
            .lengthPassword(8)
            .complexityFlag('N')
            .expirationPassword(90)
            .dateStartPass(null)
            .dateEndPass(null)
            //.blockAccess('N')
            .blockAccess('N')
            .languageCode("en")
            .mobileNumber(null)
//              .status("P")
            .status("A")
            .expiredPassword("N")
            .build();



    logger.info("--->user"+user);

    repository.save(user);

//    if (management.isEmpty()) {
//      emailService.sendCodeByMail(mail);
//      repository.save(user);
//    }

  }


  /////************




  public ResponseApiJson<AuthenticationResponse> register(RegisterRequest request) {
    Optional<UserManagement> userManagement;
    String userM;
    String ipAddress = IpAddressHelper.getIpAddress();

    logger.info("--->request register"+request);
    if (GlobalVars.getBearerTokenHeader()!=null) {
      userManagement = globalVars.getUser(GlobalVars.getBearerTokenHeader());
      userM=userManagement.get().getUserCode();
    }
    else userM = request.getUserCode();
    String idRequest = "R_" + new Date().getTime() + (Math.random() * 9999);
    request.setPassword(generatePassword(8));
    logger.info("Password is: "+request.getPassword());

    Mail mail = new Mail(request.getEmail(), request.getPassword());

    try {
      //logger.info("Logged User ::> " + authentication.getName());
      var user = UserManagement.builder()
              .userCode(request.getUserCode())
              .userName2(request.getUserName())
              .email(request.getEmail())
              .userBankCode(request.getUserBankCode())
              .password(passwordEncoder.encode(request.getPassword()))
              .userType(request.getUserType())
              .numberOfTries(0)
              .numberOfTriesAllowed(request.getNumberOfTriesAllowed())
//              .ipAddress(request.getIpAddress())
              .ipAddress(ipAddress)
              .connected("N")
              .firstConnection('Y')
              .nbrSessionAllowed(99)
              .nbrSessionConnected(0)
              .address(request.getAddress())
              .lengthPassword(request.getPassword().length())
              .complexityFlag('N')
              .expirationPassword(90)
              .dateStartPass(request.getDateStartPass())
              .dateEndPass(request.getDateEndPass())
              //.blockAccess('N')
              .blockAccess(request.getBlockAccess())
              .languageCode(request.getLanguageCode())
              .mobileNumber(request.getMobileNumber())
              .status(request.getStatus())
              .expiredPassword("N")
              .build();


      logger.info("user   -->"+user);
      logger.info("start date license ::> " + request.getDateStartPass());
      logger.info("end date license ::> " + request.getDateEndPass());
      Optional<UserManagement> management = repository.findById(request.getUserCode());
      logger.info("found user " + management);
      if (management.isEmpty()) {
        emailService.sendCodeByMail(mail);
        repository.save(user);
        // user Activity tracking Hamza 29.02.2024 start
        UserActivityTracking userTrace = new UserActivityTracking(userM,"Add", "UserManagement","000.000.00.00", "Success", GlobalVars.SUCCESS,"Add user ( " + request.getUserCode() + " ) ","Account created successfully !",new Date());
        userActivityTrackingRepository.save(userTrace);
        // user Activity tracking Hamza 29.02.2024 end
        return new ResponseApiJson<>(
                idRequest,
                GlobalVars.SUCCESS,
                "Account created successfully !"
        );
      } else {
        // user Activity tracking Hamza 29.02.2024 start
        UserActivityTracking userTrace = new UserActivityTracking(userM,"Add", "UserManagement","000.000.00.00", "Failed", GlobalVars.ALREADYEXIST,"Add user ( " + request.getUserCode() + " ) ","Already exist!",new Date());
        userActivityTrackingRepository.save(userTrace);
        // user Activity tracking Hamza 29.02.2024 end
        return new ResponseApiJson<>(
                idRequest,
                GlobalVars.ALREADYEXIST,
                "Already exist!");
      }
    }catch(Exception e){
      logger.info( "Exception error ====>"+e.getMessage());
      // user Activity tracking Hamza 29.02.2024 start
      UserActivityTracking userTrace = new UserActivityTracking(userM,"Add", "UserManagement","000.000.00.00", "Failed", GlobalVars.EXCEPTIONSISSUE,"Add user ( " + request.getUserCode() + " ) ","Something went wrong, with user Creation",new Date());
      userActivityTrackingRepository.save(userTrace);
      // user Activity tracking Hamza 29.02.2024 end
      return new ResponseApiJson<>(
              idRequest,
              GlobalVars.EXCEPTIONSISSUE,
              "Account does not created !");
    }


  }

//  public ResponseApiJson<?> authenticate(AuthenticationRequest request) {
//    //logger.info("Logged User ::> " + authentication.getName());
//    logger.info("Logged User" + SecurityContextHolder.getContext().getAuthentication().getName());
//    String idRequest = "AU_" + new Date().getTime() + (Math.random() * 9999);
//    Optional<UserManagement> user = repository.findByUserCodeAndStatus(request.getUserCode(),"A");
//    if (user.isEmpty()) {
//      return new ResponseApiJson<>(
//              idRequest,
//              GlobalVars.NOTEXIST,
//              "User does not exist or still not approved"
//      );
//    } else {
//      if(user.get().getBlockAccess()=='Y'){
//        return new ResponseApiJson<>(
//                idRequest,
//                "001",
//                "User blocked please talk to your administrator"
//        );
//      }
//      if (user.get().getExpiredPassword().equals("Y")){
//        return new ResponseApiJson<>(
//                idRequest,
//                "001",
//                "Expired Password please talk to your administrator"
//        );
//      }
//      //TODO CHECK EXPIRATION OF USER'S PASSWORD
//      if(user.get().getDateEndPass().before(new Date())){
//        user.get().setExpiredPassword("Y");
//        user.ifPresent(repository::save);
//        return new ResponseApiJson<>(
//                idRequest,
//                "001",
//                "Expired Password please talk to your administrator !");
//      }
//      try {
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        request.getUserCode(),
//                        request.getPassword()
//                )
//        );
//        for (GrantedAuthority authority : SecurityContextHolder.getContext().getAuthentication().getAuthorities()) {
//          String userRole = authority.getAuthority();
//          logger.info("userRole=["+userRole +"]");
//
//        }
//        user.get().setNumberOfTries(0);
//        repository.save(user.get());
//      } catch (Exception e) {
//        user.get().setNumberOfTries(user.get().getNumberOfTries()+1);
//        if (user.get().getNumberOfTries()>=user.get().getNumberOfTriesAllowed())
//        {
//          user.get().setBlockAccess('Y');
//          user.get().setNumberOfTries(0);
//        }
//        repository.save(user.get());
//        return tokenError.issue(5);
//      }
//
//
//      var jwtToken = jwtService.generateToken(user.get());
//      var refreshToken = jwtService.generateRefreshToken(user.get());
//      //revokeAllUserTokens(request.getUserCode());
//      saveUserToken(user.get(), jwtToken);
//
//
//      AuthenticationResponse a =  AuthenticationResponse.builder()
//              .accessToken(jwtToken)
//              .refreshToken(refreshToken)
//              .userId(user.get().getUserCode())
//              .userName(user.get().getUsername())
//              .userRole(user.get().getUserType())
//              .firstConnection(user.get().getFirstConnection())
//              .languageCode(user.get().getLanguageCode())
//              .bankCode(user.get().getUserBankCode())
//              .build();
//      String requestBody = request.getUserCode() + " " + request.getPassword().hashCode();
//      UserActivityTracking userTrace = new UserActivityTracking(user.get().getUserCode(),"Login", "UserManagement","000.000.00.00", "Success", "000",requestBody,"user logged in successfully !",new Date());
//      userActivityTrackingRepository.save(userTrace);
//      return new ResponseApiJson<>(
//              idRequest,
//              GlobalVars.SUCCESS,
//              "user logged in successfully !",
//              a
//      );
//    }
//  }



  public ResponseApiJson<?> authenticate(AuthenticationRequest request ,String ipAdd) {
    //logger.info("Logged User ::> " + authentication.getName());
    logger.info("Logged User" + SecurityContextHolder.getContext().getAuthentication().getName());
    String idRequest = "AU_" + new Date().getTime() + (Math.random() * 9999);
    Optional<UserManagement> user = repository.findByUserCodeAndStatus(request.getUserCode(),"A");
    logger.info("REQUEST:: " + request.toString());
    String ipAddress = IpAddressHelper.getIpAddress();



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
//      if (user.get().getExpiredPassword().equals("Y")){
//        return new ResponseApiJson<>(
//                idRequest,
//                "001",
//                "Expired Password please talk to your administrator"
//        );
//      }
      logger.info("test date ---->" +user.get().getDateEndPass());
//      TODO CHECK EXPIRATION OF USER'S PASSWORD
      if(user.get().getDateEndPass() != null &&  user.get().getDateEndPass().before(new Date())){
        user.get().setExpiredPassword("Y");
        user.ifPresent(repository::save);
        return new ResponseApiJson<>(
                idRequest,
                "001",
                "Expired Password please talk to your administrator !");
      }else {
        user.get().setExpiredPassword("N");
        user.ifPresent(repository::save);
      }
      try {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUserCode(),
                        request.getPassword()
                )
        );
        for (GrantedAuthority authority : SecurityContextHolder.getContext().getAuthentication().getAuthorities()) {
          String userRole = authority.getAuthority();
          logger.info("userRole=["+userRole +"]");

        }
        user.get().setNumberOfTries(0);
        repository.save(user.get());
      } catch (Exception e) {
        user.get().setNumberOfTries(user.get().getNumberOfTries()+1);
        if (user.get().getNumberOfTries()>=user.get().getNumberOfTriesAllowed())
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

      UserManagement userManagementDate = user.get();
      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
      Date currentDate = new Date();
      String formattedDate = dateFormat.format(currentDate);
      logger.info("formattedDate ==>"+formattedDate);
      userManagementDate.setSecretKey(formattedDate);
      repository.save(userManagementDate);

      AuthenticationResponse a =  AuthenticationResponse.builder()
              .accessToken(jwtToken)
              .refreshToken(refreshToken)
              .userId(user.get().getUserCode())
              .userName(user.get().getUsername())
              .userRole(user.get().getUserType())
              .firstConnection(user.get().getFirstConnection())
              .languageCode(user.get().getLanguageCode())
              .bankCode(user.get().getUserBankCode())
              .ipAddress(ipAdd)
              .build();
      String requestBody = request.getUserCode() + " " + request.getPassword().hashCode();


//      UserActivityTracking userTrace = new UserActivityTracking(user.get().getUserCode(),"Login", "UserManagement",ipAddress, "Success", "000",requestBody,"user logged in successfully !",new Date(),null,null);
//      userActivityTrackingRepository.save(userTrace);

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


  public UserManagement getCurrentUser() {
    return repository.findById(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow();
  }
}
