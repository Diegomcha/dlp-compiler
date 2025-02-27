package ast.type.struct;

import ast.node.AbstractLocatable;
import ast.type.Type;

public class StructField extends AbstractLocatable {

    private final String name;
    private final Type type;

    public StructField(int line, int col, String name, Type type) {
        super(line, col);
        this.name = name;
        this.type = type;
    }

    @Override
    public String toString() {
        return "StructField{" +
                "name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}
