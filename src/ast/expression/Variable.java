package ast.expression;

import semantic.Visitor;

public class Variable extends AbstractExpression {

    private final String name;

    public Variable(int line, int col, String name) {
        super(line, col);
        this.name = name;
    }

    @Override
    public String toString() {
        return "Variable{" +
                super.toString() +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }
}
