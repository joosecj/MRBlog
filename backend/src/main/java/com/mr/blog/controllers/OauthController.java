package com.mr.blog.controllers;

import com.mr.blog.dto.v1.AccountCredentialsDTO;
import com.mr.blog.services.OauthService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/oauth")
public class OauthController {
  @Autowired
  private OauthService oauthService;

  @Operation(summary = "Authenticates an user and return a token",
          description = "Authenticates an user and return a token",
          tags = "Oauth"
  )
  @PostMapping(value = "/signin")
  public ResponseEntity<AccountCredentialsDTO> signin(AccountCredentialsDTO data) {
    oauthService.signin(data);
    return ResponseEntity.ok(data);
  }

}
