package com.example.matchtimeconverter.service;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.beans.factory.annotation.Autowired;

@RunWith(Parameterized.class)
public class InputValidationServiceTest {

  @Autowired
  private InputValidationService inputValidationService;


  private String inputString;
  private boolean isValid;

  public InputValidationServiceTest(String inputString, boolean isValid){
    this.inputString = inputString;
    this.isValid = isValid;
  }

  @Parameters
  public static Collection<Object[]> data() {
    return Arrays.asList(new Object[][]{
        {"[PM] 0:00.000", true},
        {"[H1] 30:00.000", true},
        {"[HT] 45:00.000", true},
        {"[H2] 60:00.000", true},
        {"[FT] 90:00.000", true},
        {"[H1] 30:02.000", true},
        {"[H2] 65:00.153", true},
        {"[H2] 65:00.823", true},
        {"[H1] 50:06.000", true},
        {"[H2] 91:43.000", true},
        {"[H2] 101:43.000", true},
        {"[H2] 201:43.000", false},
        {"[PM] -1:00.000", false},
        {"[H1] 300:00.000", false},
        {"[HF] 45:00.000", false},
        {"[H1] 45:60.000", false},
        {"[H2] 60:72:000", false},
        {"90:00.000", false},
        {"[H1]", false},
        {"ABCD", false},
        {"23", false},
        {"[P1] 10.50.100", false},
        {"-100", false},
        {"", false},
        {null, false}
    });
  }

  @Test
  public void validateMatchTime() {
    assertEquals("Result should match the expected boolean value", isValid, inputValidationService.isValidMatchTimeInputString(inputString));
  }

}
