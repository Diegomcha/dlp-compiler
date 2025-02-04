package ast.expression.binary;

import ast.expression.Expression;

public class LogicalExpression extends BinaryOperatorExpression {

    public LogicalExpression(int line, int col, String operator, Expression op1, Expression op2) {
        super(line, col, operator, op1, op2);
    }
}
