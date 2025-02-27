package ast.expression.unary;

import ast.expression.Expression;
import ast.node.AbstractLocatable;

public abstract class UnaryExpression extends AbstractLocatable implements Expression {

    private final Expression expr;

    protected UnaryExpression(int line, int col, Expression expr) {
        super(line, col);
        this.expr = expr;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", expr=" + expr;
    }
}
