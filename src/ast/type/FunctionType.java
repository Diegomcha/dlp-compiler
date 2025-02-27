package ast.type;

import ast.definition.VariableDefinition;
import ast.type.builtin.VoidType;

import java.util.ArrayList;
import java.util.List;

public class FunctionType implements Type {

    private final List<VariableDefinition> params;
    private final Type returnType;

    /**
     * Useful for main fn type definition.
     */
    public FunctionType() {
        this(new ArrayList<>(), VoidType.getInstance());
    }

    public FunctionType(List<VariableDefinition> params, Type returnType) {
        this.params = new ArrayList<>(params);
        this.returnType = returnType;
    }

    @Override
    public String toString() {
        return "FunctionType{" +
                "params=" + params.size() +
                ", returnType=" + returnType +
                '}';
    }
}
