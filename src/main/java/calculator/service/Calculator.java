package calculator.service;

import calculator.util.InputValidator;
import java.util.Arrays;

public class Calculator {

    private final InputValidator inputValidator;

    public Calculator() {
        this.inputValidator = new InputValidator();
    }

    public double calculate(String[] splitNumbers) {
        double[] convertDouble = getStringToDoubles(splitNumbers);

        double result = 0;

        for (double num : convertDouble) {
            if (inputValidator.validateIsNumberPositive(num)) {
                result += num;
            }
        }
        return result;
    }

    private double[] getStringToDoubles(String[] splitNumbers) {
        if (inputValidator.validateIsNumber(splitNumbers)) {
            return Arrays.stream(splitNumbers)
                    .mapToDouble(Double::parseDouble)
                    .toArray();
        }
        return new double[]{};
    }

    public boolean isResultInteger(double result) {
        int convertInteger = (int) result;
        return convertInteger == result;
    }

    public String getResult(double result) {
        if (isResultInteger(result)) {      // 결과가 정수일 때
            return String.valueOf((int) result);
        }
        // 결과가 소수일때
        return String.valueOf(result);
    }
}
