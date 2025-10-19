package calculator.util;

import java.util.regex.Pattern;

public interface DelimiterParser {

    Pattern DELIMITER_PATTERN = Pattern.compile("^//(.)\\n(.*)$");

    boolean isCustomDelimiter(String input);

    String[] splits(String input);
}
