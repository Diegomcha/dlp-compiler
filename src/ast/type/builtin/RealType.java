package ast.type.builtin;

import ast.type.Type;

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
}
