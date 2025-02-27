package ast.type.builtin;

import ast.type.Type;

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
}
