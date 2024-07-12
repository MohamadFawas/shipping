package com.imara.shipping.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "sfa")
@Data
public class AppConfig {

  private String time_zone;
  private boolean encrypted;

  // odoo settings

}
