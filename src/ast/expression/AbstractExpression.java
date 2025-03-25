package ast.expression;

import ast.node.AbstractLocatable;
import ast.type.Type;

public abstract class AbstractExpression extends AbstractLocatable implements Expression {

    private boolean lValue;
    private Type type;

    protected AbstractExpression(int line, int col) {
        super(line, col);
    }

    @Override
    public boolean getLValue() {
        return lValue;
    }

    @Override
    public void setLValue(boolean lValue) {
        this.lValue = lValue;
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "lValue=" + lValue;
    }
}
