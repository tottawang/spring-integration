package com.sample.service;

import java.util.List;

import org.springframework.integration.splitter.AbstractMessageSplitter;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class Splitter extends AbstractMessageSplitter {

  @SuppressWarnings("unchecked")
  @Override
  protected Object splitMessage(Message<?> message) {
    Object payload = message.getPayload();
    return (List<Integer>) payload;
  }
}
