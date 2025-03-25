package ast.type.builtin;

import ast.type.AbstractType;
import ast.type.Type;
import semantic.Visitor;

public class CharType extends AbstractType {

    private static final CharType instance = new CharType();

    private CharType() {
    }

    public static CharType getInstance() {
        return instance;
    }

    @Override
    public String toString() {
        return "CharType";
    }

    @Override
    public String typeExpression() {
        return "char";
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }

    @Override
    public Type arithmetic(Type type) {
        if (type instanceof CharType)
            return IntType.getInstance();

        return super.arithmetic(type);
    }

    @Override
    public Type percentage(Type type) {
        if (type instanceof CharType)
            return IntType.getInstance();

        return super.percentage(type);
    }

    @Override
    public Type compare(Type type) {
        if (type instanceof CharType)
            return IntType.getInstance();

        return super.compare(type);
    }

    @Override
    public Type minus() {
        return IntType.getInstance();
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
