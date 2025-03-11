package ast.statement.unary;

import ast.expression.Expression;
import semantic.Visitor;

public class Read extends UnaryStatement {

    /**
     * Commodity constructor to create a Read from an Expression.
     * @param expr Expression to create the Read from.
     */
    public Read(Expression expr) {
        this(expr.getLine(), expr.getCol(), expr);
    }

    public Read(int line, int col, Expression expr) {
        super(line, col, expr);
    }

    @Override
    public String toString() {
        return "Read{" + super.toString() + "}";
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }
}
