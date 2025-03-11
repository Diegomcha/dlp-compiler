package ast.type.builtin;

import ast.type.Type;
import semantic.Visitor;

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

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }
}
