package ast.expression.literal;

import ast.expression.AbstractExpression;
import semantic.Visitor;

public class CharLiteral extends AbstractExpression {

    private final char value;

    public CharLiteral(int line, int col, char value) {
        super(line, col);
        this.value = value;
    }

    @Override
    public String toString() {
        return "CharLiteral{" +
                super.toString() +
                ", value=" + value +
                '}';
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }
}
