package ast.type;

import errorhandler.ErrorHandler;
import ast.node.AbstractLocatable;

public class ErrorType extends AbstractLocatable implements Type {

    private final String message;

    public ErrorType(int line, int col, String message) {
        super(line, col);
        this.message = message;

        // Auto-registration
        ErrorHandler.getInstance().addError(this);
    }

    @Override
    public String toString() {
        return "Semantic Error (" + super.toString() + "):\n\t" + message;
    }
}
