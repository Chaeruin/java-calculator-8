package calculator.util;

import calculator.enums.ErrorCode;
import java.util.regex.Matcher;

public class CustomDelimiterParser implements DelimiterParser {

    @Override
    public boolean isCustomDelimiter(String input) {
        input = input.replace("\\n", "\n");
        Matcher matcher = DELIMITER_PATTERN.matcher(input);
        return matcher.find();
    }

    @Override
    public String[] splits(String input) {
        input = input.replace("\\n", "\n");
        Matcher matcher = DELIMITER_PATTERN.matcher(input);
        if (!matcher.find()) {
            throw new IllegalArgumentException(ErrorCode.IS_NOT_CUSTOM_DELIMITER_INPUT.getErrorName());
        }
        String customDelimiter = matcher.group(1);
        String otherNumbers = matcher.group(2);
        judgeValidInput(otherNumbers, customDelimiter);
        if (isPeriod(matcher)) {
            return otherNumbers.split("\\.");
        }
        return otherNumbers.split(customDelimiter);
    }

    private void judgeValidInput(String otherNumbers, String customDelimiter) {
        if (isNotStartOrEndWithNumbers(otherNumbers, customDelimiter)) {
            throw new IllegalArgumentException(ErrorCode.INVALID_POSITION_OF_DELIMITER.getErrorName());
        }
    }

    private boolean isPeriod(Matcher matcher) {
        String customDelimiter = matcher.group(1);
        return customDelimiter.equals(".");
    }

    private boolean isNotStartOrEndWithNumbers(String otherNumbers, String customDelimiter) {
        return otherNumbers.startsWith(customDelimiter) || otherNumbers.endsWith(customDelimiter);
    }
}
