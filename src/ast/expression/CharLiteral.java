package ast.expression;

import ast.node.AbstractLocatable;

public class CharLiteral extends AbstractLocatable implements Expression {

    private final char value;

    public CharLiteral(int line, int col, char value) {
        super(line, col);
        this.value = value;
    }

    public char getValue() {
        return value;
    }
}
