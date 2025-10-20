package calculator.util;

import calculator.enums.ErrorCode;

public class InputValidator {

    public boolean validateIsNumber(String[] number) {
        try {
            for (String num : number) {
                if (num.isEmpty()) {
                    throw new IllegalArgumentException(ErrorCode.ENTERED_DELIMITER_CONTINUOUS.getErrorName());
                }
                int convertNum = Integer.parseInt(num);
                validateIsNumberPositive(convertNum);
            }
            return true;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorCode.IS_NOT_NUMBER.getErrorName());
        }
    }

    public boolean validateIsNumberPositive(int number) {
        if (number <= 0) {
            throw new IllegalArgumentException(ErrorCode.IS_NOT_POSITIVE_NUMBER.getErrorName());
        }
        return true;
    }
}
