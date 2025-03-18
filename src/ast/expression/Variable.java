package ast.expression;

import ast.definition.Definition;
import semantic.Visitor;

public class Variable extends AbstractExpression {

    private final String name;
    private Definition definition;

    public Variable(int line, int col, String name) {
        super(line, col);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Definition getDefinition() {
        return definition;
    }

    public void setDefinition(Definition definition) {
        this.definition = definition;
    }

    @Override
    public String toString() {
        return "Variable{" +
                super.toString() +
                ", name='" + name + '\'' +
                ", definition=" + definition +
                '}';
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }
}
