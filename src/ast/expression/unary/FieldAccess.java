package ast.expression.unary;

import ast.expression.Expression;
import semantic.Visitor;

public class FieldAccess extends UnaryExpression {

    private final String property;

    public FieldAccess(int line, int col, Expression expr, String property) {
        super(line, col, expr);
        this.property = property;
    }

    @Override
    public String toString() {
        return "FieldAccess{" +
                super.toString() +
                ", property='" + property + '\'' +
                '}';
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }
}
