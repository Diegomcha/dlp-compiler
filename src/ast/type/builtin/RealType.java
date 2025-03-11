package ast.type.builtin;

import ast.type.Type;
import semantic.Visitor;

public class RealType implements Type {

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
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }
}
