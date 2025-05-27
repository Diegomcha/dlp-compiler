package ast.type.builtin;

import ast.node.Locatable;
import ast.type.AbstractType;
import ast.type.Type;
import codegeneration.util.CodeGenerator;
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
    public Type arithmetic(Type type, Locatable location) {
        if (type instanceof CharType)
            return IntType.getInstance();

        return super.arithmetic(type, location);
    }

    @Override
    public Type percentage(Type type, Locatable location) {
        if (type instanceof CharType)
            return IntType.getInstance();

        return super.percentage(type, location);
    }

    @Override
    public Type compare(Type type, Locatable location) {
        if (type instanceof CharType)
            return IntType.getInstance();

        return super.compare(type, location);
    }

    @Override
    public Type minus(Locatable location) {
        return IntType.getInstance();
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
    public void mustBeAssignable(Locatable location) {
        // It's assignable
    }

    @Override
    public void ret(Type type, Locatable location) {
        if (type != this) // Comparison by reference since we are using singletons
            super.ret(type, location);
    }

    @Override
    public int numberOfBytes() {
        return 1;
    }

    @Override
    public void convertTo(Type type, CodeGenerator cg) {
        switch (type) {
            case CharType _t:
                // no conversion needed
                break;
            case IntType _t:
                cg.b2i();
                break;
            case RealType _t:
                cg.b2i();
                cg.i2f();
                break;

            default:
                // error
                super.convertTo(type, cg);
        }
    }

    @Override
    public Type supertype(Type type) {
        return switch (type) {
            case CharType _t -> this;
            case IntType t -> t;
            case RealType t -> t;

            // error
            default -> super.supertype(type);
        };
    }

    @Override
    public String suffix() {
        return "b";
    }
}
