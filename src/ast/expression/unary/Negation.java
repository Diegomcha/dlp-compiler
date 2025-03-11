package ast.expression.unary;

import ast.expression.Expression;
import semantic.Visitor;

public class Negation extends UnaryExpression {

    public Negation(int line, int col, Expression expr) {
        super(line, col, expr);
    }

    @Override
    public String toString() {
        return "Negation{" + super.toString() + "}";
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }
}
