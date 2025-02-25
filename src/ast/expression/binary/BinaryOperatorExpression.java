package ast.expression.binary;

import ast.expression.Expression;
import ast.node.AbstractLocatable;

public abstract class BinaryOperatorExpression extends AbstractLocatable implements Expression {

    private final String operator;
    private final Expression op1;
    private final Expression op2;

    protected BinaryOperatorExpression(int line, int col, String operator, Expression op1, Expression op2) {
        super(line, col);
        this.operator = operator;
        this.op1 = op1;
        this.op2 = op2;
    }

    public final String getOperator() {
        return operator;
    }

    public final Expression getOp1() {
        return op1;
    }

    public final Expression getOp2() {
        return op2;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", operator='" + operator + '\'' +
                ", op1=" + op1 +
                ", op2=" + op2;
    }
}
