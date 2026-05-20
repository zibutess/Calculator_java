package operations;

public class Subtraction extends Operation {

    public Subtraction() {super("-");}

    @Override
    public double apply(double left, double right) {return left - right;}
}
