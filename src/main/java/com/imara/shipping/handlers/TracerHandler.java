package com.imara.shipping.handlers;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.*;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TracerHandler {

  public static Marker RESERVATION_LOGIC = MarkerFactory.getMarker("RESERVATION_LOGIC");
  public static Marker SMS = MarkerFactory.getMarker("SMS");
  public static Marker REQUEST = MarkerFactory.getMarker("REQUEST");
  public static Marker DEADLOCK = MarkerFactory.getMarker("DEADLOCK");
  public static Marker SYSTEM = MarkerFactory.getMarker("SYSTEM");
  public static Marker CACHE = MarkerFactory.getMarker("CACHE");
  public static Marker THREAD_DUMP = MarkerFactory.getMarker("THREAD_DUMP");

  public void trace(Marker marker, String message) {
    if (message != null && message.contains("jndi")) return;
    log.trace(marker, message);
  }

}
