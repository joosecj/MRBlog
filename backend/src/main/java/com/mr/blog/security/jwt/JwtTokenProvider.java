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

  public TokenDTO createAccesToken(String email, List<String> roles) {
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

  private String getRefreshToken(String email, List<String> roles, Date now) {
    var validityRefreshToken = new Date(now.getTime() + validityInMilliSeconds * 3);
    return JWT.create()
            .withClaim("roles", roles)
            .withExpiresAt(validityRefreshToken)
            .withSubject(email)
            .sign(algorithm)
            .trim();
  }

  public Authentication getAuthentication(String token) {
    DecodedJWT decodedJWT = decodedToken(token);
    UserDetails userDetails = userDetailsService.loadUserByUsername(decodedJWT.getSubject());
    return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
  }

  public DecodedJWT decodedToken(String token) {
    var algorithmDecode = Algorithm.HMAC256(secretKey.getBytes());
    JWTVerifier verifier = JWT.require(algorithmDecode).build();
    return verifier.verify(token);
  }

  public String resolveToken(HttpServletRequest require) {
    var bearerToken = require.getHeader("Authorization");
    if (!bearerToken.isBlank() && bearerToken.startsWith("Bearer")) {
      return bearerToken.substring("Bearer ".length());
    } else return null;
  }

  public Boolean validateToken(String token) {
    var decodedJWT = decodedToken(token);
    try {
      return !decodedJWT.getExpiresAt().before((new Date()));
    } catch (Exception e) {
      throw new ResourceNotFoundException("Expired or invalid JWT token!");
    }
  }
}
