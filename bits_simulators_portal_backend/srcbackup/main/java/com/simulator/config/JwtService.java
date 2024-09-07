package com.simulator.config;

import com.simulator.repository.TokenRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

  private static final Logger logger = LogManager.getLogger(JwtService.class);
  private String secretKey="7538782F413F4428472B4B6250645367566B5970337336763979244226452948";

  private long jwtExpiration=86400000;

  private long refreshExpiration=604800000;
  @Autowired
  TokenRepository tokenRepository;


  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }
  public String generateTokenForOtp(UserDetails userDetails){
    return generateTokenForOtp(new HashMap<>(),userDetails);
  }
  public String generateTokenForOtp (Map<String,Object> extraClaims, UserDetails userDetails){
    Date currentDate = new Date();
    return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername())
            .setIssuedAt(new Date( currentDate.getTime()))
            .setExpiration(new Date(currentDate.getTime() + 1000 * 30 * 60))
            .signWith(getSignInKey(), SignatureAlgorithm.HS256)
            .compact();
  }
  public String generateToken(UserDetails userDetails) {
    return generateToken(new HashMap<>(), userDetails);
  }

  public String generateToken(
      Map<String, Object> extraClaims,
      UserDetails userDetails
  ) {
    return buildToken(extraClaims, userDetails, jwtExpiration);
  }

  public String generateRefreshToken(
      UserDetails userDetails
  ) {
    return buildToken(new HashMap<>(), userDetails, refreshExpiration);
  }

  private String buildToken(
          Map<String, Object> extraClaims,
          UserDetails userDetails,
          long expiration
  ) {
    return Jwts
            .builder()
            .setClaims(extraClaims)
            .setSubject(userDetails.getUsername())
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + expiration))
            .signWith(getSignInKey(), SignatureAlgorithm.HS256)
            .compact();
  }

  public boolean isTokenValid(String token, UserDetails userDetails) {
    final String username = extractUsername(token);

    logger.info("Is token valid "+userDetails.getUsername()+ " " + username.equals(userDetails.getUsername()) + " "+ !isTokenExpired(token)+" " );
    return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
  }

  private boolean isTokenExpired(String token) {
    if (extractExpiration(token).before(new Date())){
      revokeAllUserTokens(token);
    }
    return extractExpiration(token).before(new Date());
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
  private Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  private Claims extractAllClaims(String token) {

      return Jwts
              .parserBuilder()
              .setSigningKey(getSignInKey())
              .build()
              .parseClaimsJws(token)
              .getBody();


  }

  private Key getSignInKey() {
    //logger.info(secretKey+" secretKey");
    byte[] keyBytes = Decoders.BASE64.decode(secretKey);
    return Keys.hmacShaKeyFor(keyBytes);
  }
}
