package ast.statement.unary;

import ast.expression.Expression;
import ast.node.AbstractLocatable;
import ast.statement.Statement;

public abstract class UnaryStatement extends AbstractLocatable implements Statement {

    private final Expression expr;

    protected UnaryStatement(int line, int col, Expression expr) {
        super(line, col);
        this.expr = expr;
    }

    public final Expression getExpr() {
        return expr;
    }
}
