package com.sample.service;

import java.util.List;

import org.springframework.integration.annotation.ReleaseStrategy;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class Aggregator {

  @ReleaseStrategy
  public boolean canRelease(List<Message<?>> messages) {
    return messages.size() == 1;
  }

}
