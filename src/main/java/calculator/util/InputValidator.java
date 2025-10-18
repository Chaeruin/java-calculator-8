package calculator.util;

import calculator.enums.ErrorCode;

public class InputValidator {

    public boolean validateIsNumber(String[] number) {
        try {
            for (String num : number) {
                double convertNum = Double.parseDouble(num);
            }
            return true;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorCode.IS_NOT_NUMBER.getErrorName());
        }
    }

    public boolean validateIsNumberPositive(double number) {
        if (number <= 0) {
            throw new IllegalArgumentException(ErrorCode.IS_NOT_POSITIVE_NUMBER.getErrorName());
        }
        return true;
    }
}
