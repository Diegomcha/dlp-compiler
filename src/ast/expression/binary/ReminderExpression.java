package ast.expression.binary;

import ast.expression.Expression;
import semantic.Visitor;

public class ReminderExpression extends BinaryExpression {

    ReminderExpression(int line, int col, Expression op1, Expression op2) {
        super(line, col, op1, op2);
    }

    @Override
    public String toString() {
        return "ReminderExpression{" + super.toString() + '}';
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }
}
