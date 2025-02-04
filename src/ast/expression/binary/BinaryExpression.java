package ast.expression.binary;

import ast.expression.Expression;
import ast.node.AbstractLocatable;

public abstract class BinaryExpression extends AbstractLocatable implements Expression {

    private final String operator;
    private final Expression op1;
    private final Expression op2;

    protected BinaryExpression(int line, int col, String operator, Expression op1, Expression op2) {
        super(line, col);
        this.operator = operator;
        this.op1 = op1;
        this.op2 = op2;
    }

    public String getOperator() {
        return operator;
    }

    public Expression getOp1() {
        return op1;
    }

    public Expression getOp2() {
        return op2;
    }
}
