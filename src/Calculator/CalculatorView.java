package Calculator;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import operations.Operation;

import java.util.List;

public class CalculatorView {

    private final CalculatorEngine engine;
    private Label display;

    public CalculatorView(CalculatorEngine engine) {
        this.engine = engine;
    }

    public Scene buildScene() {
        display = new Label("0");
        display.setStyle(
                "-fx-font-size: 44;" +
                        "-fx-background-color: #1c1c1e;" +
                        "-fx-text-fill: white;" +
                        "-fx-alignment: center-right;" +
                        "-fx-padding: 20 16 20 16;" +
                        "-fx-min-width: 330;" +
                        "-fx-min-height: 130;"
        );

        GridPane grid = new GridPane();
        grid.setHgap(8);
        grid.setVgap(8);
        grid.setPadding(new Insets(0, 8, 8, 8));
        grid.setStyle("-fx-background-color: #1c1c1e;");

        Button clearBtn = makeButton("C", "#636366");
        clearBtn.setMinWidth(4 * 72 + 3 * 8);
        clearBtn.setOnAction(e -> { engine.clear(); refreshDisplay(); });
        grid.add(clearBtn, 0, 0, 4, 1);

        String[][] digits = {
                {"7", "8", "9"},
                {"4", "5", "6"},
                {"1", "2", "3"}
        };

        for (int row = 0; row < digits.length; row++) {
            for (int col = 0; col < digits[row].length; col++) {
                String digit = digits[row][col];
                Button btn = makeButton(digit, "#3a3a3c");
                btn.setOnAction(e -> { engine.inputDigit(digit); refreshDisplay(); });
                grid.add(btn, col, row + 1);
            }
        }

        List<Operation> ops = engine.getRegistry().getAll();
        for (int i = 0; i < 3 && i < ops.size(); i++) {
            Operation op = ops.get(i);
            Button opBtn = makeButton(op.getSymbol(), "#ff9f0a");
            opBtn.setOnAction(e -> { engine.inputOperation(op); refreshDisplay(); });
            grid.add(opBtn, 3, i + 1);
        }

        Button zeroBtn = makeButton(".", "#3a3a3c");
        zeroBtn.setOnAction(e -> { engine.inputDigit("0"); refreshDisplay(); });
        grid.add(zeroBtn, 0, 4);

        Button dotBtn = makeButton("0", "#3a3a3c");
        dotBtn.setOnAction(e -> { engine.inputDigit("."); refreshDisplay(); });
        grid.add(dotBtn, 1, 4);

        Button equalsBtn = makeButton("=", "#ff9f0a");
        equalsBtn.setOnAction(e -> { engine.equals(); refreshDisplay(); });
        grid.add(equalsBtn, 2, 4);

        if (ops.size() >= 4) {
            Operation divOp = ops.get(3);
            Button divBtn = makeButton(divOp.getSymbol(), "#ff9f0a");
            divBtn.setOnAction(e -> { engine.inputOperation(divOp); refreshDisplay(); });
            grid.add(divBtn, 3, 4);
        }

        VBox root = new VBox(0, display, grid);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #1c1c1e;");

        return new Scene(root, 330, 540);
    }

    private Button makeButton(String label, String color) {
        Button btn = new Button(label);
        btn.setMinSize(72, 72);
        btn.setStyle(
                "-fx-background-color: " + color + ";" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 34;" +
                        "-fx-background-radius: 36;"
        );
        return btn;
    }

    private void refreshDisplay() {
        display.setText(engine.getDisplayText());
    }
}