package com.imara.shipping.utility;

import java.time.*;
import java.time.temporal.ChronoUnit;

public class CurrentTimeUtility {

  private static LocalDateTime testTime;
  private static LocalDateTime testSetTime;

  public static void setTestTime(LocalDateTime time) {
    testTime = time;
    testSetTime = LocalDateTime.now();
  }

  public static void resetTestTime() {
    testTime = null;
    testSetTime = null;
  }

  public static boolean isTestTimeSet() {
    return testTime != null;
  }

  public static LocalDateTime getCurrentTime() {
    if (testTime != null) {
      LocalDateTime now = LocalDateTime.now();
      long diff = ChronoUnit.NANOS.between(testSetTime, now);
      return testTime.plusNanos(diff);
    }
    else {
      return LocalDateTime.now();
    }
  }

  public static LocalDate getCurrentDate() {
    LocalDateTime currentTime = getCurrentTime();
    return currentTime.toLocalDate();
  }

}

