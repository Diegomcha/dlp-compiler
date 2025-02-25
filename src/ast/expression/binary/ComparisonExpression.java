package ast.expression.binary;

import ast.expression.Expression;

public class ComparisonExpression extends BinaryOperatorExpression {

    public ComparisonExpression(int line, int col, String operator, Expression op1, Expression op2) {
        super(line, col, operator, op1, op2);
    }

    @Override
    public String toString() {
        return "ComparisonExpression{" + super.toString() + "}";
    }
}
