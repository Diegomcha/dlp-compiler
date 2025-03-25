package ast.type.builtin;

import ast.type.AbstractType;
import ast.type.Type;
import semantic.Visitor;

public class IntType extends AbstractType {

    private static final IntType instance = new IntType();

    private IntType() {
    }

    public static IntType getInstance() {
        return instance;
    }

    @Override
    public String toString() {
        return "IntType";
    }

    @Override
    public String typeExpression() {
        return "int";
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }

    @Override
    public Type arithmetic(Type type) {
        if (type instanceof IntType)
            return this;

        return super.arithmetic(type);
    }

    @Override
    public Type percentage(Type type) {
        if (type instanceof IntType)
            return IntType.getInstance();

        return super.percentage(type);
    }

    @Override
    public Type logicCompare(Type type) {
        if (type instanceof IntType)
            return this;

        return super.logicCompare(type);
    }

    @Override
    public Type exclamation() {
        return this;
    }

    @Override
    public Type compare(Type type) {
        if (type instanceof IntType)
            return this;

        return super.compare(type);
    }

    @Override
    public Type minus() {
        return this;
    }

    @Override
    public Type cast(Type type) {
        return type;
    }

    @Override
    public void assign(Type type) {
        if (type != this) // Comparison by reference since we are using singletons
            super.assign(type);
    }

    @Override
    public void mustBeReadable() {
        // It's readable
    }

    @Override
    public void mustBeWritable() {
        // It's writable
    }

    @Override
    public void mustBeCondition() {
        // It's a condition
    }

    @Override
    public void ret(Type type) {
        if (type != this) // Comparison by reference since we are using singletons
            super.ret(type);
    }
}
