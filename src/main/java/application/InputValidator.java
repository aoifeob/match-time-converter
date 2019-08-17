package application;

import java.util.regex.Pattern;

public final class InputValidator {

    private final Pattern validMatchTimeInputPattern = Pattern.compile("^\\[(PM|H1|HT|H2|FT)\\]\\s[0-9]{1,2}:[0-9]{1,2}.[0-9]{1,3}$");

    boolean isValidMatchTimeInput(String input) {
        return validMatchTimeInputPattern.matcher(input).matches();
    }
}
