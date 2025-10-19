package calculator.util;

import calculator.enums.ErrorCode;
import java.util.regex.Matcher;

public class BasicDelimiterParser implements DelimiterParser {

    @Override
    public boolean isCustomDelimiter(String input) {
        Matcher matcher = DELIMITER_PATTERN.matcher(input);
        return matcher.find();
    }

    @Override
    public String[] splits(String input) {
        if (input.isEmpty()) {
            return new String[]{"0",};
        }
        if (isNotStartOrEndWithNumbers(input)) {
            throw new IllegalArgumentException(ErrorCode.INVALID_POSITION_OF_DELIMITER.getErrorName());
        }

        if (!isFitDelimiter(input)) {
            throw new IllegalArgumentException(ErrorCode.IS_NOT_CUSTOM_DELIMITER_INPUT.getErrorName());
        }

        return input.split("[,:]");
    }

    private boolean isFitDelimiter(String input) {
        char[] numbers = input.toCharArray();
        for (int i = 1; i < numbers.length; i += 2) {
            if (numbers[i] != ',' && numbers[i] != ':') {
                return false;
            }
        }
        return true;
    }

    private boolean isNotStartOrEndWithNumbers(String input) {
        return input.startsWith(",") || input.endsWith(",") ||
                input.startsWith(":") || input.endsWith(":");
    }
}
