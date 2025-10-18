package calculator.service;

import calculator.util.InputValidator;
import java.util.Arrays;

public class Calculator {

    private final InputValidator inputValidator;

    public Calculator() {
        this.inputValidator = new InputValidator();
    }

    public double calculate(String[] splitNumbers) {
        double[] convertDouble = new double[]{};
        if (inputValidator.validateIsNumber(splitNumbers)) {
            convertDouble = Arrays.stream(splitNumbers)
                    .mapToDouble(Double::parseDouble)
                    .toArray();
        }

        double result = 0;

        for (double num : convertDouble) {
            if (inputValidator.validateIsNumberPositive(num)) {
                result += num;
            }
        }
        return result;
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
