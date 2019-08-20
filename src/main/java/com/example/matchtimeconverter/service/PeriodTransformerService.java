package com.example.matchtimeconverter.service;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class PeriodTransformerService {

  private final Map<String, String> periodMap = ImmutableMap.of
      ("PM", "PRE_MATCH",
      "H1", "FIRST_HALF",
      "HT", "HALF_TIME",
      "H2","SECOND_HALF",
      "FT", "FULL_TIME");

  public String getLongFormPeriod(String shortFormPeriod){
    return periodMap.get(shortFormPeriod);
  }

}
