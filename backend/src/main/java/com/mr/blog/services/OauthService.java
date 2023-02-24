package com.mr.blog.services;

import com.mr.blog.dto.v1.AccountCredentialsDTO;
import com.mr.blog.dto.v1.TokenDTO;
import com.mr.blog.entities.User;
import com.mr.blog.repositories.UserRepository;
import com.mr.blog.security.jwt.JwtTokenProvider;
import com.mr.blog.services.exeptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class OauthService {
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private AuthenticationManager authenticationManager;
  @Autowired
  private JwtTokenProvider tokenProvider;

  public ResponseEntity<TokenDTO> signin(AccountCredentialsDTO data) {
    try {
      var email = data.getEmail();
      var password = data.getPassword();
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
      User user = userRepository.findByEmail(email);
      if (user != null) {
        return ResponseEntity.ok(tokenProvider.createAccesToken(email, user.getRoles()));
      } else {
        throw new ResourceNotFoundException("Username not found");
      }
    } catch (AuthenticationException e) {
      throw new BadCredentialsException("Invalid username or password supplied!");
    }
  }
}
