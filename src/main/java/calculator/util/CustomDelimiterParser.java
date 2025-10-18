package calculator.util;

import java.util.regex.Matcher;

public class CustomDelimiterParser implements DelimiterParser {

    @Override
    public boolean isCustomDelimiter(String input) {
        Matcher matcher = DELIMITER_PATTERN.matcher(input);
        return matcher.find();
    }

    @Override
    public String[] splits(String input) {
        if (input.isBlank()) {
            return new String[]{};
        }

        String regulationInput = input.replace("\\n", "\n");
        Matcher matcher = DELIMITER_PATTERN.matcher(regulationInput);

        String customDelimiter = matcher.group(1);
        String otherNumbers = matcher.group(2);

        return otherNumbers.split(customDelimiter);
    }
}
