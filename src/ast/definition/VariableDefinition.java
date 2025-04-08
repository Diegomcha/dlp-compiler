package ast.definition;

import ast.type.Type;
import semantic.Visitor;

public class VariableDefinition extends AbstractDefinition<Type> {

    public VariableDefinition(int line, int col, String name, Type type) {
        super(line, col, name, type);
    }

    @Override
    public String toString() {
        return "VariableDefinition{" +
                super.toString() +
                '}';
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }
}
