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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private AuthenticationManager authenticationManager;
  @Autowired
  private JwtTokenProvider tokenProvider;

  public TokenDTO signin(AccountCredentialsDTO data) {
    try {
      String email = data.getEmail();
      String password = data.getPassword();
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
      User user = userRepository.findByEmail(email);
      if (user != null) {
        return tokenProvider.createAccessToken(email, user.getRoles());
      } else {
        throw new ResourceNotFoundException("Email not found");
      }
    } catch (AuthenticationException e) {
      throw new BadCredentialsException("Invalid email or password supplied!");
    }
  }

  public TokenDTO refreshToken(String email, String refreshToken) {
    var user = userRepository.findByEmail(email);
    if (user != null) {
      return tokenProvider.refreshToken(refreshToken);
    } else {
      throw new UsernameNotFoundException("Email " + email + " not found!");
    }
  }
}
