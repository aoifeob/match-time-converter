package com.example.matchtimeconverter.service;

import static org.junit.Assert.*;

import com.example.matchtimeconverter.model.MatchTimeInput;
import org.junit.Test;

public class MatchTimeInputParsingServiceTest {

  private MatchTimeInputParsingService matchTimeInputParsingService = new MatchTimeInputParsingService();

  @Test
  public void parseStringToMatchTimeInput() {
    String input = "[H1] 30:45.162";
    MatchTimeInput matchTimeInput = matchTimeInputParsingService.parseStringToMatchTimeInput(input);
    assertEquals("H1", matchTimeInput.getPeriod());
    assertEquals(30, matchTimeInput.getMinutes());
    assertEquals(45.162f, matchTimeInput.getSeconds(), 0);
  }

  @Test
  public void getPeriodFromMatchTime() {
    String input = "[H2] 60:32.994";
    assertEquals("H2", matchTimeInputParsingService.getPeriodFromMatchTime(input));
  }

  @Test
  public void getMinutesFromMatchTime() {
    String input = "[H2] 60:32.994";
    assertEquals(60, matchTimeInputParsingService.getMinutesFromMatchTime(input));
  }

  @Test
  public void getSecondsFromMatchTime() {
    String input = "[H2] 60:32.994";
    assertEquals(32.994f, matchTimeInputParsingService.getSecondsFromMatchTime(input), 0);
  }

}
