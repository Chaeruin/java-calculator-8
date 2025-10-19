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
        if (isNotStartOrEndWithNumbers(input)) {
            throw new IllegalArgumentException(ErrorCode.INVALID_POSITION_OF_DELIMITER.getErrorName());
        }
        return input.split("[,:]");
    }

    private boolean isNotStartOrEndWithNumbers(String input) {
        return input.startsWith(",") || input.endsWith(",") ||
                input.startsWith(":") || input.endsWith(":");
    }
}
