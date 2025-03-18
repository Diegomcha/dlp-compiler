package ast.definition;

import ast.node.AbstractLocatable;
import ast.type.Type;

public abstract class AbstractDefinition<T extends Type> extends AbstractLocatable implements Definition {

    private final String name;
    private final T type;
    private int scope;

    protected AbstractDefinition(int line, int col, String name, T type) {
        super(line, col);
        this.name = name;
        this.type = type;
    }

    @Override
    public final String getName() {
        return name;
    }

    public final T getType() {
        return type;
    }

    @Override
    public final void setScope(int scope) {
        this.scope = scope;
    }

    @Override
    public final int getScope() {
        return this.scope;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", scope=" + scope;
    }
}
