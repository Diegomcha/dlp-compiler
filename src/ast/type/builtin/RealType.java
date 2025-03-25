package ast.type.builtin;

import ast.type.AbstractType;
import ast.type.Type;
import semantic.Visitor;

public class RealType extends AbstractType {

    private static final RealType instance = new RealType();

    private RealType() {
    }

    public static RealType getInstance() {
        return instance;
    }

    @Override
    public String toString() {
        return "RealType";
    }

    @Override
    public String typeExpression() {
        return "double";
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }

    @Override
    public Type arithmetic(Type type) {
        if (type instanceof RealType)
            return this;

        return super.arithmetic(type);
    }

    @Override
    public Type compare(Type type) {
        if (type instanceof RealType)
            return IntType.getInstance();

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
    public void ret(Type type) {
        if (type != this) // Comparison by reference since we are using singletons
            super.ret(type);
    }
}
