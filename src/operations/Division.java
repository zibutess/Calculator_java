package operations;

public class Division extends Operation {

    public Division() {super("/");}

    @Override
    public double apply(double left, double right) {
        if(right == 0) throw new ArithmeticException("Division by zero");
        else return left / right;
    }
}
