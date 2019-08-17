package application;

import org.springframework.beans.factory.annotation.Autowired;

public class InputConverter {

    private static final String INVALID_INPUT_RESPONSE = "INVALID";

    @Autowired
    private InputValidator inputValidator;

    String convertMatchTime(String matchTimeInput) {
        if (!inputValidator.isValidMatchTimeInput(matchTimeInput)) {
            return INVALID_INPUT_RESPONSE;
        }
        return "";
    }

    private String convertMatchPeriodToLongform(String matchPeriod) {
        switch (matchPeriod) {
            case "PM":
                return "PRE_MATCH";
            case "H1":
                return "FIRST_HALF";
            case "HT":
                return "HALF_TIME";
            case "H2":
                return "SECOND_HALF";
            case "FT":
                return "FULL_TIME";
        }
        return "";
    }
}
