package com.mr.blog.services;

import com.mr.blog.dto.v1.AccountCredentialsDTO;
import com.mr.blog.entities.User;
import com.mr.blog.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
  @Autowired
  private UserRepository userRepository;

  public void signin(AccountCredentialsDTO data) {
    return try {
      var userName = data.getUserName();
      var password = data.getPassword();
      authenticationManager.authenticate(UsernamePasswordAuthenticationToken(userName, password))
      User user = userRepository.findByUserName(userName)
      val tokenResponse: TokenDTO = if (user != null) {
        tokenProvider.createAccesToken(userName!!, user.roles)
      } else {
        throw UsernameNotFoundException("Username $userName not found")
      }
      ResponseEntity.ok(tokenResponse)
    } catch (e: AuthenticationException) {
      throw BadCredentialsException("Invalid username or password supplied!")
    }
  }
}
