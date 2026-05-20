package Calculator;

import javafx.application.Application;
import javafx.stage.Stage;
import operations.OperationRegistry;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        OperationRegistry registry = new OperationRegistry();
        CalculatorEngine engine = new CalculatorEngine(registry);
        CalculatorView view = new CalculatorView(engine);

        stage.setScene(view.buildScene());
        stage.setTitle("Calculator");
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {launch(args);}
}