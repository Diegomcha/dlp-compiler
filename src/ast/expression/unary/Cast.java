package ast.expression.unary;

import ast.expression.Expression;
import ast.type.Type;

public class Cast extends UnaryExpression {

    private final Type type;

    public Cast(int line, int col, Expression expr, Type type) {
        super(line, col, expr);
        this.type = type;
    }

    public Type getType() {
        return type;
    }
}
