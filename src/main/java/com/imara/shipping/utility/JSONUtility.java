package com.imara.shipping.utility;

import com.google.gson.*;
import com.imara.shipping.config.AppConfig;
import com.imara.shipping.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.*;

@Component
public class JSONUtility {

  @Autowired
  private EncryptionUtility encrypt;

  @Autowired
  private AppConfig config;

  public String getEncryptedJson(Object obj) {
    if (obj == null) return null;
    try {
      if (config.isEncrypted()) return encrypt.encrypt(getJSon(obj));
      else return getJSon(obj);
    }
    catch (Exception e) {
      return "";
    }
  }

  public String getJSon(Object obj) {
    if (obj == null) return "";
    Gson gson = new GsonBuilder()
    .setPrettyPrinting()
    .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
    .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
    .create();
    return gson.toJson(obj);
  }

}
