package com.example.matchtimeconverter.service;

import com.example.matchtimeconverter.model.MatchTimeInput;
import org.springframework.stereotype.Service;

@Service
public class MatchTimeInputParsingService {

  MatchTimeInput parseStringToMatchTimeInput(String matchTime){
    return MatchTimeInput.builder()
        .period(getPeriodFromMatchTime(matchTime))
        .minutes(getMinutesFromMatchTime(matchTime))
        .seconds(getSecondsFromMatchTime(matchTime))
        .build();
  }

  String getPeriodFromMatchTime(String matchTime){
    //get substring of characters between [ and ] to retrieve the short form period
    return matchTime.substring(matchTime.indexOf('[')+1, matchTime.indexOf(']'));
  }

  int getMinutesFromMatchTime(String matchTime){
    //get substring of characters after ] and before :, then trim to retrieve minutes
    return Integer.parseInt(matchTime.substring(matchTime.indexOf(']')+1, matchTime.indexOf(':')).trim());
  }

  double getSecondsFromMatchTime(String matchTime) {
    //get substring of characters after : to retrieve seconds with milliseconds after decimal point
    return Double.parseDouble(matchTime.substring(matchTime.indexOf(':')+1));
  }


}
