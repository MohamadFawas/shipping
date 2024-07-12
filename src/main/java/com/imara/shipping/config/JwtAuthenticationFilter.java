package com.imara.shipping.config;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.imara.shipping.controller.core.Result;
import com.imara.shipping.dto.UserDTO;
import com.imara.shipping.dto.UserDTOMapper;
import com.imara.shipping.model.LoginModel;
import com.imara.shipping.model.User;
import com.imara.shipping.utility.JSONUtility;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  private AuthenticationManager authenticationManager;
  private UserDTOMapper userMapper;
  private JSONUtility jsonUtil;
  private AppConfig config;

  public JwtAuthenticationFilter(AuthenticationManager authenticationManager, UserDTOMapper userMapper,
      JSONUtility jsonUtil, AppConfig config) {
    this.authenticationManager = authenticationManager;
    this.userMapper = userMapper;
    this.jsonUtil = jsonUtil;
    this.config = config;
  }

  /*
   * Trigger when we issue POST request to /login
   * We also need to pass in {"username":"dan", "password":"dan123"} in the
   * request body
   */
  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
      throws AuthenticationException {

    // Grab credentials and map them to login viewmodel
    LoginModel credentials = null;
    try {
      credentials = new ObjectMapper().readValue(request.getInputStream(), LoginModel.class);
    } catch (IOException e) {
      e.printStackTrace();
    }

    // Create login token
    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
            credentials.getPhoneNumber(),
            credentials.getPassword(),
            new ArrayList<>());

    // Authenticate user
    Authentication auth = authenticationManager.authenticate(authenticationToken);

    return auth;
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
      Authentication authResult) throws IOException, ServletException {
    // Grab principal
    User principal = (User) authResult.getPrincipal();
    // check if the user is active
    if (!principal.isActive()) {
      Result result = new Result(Result.ERROR, "Your account is not active");
      response.getWriter().write(jsonUtil.getJSon(result));
    } else {
      // Create JWT Token
      String token = JWT.create()
          .withSubject(principal.getUsername())
          // .withExpiresAt(new Date(System.currentTimeMillis() +
          // JwtProperties.EXPIRATION_TIME))
          .sign(HMAC512(JwtProperties.SECRET.getBytes()));

      // Add token in response
      response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + token);
      response.addHeader("Access-Control-Expose-Headers", JwtProperties.HEADER_STRING);
      response.addHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
      UserDTO dto = userMapper.getDTO(principal);

      String value;

      if (config.isEncrypted()) {
        value = "{ \"value\":\"" + jsonUtil.getEncryptedJson(dto) + "\" }";
      } else {
        value = "{ \"value\": " + jsonUtil.getJSon(dto) + " }";
      }
      response.setContentType("application/json");
      response.getWriter().write(value);
    }

  }

}