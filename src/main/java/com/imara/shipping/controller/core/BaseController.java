package com.imara.shipping.controller.core;

// import com.imara.sfa.handlers.AccessHandler;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class BaseController {

  // @Autowired
  // protected AccessHandler access;

  @ExceptionHandler(Exception.class)
  public Result handleValidationException(Exception ex) {
    ex.printStackTrace();
    String msg = ex.getMessage() == null ? "" : ex.getMessage();
    return new Result(Result.ERROR, "Error : " + msg);
  }

}
