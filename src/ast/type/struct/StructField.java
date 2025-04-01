package ast.type.struct;

import ast.definition.AbstractDefinition;
import ast.definition.VariableDefinition;
import ast.type.Type;
import semantic.Visitor;

public class StructField extends AbstractDefinition<Type> {

    private int offset;

    /**
     * Commodity constructor to create a StructField from a VariableDefinition.
     *
     * @param variableDefinition VariableDefinition to create the StructField from.
     */
    public StructField(VariableDefinition variableDefinition) {
        this(variableDefinition.getLine(), variableDefinition.getCol(), variableDefinition.getName(), variableDefinition.getType());
    }

    public StructField(int line, int col, String name, Type type) {
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
        return "StructField{" +
                super.toString() +
                ", offset=" + offset +
                '}';
    }

    public String typeExpression() {
        return this.getType().typeExpression() + " " + this.getName();
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }
}
