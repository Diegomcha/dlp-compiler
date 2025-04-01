package ast.type.builtin;

import ast.node.Locatable;
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
    public Type arithmetic(Type type, Locatable location) {
        if (type instanceof IntType)
            return this;

        return super.arithmetic(type, location);
    }

    @Override
    public Type percentage(Type type, Locatable location) {
        if (type instanceof IntType)
            return IntType.getInstance();

        return super.percentage(type, location);
    }

    @Override
    public Type logicCompare(Type type, Locatable location) {
        if (type instanceof IntType)
            return this;

        return super.logicCompare(type, location);
    }

    @Override
    public Type exclamation(Locatable location) {
        return this;
    }

    @Override
    public Type compare(Type type, Locatable location) {
        if (type instanceof IntType)
            return this;

        return super.compare(type, location);
    }

    @Override
    public Type minus(Locatable location) {
        return this;
    }

    @Override
    public Type cast(Type type, Locatable location) {
        return type;
    }

    @Override
    public void assign(Type type, Locatable location) {
        if (type != this) // Comparison by reference since we are using singletons
            super.assign(type, location);
    }

    @Override
    public void mustBeReadable(Locatable location) {
        // It's readable
    }

    @Override
    public void mustBeWritable(Locatable location) {
        // It's writable
    }

    @Override
    public void mustBeCondition(Locatable location) {
        // It's a condition
    }

    @Override
    public void ret(Type type, Locatable location) {
        if (type != this) // Comparison by reference since we are using singletons
            super.ret(type, location);
    }

    @Override
    public int numberOfBytes() {
        return 2;
    }
}
