package com.example.matchtimeconverter;

import com.example.matchtimeconverter.service.MatchTimeTransformerService;
import com.example.matchtimeconverter.util.FileReaderUtil;
import java.util.List;
import lombok.experimental.PackagePrivate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class ShellCommands {

  private FileReaderUtil fileReaderUtil;
  private MatchTimeTransformerService matchTimeTransformerService;

  @Autowired
  public ShellCommands(FileReaderUtil fileReaderUtil, MatchTimeTransformerService matchTimeTransformerService){
    this.fileReaderUtil = fileReaderUtil;
    this.matchTimeTransformerService = matchTimeTransformerService;
  }

  @ShellMethod(value = "Convert the match times in the file provided.", key = "convert")
  public void convertMatchTimesAndPrint(String path) {
    List<String> convertedMatchTimes = convertMatchTimes(path);
    printConvertedMatchTimes(convertedMatchTimes);
  }

  @PackagePrivate
  List<String> convertMatchTimes(String path){
    List<String> inputMatchTimes = fileReaderUtil.readFile(path);
    return matchTimeTransformerService.transformMatchTimesList(inputMatchTimes);
  }

  @PackagePrivate
  void printConvertedMatchTimes(List<String> convertedMatchTimes){
    for (String convertedMatchTime: convertedMatchTimes){
      System.out.println(convertedMatchTime);
    }
  }

}
