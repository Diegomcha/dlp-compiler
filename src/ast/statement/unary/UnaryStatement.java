package ast.statement.unary;

import ast.expression.Expression;
import ast.statement.AbstractStatement;

public abstract class UnaryStatement extends AbstractStatement {

    private final Expression expr;

    protected UnaryStatement(int line, int col, Expression expr) {
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
