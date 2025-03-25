package ast.type;

import java.util.List;

public abstract class AbstractType implements Type {

    @Override
    public Type squareBrackets(Type type) {
        if (type instanceof ErrorType)
            return type;
        return new ErrorType(String.format("Type '%s' cannot be indexed", this.typeExpression()));
    }

    @Override
    public Type parenthesis(List<Type> argTypes) {
        return new ErrorType(String.format("Type '%s' is not callable", this.typeExpression()));
    }

    @Override
    public Type arithmetic(Type type) {
        if (type instanceof ErrorType)
            return type;

        return new ErrorType(String.format("Cannot perform arithmetic operation between types '%s' and '%s'", this.typeExpression(), type.typeExpression()));
    }

    @Override
    public Type compare(Type type) {
        if (type instanceof ErrorType)
            return type;
        return new ErrorType(String.format("Cannot perform comparison between types '%s' and '%s'", this.typeExpression(), type.typeExpression()));
    }

    @Override
    public Type logicCompare(Type type) {
        if (type instanceof ErrorType)
            return type;
        return new ErrorType(String.format("Cannot perform logic comparison between types '%s' and '%s'", this.typeExpression(), type.typeExpression()));
    }

    @Override
    public Type percentage(Type type) {
        if (type instanceof ErrorType)
            return type;
        return new ErrorType(String.format("Cannot perform reminder between types '%s' and '%s'", this.typeExpression(), type.typeExpression()));
    }

    @Override
    public Type cast(Type type) {
        if (type instanceof ErrorType)
            return type;
        return new ErrorType(String.format("Cannot cast type '%s' to type '%s'", this.typeExpression(), type.typeExpression()));
    }

    @Override
    public Type dot(String fieldName) {
        return new ErrorType(String.format("Cannot access field '%s' from type '%s'", fieldName, this.typeExpression()));
    }

    @Override
    public Type exclamation() {
        return new ErrorType(String.format("Cannot negate type '%s'", this.typeExpression()));
    }

    @Override
    public Type minus() {
        return new ErrorType(String.format("Cannot invert type '%s'", this.typeExpression()));
    }

    @Override
    public void assign(Type type) {
        new ErrorType(String.format("Type '%s' cannot be assigned to type '%s'", type.typeExpression(), this.typeExpression()));
    }

    @Override
    public void ret(Type type) {
        new ErrorType(String.format("Expected return type '%s', got '%s'", type.typeExpression(), this));
    }

    @Override
    public void mustBeReadable() {
        new ErrorType(String.format("Cannot read to type '%s'", this.typeExpression()));
    }

    @Override
    public void mustBeWritable() {
        new ErrorType(String.format("Cannot write to type '%s'", this.typeExpression()));
    }

    @Override
    public void mustBeCondition() {
        new ErrorType(String.format("Type '%s' cannot be used as a condition", this.typeExpression()));
    }

}
