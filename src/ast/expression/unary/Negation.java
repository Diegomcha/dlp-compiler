package ast.expression.unary;

import ast.expression.Expression;

public class Negation extends UnaryExpression {

    public Negation(int line, int col, Expression expr) {
        super(line, col, expr);
    }

}
