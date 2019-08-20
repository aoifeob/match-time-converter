package com.example.matchtimeconverter.service;

import org.springframework.stereotype.Service;

@Service
public class FormattingService {

  public String padValueToTwoDigits(int valueForPadding){
    return String.format("%02d", valueForPadding);
  }

}
