package com.example.matchtimeconverter.factory;

import static org.junit.Assert.assertEquals;

import com.example.matchtimeconverter.model.MatchTimeOutput;
import com.example.matchtimeconverter.service.FormattingService;
import com.example.matchtimeconverter.service.PeriodTransformerService;
import com.example.matchtimeconverter.service.TimeTransformerService;
import org.junit.Test;

public class MatchTimeOutputFactoryTest {

  private PeriodTransformerService periodTransformerService = new PeriodTransformerService();
  private TimeTransformerService timeTransformerService = new TimeTransformerService();
  private FormattingService formattingService = new FormattingService();
  private MatchTimeOutputFactory matchTimeOutputFactory = new MatchTimeOutputFactory(periodTransformerService, timeTransformerService, formattingService);

  @Test
  public void getMatchTimeOutPutAsStringIsCorrect(){
    MatchTimeOutput actualOutput = MatchTimeOutput.builder()
        .minutes(47)
        .seconds(12)
        .period("Second Half")
        .build();
    String expectedOutput = "47:12 - Second Half";
    assertEquals("String output should match expected value", expectedOutput, matchTimeOutputFactory.getMatchTimeOutputAsString(actualOutput));
  }

  @Test
  public void getMatchTimeOutputAsStringWithAdditionalMinutesIsCorrect(){
    MatchTimeOutput actualOutput = MatchTimeOutput.builder()
        .minutes(47)
        .seconds(12)
        .additionalMinutes(1)
        .period("Second Half")
        .build();
    String expectedOutput = "47:12 + 01:00 - Second Half";
    assertEquals("String output should match expected value", expectedOutput, matchTimeOutputFactory.getMatchTimeOutputAsString(actualOutput));
  }

  @Test
  public void getMatchTimeOutputAsStringWithAdditionalSecondsIsCorrect(){
    MatchTimeOutput actualOutput = MatchTimeOutput.builder()
        .minutes(47)
        .seconds(12)
        .additionalSeconds(5)
        .period("Second Half")
        .build();
    String expectedOutput = "47:12 + 00:05 - Second Half";
    assertEquals("String output should match expected value", expectedOutput, matchTimeOutputFactory.getMatchTimeOutputAsString(actualOutput));
  }

  @Test
  public void getMatchTimeOutputAsStringWithAdditionalMinutesAndSecondsIsCorrect(){
    MatchTimeOutput actualOutput = MatchTimeOutput.builder()
        .minutes(47)
        .seconds(12)
        .additionalMinutes(1)
        .additionalSeconds(30)
        .period("Second Half")
        .build();
    String expectedOutput = "47:12 + 01:30 - Second Half";
    assertEquals("String output should match expected value", expectedOutput, matchTimeOutputFactory.getMatchTimeOutputAsString(actualOutput));
  }

}
