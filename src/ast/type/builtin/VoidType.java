package ast.type.builtin;

import ast.type.Type;
import semantic.Visitor;

public class VoidType implements Type {

    private static final VoidType instance = new VoidType();

    private VoidType() {
    }

    public static VoidType getInstance() {
        return instance;
    }

    @Override
    public String toString() {
        return "VoidType";
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }
}
