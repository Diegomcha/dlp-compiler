package ast.expression.binary;

import ast.expression.Expression;

public abstract class BinaryOperatorExpression extends BinaryExpression {

    private final String operator;

    protected BinaryOperatorExpression(int line, int col, String operator, Expression op1, Expression op2) {
        super(line, col, op1, op2);
        this.operator = operator;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", operator='" + operator + '\'';
    }
}
