package ast.expression.unary;

import ast.expression.Expression;

public class Cast extends UnaryExpression {

    public Cast(int line, int col, Expression expr) {
        super(line, col, expr);
    }

}
