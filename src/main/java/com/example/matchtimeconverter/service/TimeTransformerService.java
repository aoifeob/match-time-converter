package com.example.matchtimeconverter.service;

import org.springframework.stereotype.Service;

@Service
public class TimeTransformerService {

  public int roundSeconds(double seconds){
    return (int) Math.round(seconds);
  }

}
