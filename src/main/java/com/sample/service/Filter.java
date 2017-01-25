package com.sample.service;

import org.springframework.integration.core.GenericSelector;
import org.springframework.stereotype.Component;

@Component
public class Filter implements GenericSelector<String> {

  @Override
  public boolean accept(String source) {
    return !source.equals("test2");
  }
}
