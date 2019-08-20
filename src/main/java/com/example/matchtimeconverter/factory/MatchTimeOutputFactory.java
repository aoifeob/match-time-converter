package com.example.matchtimeconverter.factory;

import com.example.matchtimeconverter.model.MatchTimeInput;
import com.example.matchtimeconverter.model.MatchTimeOutput;
import com.example.matchtimeconverter.service.FormattingService;
import com.example.matchtimeconverter.service.PeriodTransformerService;
import com.example.matchtimeconverter.service.TimeTransformerService;
import com.google.common.collect.ImmutableMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatchTimeOutputFactory {

  private Map<String, Integer> maxTimePerHalf = ImmutableMap.of
      ("H1", 45,
       "H2", 90);

  private PeriodTransformerService periodTransformerService;
  private TimeTransformerService timeTransformerService;
  private FormattingService formattingService;

  @Autowired
  public MatchTimeOutputFactory(PeriodTransformerService periodTransformerService,
      TimeTransformerService timeTransformerService,
      FormattingService formattingService){
    this.periodTransformerService = periodTransformerService;
    this.timeTransformerService = timeTransformerService;
    this.formattingService = formattingService;
  }

  private boolean hasAdditionalTime(MatchTimeInput matchTimeInput){
    if (maxTimePerHalf.containsKey(matchTimeInput.getPeriod())){
      int maxTime = maxTimePerHalf.get(matchTimeInput.getPeriod());
      return matchTimeInput.getMinutes() > maxTime ||
          (matchTimeInput.getMinutes() == maxTime && matchTimeInput.getSeconds() > 0);
    }
    return false;
  }

  public MatchTimeOutput transformInputToOutput(MatchTimeInput matchTimeInput){
    MatchTimeOutput matchTimeOutput = new MatchTimeOutput();
    String outputPeriod = periodTransformerService.getLongFormPeriod(matchTimeInput.getPeriod());
    matchTimeOutput.setPeriod(outputPeriod);

    if (hasAdditionalTime(matchTimeInput)){
      matchTimeOutput.setMinutes(maxTimePerHalf.get(matchTimeInput.getPeriod()));
      matchTimeOutput.setAdditionalMinutes(matchTimeInput.getMinutes() - maxTimePerHalf.get(matchTimeInput.getPeriod()));
      matchTimeOutput.setAdditionalSeconds(timeTransformerService.roundSeconds(matchTimeInput.getSeconds()));
    }
    else {
      matchTimeOutput.setMinutes(matchTimeInput.getMinutes());
      matchTimeOutput.setSeconds(timeTransformerService.roundSeconds(matchTimeInput.getSeconds()));
    }

    return matchTimeOutput;
  }

  public String getMatchTimeOutputAsString(MatchTimeOutput matchTimeOutput){
    StringBuilder output = new StringBuilder();
    output.append(formattingService.padValueToTwoDigits(matchTimeOutput.getMinutes()))
        .append(":")
        .append(formattingService.padValueToTwoDigits(matchTimeOutput.getSeconds()));
    if (matchTimeOutput.getAdditionalMinutes() > 0 || matchTimeOutput.getAdditionalSeconds() > 0) {
        output.append(" + ")
            .append(formattingService.padValueToTwoDigits(matchTimeOutput.getAdditionalMinutes()))
            .append(":")
            .append(formattingService.padValueToTwoDigits(matchTimeOutput.getAdditionalSeconds()));
    }
    output.append(" - ")
        .append(matchTimeOutput.getPeriod());
    return output.toString();
  }

}
