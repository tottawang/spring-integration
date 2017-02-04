package com.sample.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import com.sample.conf.PrintInfo;
import com.sample.domain.DomainObject;

@Component
public class Cleaner {

  @Autowired
  private PrintInfo printInfo;

  public void cleanup(Message<?> message) {
    @SuppressWarnings("unchecked")
    ArrayList<DomainObject> objects = (ArrayList<DomainObject>) message.getPayload();
    printInfo.print("Cleaner: " + objects.size() + " messages to clean up");
  }

  public void cleanup() {
    printInfo.print("Cleaner: 0 messages to clean up");
  }
}
