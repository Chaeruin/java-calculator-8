package calculator.controller;

import calculator.service.Calculator;
import calculator.util.BasicDelimiterParser;
import calculator.util.CustomDelimiterParser;
import calculator.util.DelimiterParser;
import calculator.view.InputView;
import calculator.view.OutputView;

public class CalculatorController {

    private final InputView inputView;
    private final OutputView outputView;
    private final Calculator calculator;
    private DelimiterParser delimiterParser;

    public CalculatorController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.calculator = new Calculator();
        this.delimiterParser = new BasicDelimiterParser();
    }

    public void run() {
        String input = inputView.getInput();
        if (delimiterParser.isCustomDelimiter(input)) {
            delimiterParser = new CustomDelimiterParser();
        }
        int result = calculator.calculate(delimiterParser.splits(input));
        outputView.printCalculateResult(result);
    }
}
