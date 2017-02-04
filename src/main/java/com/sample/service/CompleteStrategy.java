package com.sample.service;

import org.springframework.integration.aggregator.ReleaseStrategy;
import org.springframework.integration.store.MessageGroup;
import org.springframework.stereotype.Component;

@Component
public class CompleteStrategy implements ReleaseStrategy {

  @Override
  public boolean canRelease(MessageGroup group) {
    // as long as we have one message handled, cleaner can be executed
    return group.getMessages().size() == 1;
  }
}
