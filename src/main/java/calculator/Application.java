package calculator;

import calculator.controller.CalculatorController;
import calculator.model.InputString;
import calculator.view.InputView;
import calculator.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView input = new InputView();
        OutputView output = new OutputView();
        InputString inputString = new InputString();

        CalculatorController controller = new CalculatorController(input, output, inputString);
        controller.run();
    }
}
