package Calculator;

import operations.Operation;
import operations.OperationRegistry;

public class CalculatorEngine {

    private double storedValue = 0;
    private Operation pendingOperation = null;
    private String displayText = "0";
    private boolean freshOperand = true;

    private OperationRegistry registry;

    public CalculatorEngine(OperationRegistry registry) {this.registry = registry;}

    public void inputDigit(String digit) {
        if (freshOperand) {
            displayText = digit;
            freshOperand = false;
        } else {
            if (displayText.equals("0") && !digit.equals(".")) {displayText = digit;}
            else {displayText += digit;}
        }
    }

    public void inputOperation(Operation op) {
        if (pendingOperation != null && !freshOperand) {evaluate();}
        storedValue = currentValue();
        pendingOperation = op;
        freshOperand = true;
    }

    public void equals() {
        if (pendingOperation == null) return;
        evaluate();
        pendingOperation = null;
    }

    public void clear() {
        storedValue = 0;
        pendingOperation = null;
        displayText = "0";
        freshOperand = true;
    }

    public String getDisplayText() {return displayText;}

    public OperationRegistry getRegistry() {return registry;}

    private double currentValue() {return Double.parseDouble(displayText);}

    private void evaluate() {
        try {
            double result = pendingOperation.apply(storedValue, currentValue());
            displayText = format(result);
            storedValue = result;
            freshOperand = true;
        } catch (ArithmeticException e) {
            displayText = "Error";
            freshOperand = true;
        }
    }

    private String format(double value) {
        if (value == Math.floor(value) && Math.abs(value) < 1e15) {return String.valueOf((long) value);}
        return String.valueOf(value);
    }
}
