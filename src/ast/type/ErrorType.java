package ast.type;

import errorhandler.ErrorHandler;
import ast.node.AbstractLocatable;
import semantic.Visitor;

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
        return "Semantic Error (" + super.toString() + "): " + message;
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }
}
