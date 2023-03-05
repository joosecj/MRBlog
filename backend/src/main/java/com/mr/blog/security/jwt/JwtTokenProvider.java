package com.mr.blog.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.mr.blog.dto.v1.TokenDTO;
import com.mr.blog.services.exeptions.ResourceNotFoundException;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
public class JwtTokenProvider {
  @Value("${security.jwt.token.secret-key:53cr37}")
  private String secretKey;

  @Value("${security.jwt.token.expire-length:3600000}")
  private Long validityInMilliSeconds;

  @Autowired
  private UserDetailsService userDetailsService;
  private Algorithm algorithm;

  @PostConstruct
  protected void init() {
    secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    algorithm = Algorithm.HMAC256(secretKey.getBytes());
  }

  public TokenDTO createAccessToken(String email, List<String> roles) {
    var now = new Date();
    var validity = new Date(now.getTime() + validityInMilliSeconds);
    var accessToken = getAccessToken(email, roles, now, validity);
    var refreshToken = getRefreshToken(email, roles, now);
    return new TokenDTO(
            email,
            true,
            now,
            validity,
            accessToken,
            refreshToken
    );
  }

  public TokenDTO refreshToken(String refreshToken) {
    if (refreshToken.contains("Bearer ")) refreshToken = refreshToken.substring("Bearer ".length());
    JWTVerifier verifier = JWT.require(algorithm).build();
    DecodedJWT decodedJWT = verifier.verify(refreshToken);
    String email = decodedJWT.getSubject();
    List<String> roles = decodedJWT.getClaim("roles").asList(String.class);
    return createAccessToken(email, roles);
  }

  private String getAccessToken(String email, List<String> roles, Date now, Date validity) {
    String issuerURL = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
    return JWT.create()
            .withClaim("roles", roles)
            .withIssuedAt(now)
            .withExpiresAt(validity)
            .withSubject(email)
            .withIssuer(issuerURL)
            .sign(algorithm)
            .trim();
  }

  private String getRefreshToken(String username, List<String> roles, Date now) {
    Date validityRefreshToken = new Date(now.getTime() + (validityInMilliSeconds * 3));
    return JWT.create()
            .withClaim("roles", roles)
            .withIssuedAt(now)
            .withExpiresAt(validityRefreshToken)
            .withSubject(username)
            .sign(algorithm)
            .strip();
  }


  public Authentication getAuthentication(String token) {
    DecodedJWT decodedJWT = decodedToken(token);
    UserDetails userDetails = userDetailsService.loadUserByUsername(decodedJWT.getSubject());
    return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
  }

  private DecodedJWT decodedToken(String token) {
    var algorithmDecode = Algorithm.HMAC256(secretKey.getBytes());
    JWTVerifier verifier = JWT.require(algorithmDecode).build();
    return verifier.verify(token);
  }

  public String resolveToken(HttpServletRequest require) {
    String bearerToken = require.getHeader("Authorization");
    if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring("Bearer ".length());
    }
    return null;
  }

  public Boolean validateToken(String token) {
    DecodedJWT decodedJWT = decodedToken(token);
    try {
      return !decodedJWT.getExpiresAt().before((new Date()));
    } catch (Exception e) {
      throw new ResourceNotFoundException("Expired or invalid JWT token!");
    }
  }
}
