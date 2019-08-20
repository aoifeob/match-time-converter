package com.example.matchtimeconverter.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MatchTimeOutput {

  private int minutes;
  private int seconds;
  private int additionalMinutes;
  private int additionalSeconds;
  private String period;

}
