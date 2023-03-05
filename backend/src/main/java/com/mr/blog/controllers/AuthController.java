package com.mr.blog.controllers;

import com.mr.blog.dto.v1.AccountCredentialsDTO;
import com.mr.blog.services.AuthService;
import com.mr.blog.util.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
  @Autowired
  private AuthService authService;

  @Operation(summary = "Authenticates an user and return a token",
          description = "Authenticates an user and return a token",
          tags = "Auth"
  )
  @PostMapping(value = "/signin",
  produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_YML})
  public ResponseEntity<?> signin(@RequestBody AccountCredentialsDTO data) {
    authService.signin(data);
    return ResponseEntity.ok(data);
  }

  @Operation(summary = "Refresh token for authenticated user and returns a token")
  @PutMapping(value = "/refresh/{username}",
          produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_YML})
  public ResponseEntity refreshToken(@PathVariable("username") String username,
                                     @RequestHeader("Authorization") String refreshToken) {
    if (checkIfParamsIsNotNull(username, refreshToken))
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
    var token = authService.refreshToken(username, refreshToken);
    if (token == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
    return ResponseEntity.ok(token);
  }

  private boolean checkIfParamsIsNotNull(String username, String refreshToken) {
    return refreshToken == null || refreshToken.isBlank() ||
            username == null || username.isBlank();
  }

  private boolean checkIfParamsIsNotNull(AccountCredentialsDTO data) {
    return data == null || data.getEmail() == null || data.getEmail().isBlank()
            || data.getPassword() == null || data.getPassword().isBlank();
  }

}
