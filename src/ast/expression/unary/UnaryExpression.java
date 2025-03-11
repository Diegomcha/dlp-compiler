package ast.expression.unary;

import ast.expression.AbstractExpression;
import ast.expression.Expression;

public abstract class UnaryExpression extends AbstractExpression {

    private final Expression expr;

    protected UnaryExpression(int line, int col, Expression expr) {
        super(line, col);
        this.expr = expr;
    }

    public Expression getExpr() {
        return expr;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", expr=" + expr;
    }
}
