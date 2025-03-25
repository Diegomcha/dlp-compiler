package ast.type;

import ast.node.Locatable;
import errorhandler.ErrorHandler;
import semantic.Visitor;

import java.util.List;

public class ErrorType extends AbstractType implements Locatable {

    private final int line;
    private final int col;
    private final String message;

    // TODO: Figure out how to pass location & delete this bad implementation
    public ErrorType(String message) {
        this(-1, -1, message);
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
    public Type squareBrackets(Type idxType) {
        return this;
    }

    @Override
    public Type parenthesis(List<Type> argTypes) {
        return this;
    }

    @Override
    public Type arithmetic(Type type) {
        return this;
    }

    @Override
    public Type compare(Type type) {
        return this;
    }

    @Override
    public Type logicCompare(Type type) {
        return this;
    }

    @Override
    public Type percentage(Type type) {
        return this;
    }

    @Override
    public Type cast(Type type) {
        return this;
    }

    @Override
    public Type dot(String fieldName) {
        return this;
    }

    @Override
    public Type exclamation() {
        return this;
    }

    @Override
    public Type minus() {
        return this;
    }

    @Override
    public void assign(Type type) {
        // Avoid generating a new error
    }

    @Override
    public void ret(Type type) {
        // Avoid generating a new error
    }

    @Override
    public void mustBeReadable() {
        // Avoid generating a new error
    }

    @Override
    public void mustBeWritable() {
        // Avoid generating a new error
    }

    @Override
    public void mustBeCondition() {
        // Avoid generating a new error
    }
}
