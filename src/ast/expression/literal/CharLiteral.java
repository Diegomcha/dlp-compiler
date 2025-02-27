package ast.expression.literal;

import ast.expression.Expression;
import ast.node.AbstractLocatable;

public class CharLiteral extends AbstractLocatable implements Expression {

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
}
