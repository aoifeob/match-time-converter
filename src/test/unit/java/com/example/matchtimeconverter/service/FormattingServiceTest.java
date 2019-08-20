package com.example.matchtimeconverter.service;

import static org.junit.Assert.*;

import org.junit.Test;

public class FormattingServiceTest {

  private FormattingService formattingService = new FormattingService();

  @Test
  public void padValueToTwoDigitsPadsSingleDigit(){
    //if value is one digit, method should pad it to two digits by adding a leading 0
    assertEquals("Value should have been padded with a leading 0", "02",formattingService.padValueToTwoDigits(2));
  }

  @Test
  public void padValueToTwoDigitsDoesNotPadDoubleDigits(){
    //if value is two digits, method should not change it
    assertEquals("Value should be unchanged", "10",formattingService.padValueToTwoDigits(10));
  }

}
