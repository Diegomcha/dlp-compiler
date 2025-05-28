package ast.type;

import ast.node.Locatable;
import codegeneration.util.CodeGenerator;

import java.util.List;

public abstract class AbstractType implements Type {

    @Override
    public Type squareBrackets(Type type, Locatable location) {
        if (type instanceof ErrorType)
            return type;
        return new ErrorType(location, String.format("Type '%s' cannot be indexed", this.typeExpression()));
    }

    @Override
    public Type parenthesis(List<Type> argTypes, Locatable location) {
        return new ErrorType(location, String.format("Type '%s' is not callable", this.typeExpression()));
    }

    @Override
    public Type arithmetic(Type type, Locatable location) {
        if (type instanceof ErrorType)
            return type;

        return new ErrorType(location, String.format("Cannot perform arithmetic operation between types '%s' and '%s'", this.typeExpression(), type.typeExpression()));
    }

    @Override
    public Type compare(Type type, Locatable location) {
        if (type instanceof ErrorType)
            return type;
        return new ErrorType(location, String.format("Cannot perform comparison between types '%s' and '%s'", this.typeExpression(), type.typeExpression()));
    }

    @Override
    public Type logicCompare(Type type, Locatable location) {
        if (type instanceof ErrorType)
            return type;
        return new ErrorType(location, String.format("Cannot perform logic comparison between types '%s' and '%s'", this.typeExpression(), type.typeExpression()));
    }

    @Override
    public Type percentage(Type type, Locatable location) {
        if (type instanceof ErrorType)
            return type;
        return new ErrorType(location, String.format("Cannot perform reminder between types '%s' and '%s'", this.typeExpression(), type.typeExpression()));
    }

    @Override
    public Type cast(Type type, Locatable location) {
        if (type instanceof ErrorType)
            return type;
        return new ErrorType(location, String.format("Cannot cast type '%s' to type '%s'", this.typeExpression(), type.typeExpression()));
    }

    @Override
    public Type dot(String fieldName, Locatable location) {
        return new ErrorType(location, String.format("Cannot access field '%s' from type '%s'", fieldName, this.typeExpression()));
    }

    @Override
    public Type exclamation(Locatable location) {
        return new ErrorType(location, String.format("Cannot negate type '%s'", this.typeExpression()));
    }

    @Override
    public Type minus(Locatable location) {
        return new ErrorType(location, String.format("Cannot invert type '%s'", this.typeExpression()));
    }

    @Override
    public Type increment(Locatable location) {
        return new ErrorType(location, String.format("Cannot increment/decrement type '%s'", this.typeExpression()));
    }

    @Override
    public void assign(Type type, Locatable location) {
        new ErrorType(location, String.format("Type '%s' cannot be assigned to type '%s'", type.typeExpression(), this.typeExpression()));
    }

    @Override
    public void ret(Type type, Locatable location) {
        new ErrorType(location, String.format("Expected return type '%s', got '%s'", type.typeExpression(), this));
    }

    @Override
    public void mustBeReadable(Locatable location) {
        new ErrorType(location, String.format("Cannot read to type '%s'", this.typeExpression()));
    }

    @Override
    public void mustBeWritable(Locatable location) {
        new ErrorType(location, String.format("Cannot write to type '%s'", this.typeExpression()));
    }

    @Override
    public void mustBeCondition(Locatable location) {
        new ErrorType(location, String.format("Type '%s' cannot be used as a condition", this.typeExpression()));
    }

    @Override
    public int numberOfBytes() {
        throw new UnsupportedOperationException("numberOfBytes not implemented for type :" + this.typeExpression());
    }

    @Override
    public void convertTo(Type type, CodeGenerator cg) {
        throw new UnsupportedOperationException("convertTo not implemented for types :" + this.typeExpression() + ", " + type.typeExpression());
    }

    @Override
    public Type supertype(Type type) {
        throw new UnsupportedOperationException("supertype not implemented for types :" + this.typeExpression() + ", " + type.typeExpression());
    }

    @Override
    public String suffix() {
        throw new UnsupportedOperationException("suffix not implemented for type :" + this.typeExpression());
    }
}
