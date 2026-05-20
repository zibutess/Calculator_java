package operations;

import java.util.ArrayList;
import java.util.List;

public class OperationRegistry {

    private List<Operation> operations = new ArrayList<>();

    public OperationRegistry() {
        operations.add(new Addition());
        operations.add(new Subtraction());
        operations.add(new Multiplication());
        operations.add(new Division());
    }

    public List<Operation> getAll() {
        return operations;
    }
}
