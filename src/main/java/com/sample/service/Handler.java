package com.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import com.sample.conf.PrintInfo;

@Component
public class Handler {

  @Autowired
  private PrintInfo printInfo;

  public Integer getMessage(Message<?> message) {
    Integer values = (Integer) message.getPayload();
    printInfo.print(values.toString());
    return values;
  }
}