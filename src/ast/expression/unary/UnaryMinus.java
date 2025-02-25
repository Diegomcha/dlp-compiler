package ast.expression.unary;

import ast.expression.Expression;

public class UnaryMinus extends UnaryExpression {

    public UnaryMinus(int line, int col, Expression expr) {
        super(line, col, expr);
    }

    @Override
    public String toString() {
        return "UnaryMinus{" + super.toString() + "}";
    }
}
