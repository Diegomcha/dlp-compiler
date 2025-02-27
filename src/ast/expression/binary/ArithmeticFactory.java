package ast.expression.binary;

import ast.expression.Expression;

public class ArithmeticFactory {
    public static Expression create(int line, int col, String operator, Expression op1, Expression op2) {
        if ("%".equals(operator))
            return new ReminderExpression(line, col, op1, op2);
        else
            return new ArithmeticExpression(line, col, operator, op1, op2);
    }
}
