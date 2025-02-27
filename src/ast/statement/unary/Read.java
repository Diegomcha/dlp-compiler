package ast.statement.unary;

import ast.expression.Expression;

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
}
