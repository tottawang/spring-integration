package com.sample.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.integration.transformer.GenericTransformer;
import org.springframework.stereotype.Component;

@Component
public class Transformer implements GenericTransformer<String, List<String>> {

  @Override
  public List<String> transform(String source) {
    List<String> result = new ArrayList<String>();
    result.add(source);
    result.add("test2");
    result.add("test3");
    return result;
  }

}
