package ast.statement.unary;

import ast.expression.Expression;

public class Return extends UnaryStatement {
    public Return(int line, int col, Expression expr) {
        super(line, col, expr);
    }

    @Override
    public String toString() {
        return "Return{" + super.toString() + "}";
    }
}
