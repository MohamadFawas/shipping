package com.imara.shipping.config;

import com.imara.shipping.dto.UserDTOMapper;
import com.imara.shipping.handlers.TracerHandler;
import com.imara.shipping.service.UserService;
import com.imara.shipping.utility.JSONUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Autowired
  private UserDTOMapper userMapper;

  @Autowired
  private JSONUtility jsonUtil;

  @Autowired
  private AppConfig config;

  @Autowired
  private TracerHandler tracer;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) {
    auth.authenticationProvider(authenticationProvider());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {

      http.cors().configurationSource(corsConfigurationSource()).and()
              // remove csrf and state in session because in jwt we do not need them
              .csrf().disable()
              .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
              .and()
              // add jwt filters (1. authentication, 2. authorization)
              .addFilter(new JwtAuthenticationFilter(authenticationManager(), userMapper, jsonUtil, config))
              .addFilter(new JwtAuthorizationFilter(authenticationManager(), this.userService, this.tracer))
              .authorizeRequests()
              .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
              //.antMatchers("/api/user/save").authenticated() // This is opened for public
    // .antMatchers("/api/system/getTestTime/**").permitAll() // This is opened for public to show the reservation status
    // .antMatchers("/api/system/dump/**").permitAll() // This is opened for emergency dump
    // .antMatchers("/api/resources/getAllMessages/**").permitAll() // This is opened for public to show the reservation status
    // .antMatchers("/api/user/changeCredential").authenticated()
     .antMatchers("/api/users/update_active").permitAll()
     .antMatchers("/api/**").permitAll()
    // .antMatchers("/swagger-ui.html").denyAll()
    // .antMatchers("/").permitAll();
    // .antMatchers("/favicon.ico").permitAll()
    // .antMatchers("/scripts/**").permitAll()
    // .antMatchers("/assets/**").permitAll()
    // .antMatchers("/logos/**").permitAll()
    // .antMatchers("/sock/**").permitAll()
    .antMatchers("/**").permitAll();
  }

  @Bean
  DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
      daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
      daoAuthenticationProvider.setUserDetailsService(this.userService);
      return daoAuthenticationProvider;
  }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("*");
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


}
