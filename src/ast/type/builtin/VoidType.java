package ast.type.builtin;

import ast.type.Type;

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
}
