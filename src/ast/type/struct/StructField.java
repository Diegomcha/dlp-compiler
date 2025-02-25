package ast.type.struct;

import ast.node.ASTNode;
import ast.type.Type;

public class StructField implements ASTNode {

    private final String name;
    private final Type type;

    public StructField(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return "StructField{" +
                "name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}
