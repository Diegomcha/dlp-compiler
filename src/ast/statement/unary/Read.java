package ast.statement.unary;

import ast.expression.Expression;

public class Read extends UnaryStatement {
    public Read(int line, int col, Expression expr) {
        super(line, col, expr);
    }
}
