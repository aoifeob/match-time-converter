package com.example.matchtimeconverter.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MatchTimeInput {

  private String period;
  private int minutes;
  private double seconds;

}
