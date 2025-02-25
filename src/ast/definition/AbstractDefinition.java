package ast.definition;

import ast.node.AbstractLocatable;
import ast.type.Type;

public abstract class AbstractDefinition extends AbstractLocatable implements Definition {

    private final String name;
    private final Type type;

    protected AbstractDefinition(int line, int col, String name, Type type) {
        super(line, col);
        this.name = name;
        this.type = type;
    }

    @Override
    public final String getName() {
        return name;
    }

    @Override
    public final Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", name='" + name + '\'' +
                ", type=" + type;
    }
}
