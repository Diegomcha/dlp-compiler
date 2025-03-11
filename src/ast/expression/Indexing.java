package ast.expression;

import semantic.Visitor;

public class Indexing extends AbstractExpression {

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

    @Override
    public String toString() {
        return "Indexing{" +
                super.toString() +
                ", element=" + element +
                ", index=" + index +
                '}';
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }
}
