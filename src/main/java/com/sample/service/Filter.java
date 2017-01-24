package com.sample.service;

import org.springframework.integration.core.GenericSelector;
import org.springframework.stereotype.Component;

@Component
public class Filter implements GenericSelector<Integer> {

  @Override
  public boolean accept(Integer source) {
    return source % 2 == 0;
  }
}
