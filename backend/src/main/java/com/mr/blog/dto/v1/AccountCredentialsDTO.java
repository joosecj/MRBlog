package com.mr.blog.dto.v1;

public class AccountCredentialsDTO {

  private String userName;
  private String password;

  public AccountCredentialsDTO(String userName, String password) {
    this.userName = userName;
    this.password = password;
  }

  public String getUserName() {
    return userName;
  }

  public String getPassword() {
    return password;
  }
}
