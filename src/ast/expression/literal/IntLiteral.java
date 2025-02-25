package ast.expression.literal;

import ast.expression.Expression;
import ast.node.AbstractLocatable;

public class IntLiteral extends AbstractLocatable implements Expression {

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
}
