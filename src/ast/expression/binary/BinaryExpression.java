package ast.expression.binary;

import ast.expression.Expression;
import ast.node.AbstractLocatable;

public abstract class BinaryExpression extends AbstractLocatable implements Expression {

    private final Expression op1;
    private final Expression op2;

    protected BinaryExpression(int line, int col, Expression op1, Expression op2) {
        super(line, col);
        this.op1 = op1;
        this.op2 = op2;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", op1=" + op1 +
                ", op2=" + op2;
    }
}
