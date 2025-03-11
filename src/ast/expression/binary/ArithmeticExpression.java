package ast.expression.binary;

import ast.expression.Expression;
import semantic.Visitor;

public class ArithmeticExpression extends BinaryOperatorExpression {

    ArithmeticExpression(int line, int col, String operator, Expression op1, Expression op2) {
        super(line, col, operator, op1, op2);
    }

    @Override
    public String toString() {
        return "ArithmeticExpression{" + super.toString() + "}";
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }
}
