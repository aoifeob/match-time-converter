package com.example.matchtimeconverter.service;

import com.example.matchtimeconverter.factory.MatchTimeOutputFactory;
import com.example.matchtimeconverter.model.MatchTimeOutput;
import org.junit.Test;

public class MatchTimeTransformerServiceTest {

  private InputValidationService inputValidationService = new InputValidationService();
  private MatchTimeInputParsingService matchTimeInputParsingService = new MatchTimeInputParsingService();
  private PeriodTransformerService periodTransformerService = new PeriodTransformerService();
  private TimeTransformerService timeTransformerService = new TimeTransformerService();
  private FormattingService formattingService = new FormattingService();
  private MatchTimeOutputFactory matchTimeOutputFactory = new MatchTimeOutputFactory(periodTransformerService, timeTransformerService, formattingService);
  private MatchTimeTransformerService matchTimeTransformerService = new MatchTimeTransformerService(inputValidationService, matchTimeInputParsingService, matchTimeOutputFactory);

}
