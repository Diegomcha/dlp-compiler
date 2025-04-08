package ast.type.builtin;

import ast.node.Locatable;
import ast.type.AbstractType;
import ast.type.Type;
import codegeneration.util.CodeGenerator;
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
    public Type arithmetic(Type type, Locatable location) {
        if (type instanceof RealType)
            return this;

        return super.arithmetic(type, location);
    }

    @Override
    public Type compare(Type type, Locatable location) {
        if (type instanceof RealType)
            return IntType.getInstance();

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
    public void ret(Type type, Locatable location) {
        if (type != this) // Comparison by reference since we are using singletons
            super.ret(type, location);
    }

    @Override
    public int numberOfBytes() {
        return 4;
    }

    @Override
    public void convertTo(Type type, CodeGenerator cg) {
        switch (type) {
            case CharType _t:
                cg.f2i();
                cg.i2b();
                break;
            case IntType _t:
                cg.f2i();
                break;
            case RealType _t:
                // no conversion needed
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
            case IntType _t -> this;
            case RealType _t -> this;

            // error
            default -> super.supertype(type);
        };
    }

    @Override
    public String suffix() {
        return "f";
    }
}
