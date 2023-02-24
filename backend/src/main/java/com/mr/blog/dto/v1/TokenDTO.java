package com.mr.blog.dto.v1;

import java.util.Date;

public class TokenDTO {
  private String email;
  private Boolean authenticated;
  private Date created;
  private Date expiration;
  private String accessToken;
  private String refreshToken;

  public TokenDTO() {
  }

  public TokenDTO(String email, Boolean authenticated, Date created, Date expiration, String accessToken, String refreshToken) {
    this.email = email;
    this.authenticated = authenticated;
    this.created = created;
    this.expiration = expiration;
    this.accessToken = accessToken;
    this.refreshToken = refreshToken;
  }

  public String getEmail() {
    return email;
  }

  public Boolean getAuthenticated() {
    return authenticated;
  }

  public Date getCreated() {
    return created;
  }

  public Date getExpiration() {
    return expiration;
  }

  public String getAccessToken() {
    return accessToken;
  }

  public String getRefreshToken() {
    return refreshToken;
  }
}
