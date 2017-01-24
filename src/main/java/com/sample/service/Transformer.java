package com.sample.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.transformer.GenericTransformer;
import org.springframework.stereotype.Component;

import com.sample.conf.PrintInfo;

@Component
public class Transformer implements GenericTransformer<Integer, List<Integer>> {

  @Autowired
  private PrintInfo printInfo;

  @Override
  public List<Integer> transform(Integer source) {
    printInfo.print(source.toString());
    return getNextFiveIntegers(source);
  }

  private List<Integer> getNextFiveIntegers(Integer source) {
    List<Integer> list = new ArrayList<Integer>();
    for (int i = 0; i < 5; i++) {
      list.add(source + i);
    }
    return list;
  }

}
