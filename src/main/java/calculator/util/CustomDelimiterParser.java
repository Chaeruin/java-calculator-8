package calculator.util;

import calculator.enums.ErrorCode;
import java.util.regex.Matcher;

public class CustomDelimiterParser implements DelimiterParser {

    private static String getIsPeriod(Matcher matcher) {
        String customDelimiter = matcher.group(1);
        if (customDelimiter.equals(".")) {
            customDelimiter = "\\.";
        }
        return customDelimiter;
    }

    @Override
    public boolean isCustomDelimiter(String input) {
        Matcher matcher = DELIMITER_PATTERN.matcher(input);
        return matcher.find();
    }

    @Override
    public String[] splits(String input) {
        String regulationInput = input.replace("\\n", "\n");
        Matcher matcher = DELIMITER_PATTERN.matcher(regulationInput);

        String customDelimiter = getIsPeriod(matcher);
        String otherNumbers = matcher.group(2);

        if (isNotStartOrEndWithNumbers(otherNumbers, customDelimiter)) {
            throw new IllegalArgumentException(ErrorCode.INVALID_POSITION_OF_DELIMITER.getErrorName());
        }
        if (!isFitDelimiter(otherNumbers, customDelimiter)) {
            throw new IllegalArgumentException(ErrorCode.DIFFERENT_CUSTOM_DELIMITER_INPUT.getErrorName());
        }
        return otherNumbers.split(customDelimiter);
    }

    private boolean isFitDelimiter(String otherNumbers, String delimiter) {
        char[] numbers = otherNumbers.toCharArray();
        for (int i = 1; i < numbers.length; i += 2) {
            if (numbers[i] != delimiter.charAt(0)) {
                return false;
            }
        }
        return true;
    }

    private boolean isNotStartOrEndWithNumbers(String otherNumbers, String customDelimiter) {
        return otherNumbers.startsWith(customDelimiter) || otherNumbers.endsWith(customDelimiter);
    }
}
