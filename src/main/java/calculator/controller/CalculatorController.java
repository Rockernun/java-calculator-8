package calculator.controller;

import calculator.model.InputString;
import calculator.view.InputView;
import calculator.view.OutputView;

public class CalculatorController {

    private final InputView input;
    private final OutputView output;
    private final InputString inputString;

    public CalculatorController(InputView input, OutputView output, InputString inputString) {
        this.input = input;
        this.output = output;
        this.inputString = inputString;
    }

    public void calculate() {
        output.printInputString();
        String source = input.input();
        int result = inputString.getResult(source);
        output.printResult(result);
    }
}
