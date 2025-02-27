package ast.expression.binary;

import ast.expression.Expression;

public class ReminderExpression extends BinaryExpression {

    ReminderExpression(int line, int col, Expression op1, Expression op2) {
        super(line, col, op1, op2);
    }

    @Override
    public String toString() {
        return "ReminderExpression{" + super.toString() + '}';
    }
}
