package calculator.util;

import calculator.enums.ErrorCode;

public class InputValidator {

    public double validateIsNumber(String number) {
        try {
            double num = Double.parseDouble(number);
            return num;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorCode.IS_NOT_NUMBER.getErrorName());
        }
    }

    public double validateIsNumberPositive(double number) {
        if (number <= 0) {
            throw new IllegalArgumentException(ErrorCode.IS_NOT_POSITIVE_NUMBER.getErrorName());
        }
        return number;
    }
}
