package ast.type;

import ast.node.Locatable;
import errorhandler.ErrorHandler;
import semantic.Visitor;

import java.util.List;

public class ErrorType extends AbstractType implements Locatable {

    private final int line;
    private final int col;
    private final String message;

    public ErrorType(Locatable location, String message) {
        this(location.getLine(), location.getCol(), message);
    }

    public ErrorType(int line, int col, String message) {
        this.line = line;
        this.col = col;
        this.message = message;

        // Auto-registration
        ErrorHandler.getInstance().addError(this);
    }

    @Override
    public final int getLine() {
        return this.line;
    }

    @Override
    public final int getCol() {
        return this.col;
    }

    @Override
    public String toString() {
        return "Semantic Error (" + this.line + "," + this.col + "): " + message;
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }

    @Override
    public String typeExpression() {
        return "error";
    }

    // Type operations

    @Override
    public Type squareBrackets(Type idxType, Locatable location) {
        return this;
    }

    @Override
    public Type parenthesis(List<Type> argTypes, Locatable location) {
        return this;
    }

    @Override
    public Type arithmetic(Type type, Locatable location) {
        return this;
    }

    @Override
    public Type compare(Type type, Locatable location) {
        return this;
    }

    @Override
    public Type logicCompare(Type type, Locatable location) {
        return this;
    }

    @Override
    public Type percentage(Type type, Locatable location) {
        return this;
    }

    @Override
    public Type cast(Type type, Locatable location) {
        return this;
    }

    @Override
    public Type dot(String fieldName, Locatable location) {
        return this;
    }

    @Override
    public Type exclamation(Locatable location) {
        return this;
    }

    @Override
    public Type minus(Locatable location) {
        return this;
    }

    @Override
    public void assign(Type type, Locatable location) {
        // Avoid generating a new error
    }

    @Override
    public void ret(Type type, Locatable location) {
        // Avoid generating a new error
    }

    @Override
    public void mustBeReadable(Locatable location) {
        // Avoid generating a new error
    }

    @Override
    public void mustBeWritable(Locatable location) {
        // Avoid generating a new error
    }

    @Override
    public void mustBeCondition(Locatable location) {
        // Avoid generating a new error
    }

    @Override
    public void mustBeAssignable(Locatable location) {
        // Avoid generating a new error
    }
}
