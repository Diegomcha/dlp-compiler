package ast.type.builtin;

import ast.type.Type;

public class CharType implements Type {

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
}
