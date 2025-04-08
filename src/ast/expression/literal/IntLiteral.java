package ast.expression.literal;

import ast.expression.AbstractExpression;
import semantic.Visitor;

public class IntLiteral extends AbstractExpression {

    private final int value;

    public IntLiteral(int line, int col, int value) {
        super(line, col);
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "IntLiteral{" +
                super.toString() +
                ", value=" + value +
                '}';
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }
}
