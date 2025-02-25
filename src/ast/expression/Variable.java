package ast.expression;

import ast.node.AbstractLocatable;

public class Variable extends AbstractLocatable implements Expression {

    private final String name;

    public Variable(int line, int col, String name) {
        super(line, col);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Variable{" +
                super.toString() +
                ", name='" + name + '\'' +
                '}';
    }
}
