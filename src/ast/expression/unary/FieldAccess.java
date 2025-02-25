package ast.expression.unary;

import ast.expression.Expression;

public class FieldAccess extends UnaryExpression {

    private final String property;

    public FieldAccess(int line, int col, Expression expr, String property) {
        super(line, col, expr);
        this.property = property;
    }

    public String getProperty() {
        return property;
    }

    @Override
    public String toString() {
        return "FieldAccess{" +
                super.toString() +
                ", property='" + property + '\'' +
                '}';
    }
}
