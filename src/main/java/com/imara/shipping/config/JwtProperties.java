package com.imara.shipping.config;

public class JwtProperties {

  public static final String SECRET = "imaraSFA@9876";
  public static final int EXPIRATION_TIME = 36_000_000; // 10 hours
  public static final String TOKEN_PREFIX = "Bearer ";
  public static final String HEADER_STRING = "Authorization";

}
