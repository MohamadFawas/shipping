package com.imara.shipping.utility;

import com.imara.shipping.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.*;

@Component
public class TimeZoneConverter {

  @Autowired
  private AppConfig config;

  /**
   * Client(web / flutter) sends date in UTC time zone, we must convert that to local time
   * @param time
   * @return
   */
  public LocalDateTime convertFromUTC(LocalDateTime time) {
    if (time == null) return null;
    ZoneId oldZone = ZoneId.of("UTC");
    ZoneId newZone = ZoneId.of(config.getTime_zone());
    return time.atZone(oldZone)
    .withZoneSameInstant(newZone)
    .toLocalDateTime();
  }

}
