package errorhandler;

import ast.type.ErrorType;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class ErrorHandler {

    private static final ErrorHandler instance = new ErrorHandler();

    private ErrorHandler() {
    }

    public static ErrorHandler getInstance() {
        return instance;
    }

    private final List<ErrorType> errors = new ArrayList<>();

    public boolean anyError() {
        return !errors.isEmpty();
    }

    public void showErrors(PrintStream out) {
        errors.forEach(out::println);
    }

    public void addError(ErrorType error) {
        errors.add(error);
    }
}
