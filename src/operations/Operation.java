package operations;

public abstract class Operation {

    private String symbol;

    public Operation(String symbol) {this.symbol = symbol;}

    public String getSymbol() {return symbol;}

    public abstract double apply(double left, double right);
}
