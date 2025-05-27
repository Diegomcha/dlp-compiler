package ast.type;

import ast.definition.VariableDefinition;
import ast.node.Locatable;
import ast.type.builtin.VoidType;
import semantic.Visitor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class FunctionType extends AbstractType {

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

    public List<VariableDefinition> getParams() {
        return Collections.unmodifiableList(params);
    }

    public Type getReturnType() {
        return returnType;
    }

    @Override
    public String toString() {
        return "FunctionType{" +
                "params=" + params.size() +
                ", returnType=" + returnType +
                '}';
    }

    @Override
    public String typeExpression() {
        return "(" + String.join(", ", this.params.stream().map(vd -> vd.getType().typeExpression()).toList())
                + ") -> "
                + this.getReturnType().typeExpression();
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }

    @Override
    public Type parenthesis(List<Type> argTypes, Locatable location) {
        // Find any ErrorType within the arguments
        Optional<ErrorType> argError = argTypes.stream()
                .filter(t -> t instanceof ErrorType)
                .map(t -> (ErrorType) t)
                .findFirst();

        if (argError.isPresent())
            return argError.get();

        if (argTypes.size() != params.size())
            return new ErrorType(location, String.format("Wrong number of parameters: expected '%d', got '%d'", params.size(), argTypes.size()));

        for (int i = 0; i < params.size(); i++) {
            Type expected = params.get(i).getType();
            Type got = argTypes.get(i);
            if (!got.isPromotableTo(expected))
                return new ErrorType(location, String.format("Invalid parameter type: expected '%s' or promotable, got '%s'", expected.typeExpression(), got.typeExpression()));
        }

        return this.returnType;
    }
}
