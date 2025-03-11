package ast.expression;

import ast.node.AbstractLocatable;

public abstract class AbstractExpression extends AbstractLocatable implements Expression {

    private boolean lValue;

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
    public String toString() {
        return "lValue=" + lValue;
    }
}
