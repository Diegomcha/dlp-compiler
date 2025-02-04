package ast.expression.unary;

import ast.expression.Expression;
import ast.node.AbstractLocatable;

public abstract class UnaryExpression extends AbstractLocatable implements Expression {

    private final Expression expr;

    protected UnaryExpression(int line, int col, Expression expr) {
        super(line, col);
        this.expr = expr;
    }

    public Expression getExpr() {
        return expr;
    }
}
