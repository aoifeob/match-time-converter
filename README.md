# Application Description
This Spring Boot-driven command line application reads a text file containing match time String inputs in a specific format, and converts the Strings to another specific format to print them to the command line.

A version using Spring Shell can be found [here](https://github.com/aoifeob/spring-shell-match-time-converter).

Example Input: `[H1] 10:45.783`,
Corresponding Output: `10:46 - FIRST_HALF`

Example Input: `[H2] 95:09.162`, Corresponding Output: `90:00 + 05:09 - SECOND_HALF`

## Assumptions Made
* The end user may want to supply a file of their choice to the application, so the application should allow the user to supply the file name or path rather than hardcoding it.
* Inputs with a period of "PM", "HT", and "FT" should be considered valid even if the timestamp provided does not match their expected value ("0:00.000", "45:00.000", and "90:00.000", respectively). If required, these periods could easily be validated against the expected value by adding an additional method to `InputValidationService` to be called after line 31 of `MatchTimeTransformerService`. Example below:
```
private static final Map<String, String> validTimesForPeriod = ImmutableList.of(
"PM", "0:00.000",
"HT", "45:00.000",
"FT", "90:00.000");

boolean isTimeValidForPeriod(MatchTimeInput matchTimeInput){
  if (matchTimeInput != null && matchTimeInput.getPeriod() != null){
    if (validTimesForPeriod.get(matchTimeInput.getPeriod) != null){
      String timeAsString = matchTimeInput.getMinutes()+":"
                            + matchTimeInput.getSeconds()+"."
                            + matchTimeInput.getMilliseconds();
      return timeAsString.equals(validTimesForPeriod.get(matchTimeInput.getPeriod));
    }
    return true;
  }
  return false;
}
```    
* Similarly, inputs with a period of "H1" and a timestamp of 0:00.000, or a period of "H2" and a timestamp of before 45:00.000 are considered valid. If required, this could also be validated.
* Any value of seconds greater than 59 (not counting milliseconds) should be considered invalid. The additional seconds will not be converted and added to the minutes value.
* Any value of milliseconds greater than 999 should be considered invalid. The additional milliseconds will not be converted and added to the seconds and/or minutes value.
* A 3-digit match number may be considered valid if and only if the first digit is 1, as a match time above 100 minutes could still be considered valid due to extra time, but a match time above 199 minutes (not counting seconds or milliseconds) seems unlikely for this use case. However, match times greater than 199 minutes could easily be allowed by making a simple change to the regex in `InputValidationService`.

## Execution Instructions (Mac)
Requires: Java 8, Maven

1. Clone the repository to your local machine.
2. Run `mvn package` to create an executable .jar file.
3. Optionally, save the input file of your choice inside the `match-time-converter` folder. A sample file (`input.data`) is provided if you do not wish to create a new file.
4. Run `java -jar target/match-time-converter-0.0.1-SNAPSHOT.jar "path-to-file"`, replacing `path-to-file` with the path to the file you want converted (eg `java -jar target/match-time-converter-0.0.1-SNAPSHOT.jar "input.data"`). A relative path may be used for files below the `match-time-converter` folder, otherwise an absolute path should be provided. Multiple file paths may be provided.
5. The results will be printed to the command line in order.
