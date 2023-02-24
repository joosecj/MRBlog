package com.mr.blog.dto.v1;

public class AccountCredentialsDTO {

  private String email;
  private String password;

  public AccountCredentialsDTO() {
  }

  public AccountCredentialsDTO(String email, String password) {
    this.email = email;
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }
}
