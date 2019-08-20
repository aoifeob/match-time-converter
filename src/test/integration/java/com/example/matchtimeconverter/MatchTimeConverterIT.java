package com.example.matchtimeconverter;

import static org.junit.Assert.assertEquals;

import com.example.matchtimeconverter.factory.MatchTimeOutputFactory;
import com.example.matchtimeconverter.service.FormattingService;
import com.example.matchtimeconverter.service.InputValidationService;
import com.example.matchtimeconverter.service.MatchTimeInputParsingService;
import com.example.matchtimeconverter.service.MatchTimeTransformerService;
import com.example.matchtimeconverter.service.PeriodTransformerService;
import com.example.matchtimeconverter.service.TimeTransformerService;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class MatchTimeConverterIT {

  private InputValidationService inputValidationService = new InputValidationService();
  private MatchTimeInputParsingService matchTimeInputParsingService = new MatchTimeInputParsingService();
  private PeriodTransformerService periodTransformerService = new PeriodTransformerService();
  private TimeTransformerService timeTransformerService = new TimeTransformerService();
  private FormattingService formattingService = new FormattingService();
  private MatchTimeOutputFactory matchTimeOutputFactory = new MatchTimeOutputFactory(periodTransformerService, timeTransformerService, formattingService);

  private MatchTimeTransformerService matchTimeTransformerService = new MatchTimeTransformerService(inputValidationService, matchTimeInputParsingService, matchTimeOutputFactory);

  private String input;
  private String expectedOutput;

  public MatchTimeConverterIT(String input, String expectedOutput){
    this.input = input;
    this.expectedOutput = expectedOutput;
  }

  @Parameters
  public static Collection<Object[]> data() {
    return Arrays.asList(new Object[][]{
        {"[PM] 0:00.000", "00:00 - PRE_MATCH"},
        {"[H1] 30:00.000", "30:00 - FIRST_HALF"},
        {"[HT] 45:00.000", "45:00 - HALF_TIME"},
        {"[H2] 60:00.000", "60:00 - SECOND_HALF"},
        {"[FT] 90:00.000", "90:00 - FULL_TIME"},
        {"[H1] 30:02.000", "30:02 - FIRST_HALF"},
        {"[H2] 65:00.153", "65:00 - SECOND_HALF"},
        {"[H2] 65:00.823", "65:01 - SECOND_HALF"},
        {"[H1] 50:06.000", "45:00 + 05:06 - FIRST_HALF"},
        {"[H2] 91:43.000", "90:00 + 01:43 - SECOND_HALF"},
        {"[PM] -1:00.000", "INVALID"},
        {"[H1] 45:60.000", "INVALID"},
        {"[HM] 45:00.000", "INVALID"},
        {"[H1] 300:00.000", "INVALID"},
        {"90:00.000", "INVALID"},
        {"[H1]", "INVALID"},
        {"ABCD", "INVALID"},
        {"23", "INVALID"},
        {"[P1] 10.50.100", "INVALID"},
        {"-100", "INVALID"},
        {"", "INVALID"},
        {null, "INVALID"}
    });
  }

  @Test
  public void convertMatchTimes(){
    assertEquals("Output should match expected value", expectedOutput, matchTimeTransformerService.transformMatchTimesList(
        Collections.singletonList(input)).get(0));
  }
}