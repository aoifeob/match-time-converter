package com.example.matchtimeconverter;

import com.example.matchtimeconverter.service.MatchTimeTransformerService;
import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(Parameterized.class)
@SpringBootTest
public class MatchTimeConverterIT {

  @Autowired
  private MatchTimeTransformerService matchTimeTransformerService;

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
//
//    assertEquals("Output should match expected value", expectedOutput, matchTimeTransformerService.transformMatchTime(
//        Collections.singletonList(input)).get(0));
  }
}
