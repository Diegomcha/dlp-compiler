package ast.expression;

import ast.node.AbstractLocatable;

public class Indexing extends AbstractLocatable implements Expression {

    private final Expression element;
    private final Expression index;

    public Indexing(int line, int col, Expression element, Expression index) {
        super(line, col);
        this.element = element;
        this.index = index;
    }

    public Expression getElement() {
        return element;
    }

    public Expression getIndex() {
        return index;
    }
}
