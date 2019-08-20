package com.example.matchtimeconverter.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TimeTransformerServiceTest {

  private TimeTransformerService timeTransformerService = new TimeTransformerService();

  @Test
  public void roundSecondsRoundsUp(){
    assertEquals("Seconds should have been rounded up", 13, timeTransformerService.roundSeconds(12.726d));
  }

  @Test
  public void roundSecondsRoundsDown(){
    assertEquals("Seconds should have been rounded down", 12, timeTransformerService.roundSeconds(12.158d));
  }

}
