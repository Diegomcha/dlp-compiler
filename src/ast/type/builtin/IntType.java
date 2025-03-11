package ast.type.builtin;

import ast.type.Type;
import semantic.Visitor;

public class IntType implements Type {

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
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }
}
