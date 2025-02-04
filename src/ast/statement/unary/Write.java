package ast.statement.unary;

import ast.expression.Expression;

public class Write extends UnaryStatement {
    public Write(int line, int col, Expression expr) {
        super(line, col, expr);
    }
}
