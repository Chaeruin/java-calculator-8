package calculator.service;

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

        for (int num : convertDouble) {
            if (inputValidator.validateIsNumberPositive(num)) {
                result += num;
            }
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

    public boolean isResultInteger(double result) {
        int convertInteger = (int) result;
        return convertInteger == result;
    }

}
