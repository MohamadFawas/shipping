package com.imara.shipping.dto.core;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public abstract class AbstractDTO {

  public static int DEFAULT = 0;
  public static int DETAIL = 1;
  public static int OVERVIEW = 2;
  public static int PUBLIC = 3;
  public static int PUBLIC_DETAIL = 4;

  public long id;
  public LocalDateTime timestampC;
  public LocalDateTime timestampU;
  public long createdBy;
  public long updatedBy;

}
