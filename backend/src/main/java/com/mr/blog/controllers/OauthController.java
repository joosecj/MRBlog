package com.mr.blog.controllers;

import com.mr.blog.dto.v1.AccountCredentialsDTO;
import com.mr.blog.dto.v1.CategoryDTO;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("/auth")
class AuthController {

  @Autowired
  private AuthService authService;


  @Operation(summary = "Authenticates an user and return a token", description = "",
          tags = "Oauth"
  )
  @PostMapping(value = "/api/categories/v1")
  public void signin(AccountCredentialsDTO data) {
    return if (data.getUserName().isBlank() || data.getPassword().isBlank())
    ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request");
            else authService.signin(data);
  }

}
