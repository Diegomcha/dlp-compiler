package ast.expression.unary;

import ast.expression.Expression;
import ast.type.Type;
import semantic.Visitor;

public class Cast extends UnaryExpression {

    private final Type type;

    public Cast(int line, int col, Expression expr, Type type) {
        super(line, col, expr);
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Cast{" +
                super.toString() +
                ", type=" + type +
                '}';
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }
}
