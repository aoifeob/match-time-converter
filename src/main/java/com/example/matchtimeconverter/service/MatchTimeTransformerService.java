package com.example.matchtimeconverter.service;

import com.example.matchtimeconverter.factory.MatchTimeOutputFactory;
import com.example.matchtimeconverter.model.MatchTimeInput;
import com.example.matchtimeconverter.model.MatchTimeOutput;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatchTimeTransformerService {

  private static final String INVALID_INPUT_RESPONSE = "INVALID";

  private InputValidationService inputValidationService;
  private MatchTimeInputParsingService matchTimeInputParsingService;
  private MatchTimeOutputFactory matchTimeOutputFactory;

  @Autowired
  public MatchTimeTransformerService(InputValidationService inputValidationService,
      MatchTimeInputParsingService matchTimeInputParsingService,
      MatchTimeOutputFactory matchTimeOutputFactory){
    this.inputValidationService = inputValidationService;
    this.matchTimeInputParsingService = matchTimeInputParsingService;
    this.matchTimeOutputFactory = matchTimeOutputFactory;
  }

  public List<String> transformMatchTimesList(List<String> matchTimeList){
    List<String> convertedMatchTimeList = new ArrayList<>();
    for (String matchTime : matchTimeList){
      convertedMatchTimeList.add(transformMatchTime(matchTime));
    }
    return convertedMatchTimeList;
  }

  private String transformMatchTime(String matchTime){
    if (!inputValidationService.isValidMatchTimeInputString(matchTime)){
      return INVALID_INPUT_RESPONSE;
    }
    MatchTimeInput matchTimeInput = matchTimeInputParsingService.parseStringToMatchTimeInput(matchTime);
    MatchTimeOutput matchTimeOutput = matchTimeOutputFactory.transformInputToOutput(matchTimeInput);
    return matchTimeOutputFactory.getMatchTimeOutputAsString(matchTimeOutput);
  }

}
