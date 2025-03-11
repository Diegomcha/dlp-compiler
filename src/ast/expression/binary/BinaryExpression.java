package ast.expression.binary;

import ast.expression.AbstractExpression;
import ast.expression.Expression;

public abstract class BinaryExpression extends AbstractExpression {

    private final Expression op1;
    private final Expression op2;

    protected BinaryExpression(int line, int col, Expression op1, Expression op2) {
        super(line, col);
        this.op1 = op1;
        this.op2 = op2;
    }

    public Expression getOp1() {
        return op1;
    }

    public Expression getOp2() {
        return op2;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", op1=" + op1 +
                ", op2=" + op2;
    }
}
