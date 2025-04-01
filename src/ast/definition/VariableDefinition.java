package ast.definition;

import ast.type.Type;
import semantic.Visitor;

public class VariableDefinition extends AbstractDefinition<Type> {

    private int offset;

    public VariableDefinition(int line, int col, String name, Type type) {
        super(line, col, name, type);
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    @Override
    public String toString() {
        return "VariableDefinition{" +
                super.toString() +
                ", offset=" + offset +
                '}';
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }
}
