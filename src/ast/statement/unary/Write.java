package ast.statement.unary;

import ast.expression.Expression;
import semantic.Visitor;

public class Write extends UnaryStatement {

    /**
     * Commodity constructor to create a Write from an Expression.
     * @param expr Expression to create the Write from.
     */
    public Write(Expression expr) {
        this(expr.getLine(), expr.getCol(), expr);
    }

    public Write(int line, int col, Expression expr) {
        super(line, col, expr);
    }

    @Override
    public String toString() {
        return "Write{" + super.toString() + "}";
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }
}
