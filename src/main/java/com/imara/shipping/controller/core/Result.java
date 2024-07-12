package com.imara.shipping.controller.core;

public class Result<T> {

  private int status;
  private String message;
  private T payLoad;

  public static int SUCCESS = 0;
  public static int ERROR = -9000;

  public Result() {
  }

  public Result(T payLoad) {
    this.payLoad = payLoad;
  }

  public Result(int status) {
    this.status = status;
  }

  public Result(int status, String message) {
    this.status = status;
    this.message = message;
  }

  public Result(T payLoad, int status, String message) {
    this.payLoad = payLoad;
    this.status = status;
    this.message = message;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public int getStatus() {
    return status;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public void setPayLoad(T payLoad) {
    this.payLoad = payLoad;
  }

  public T getPayLoad() {
    return payLoad;
  }

}