package calculator.util;

import java.util.regex.Matcher;

public class BasicDelimiterParser implements DelimiterParser {

    @Override
    public boolean isCustomDelimiter(String input) {
        Matcher matcher = DELIMITER_PATTERN.matcher(input);
        return matcher.find();
    }

    @Override
    public String[] splits(String input) {
        return input.split("[,:]");
    }
}
