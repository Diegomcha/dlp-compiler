package ast.type.struct;

import ast.definition.AbstractDefinition;
import ast.definition.VariableDefinition;
import ast.type.Type;

public class StructField extends AbstractDefinition<Type> {

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

    @Override
    public String toString() {
        return "StructField{" + super.toString() + '}';
    }
}
