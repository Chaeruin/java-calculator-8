package calculator.service;

import calculator.enums.ErrorCode;
import calculator.util.InputValidator;
import java.util.Arrays;

public class Calculator {

    private final InputValidator inputValidator;

    public Calculator() {
        this.inputValidator = new InputValidator();
    }

    public int calculate(String[] splitNumbers) {
        int[] convertDouble = getStringToDoubles(splitNumbers);

        int result = 0;

        try {
            for (int number : convertDouble) {
                result = Math.addExact(result, number);
            }
        } catch (ArithmeticException e) {
            throw new IllegalArgumentException(ErrorCode.INVALID_RANGE_INTEGER.getErrorName());
        }

        return result;
    }

    private int[] getStringToDoubles(String[] splitNumbers) {
        if (inputValidator.validateIsNumber(splitNumbers)) {
            return Arrays.stream(splitNumbers)
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }
        return new int[]{};
    }
}
