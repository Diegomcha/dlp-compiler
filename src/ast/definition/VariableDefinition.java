package ast.definition;

import ast.type.Type;

public class VariableDefinition extends AbstractDefinition<Type> {
    public VariableDefinition(int line, int col, String name, Type type) {
        super(line, col, name, type);
    }

    @Override
    public String toString() {
        return "VariableDefinition{" + super.toString() + "}";
    }
}
