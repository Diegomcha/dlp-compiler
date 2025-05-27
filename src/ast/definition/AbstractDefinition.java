package ast.definition;

import ast.node.AbstractLocatable;
import ast.type.Type;

public abstract class AbstractDefinition<T extends Type> extends AbstractLocatable implements Definition {

    private final String name;
    private final T type;
    private int scope;
    private int offset;

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
    public final int getOffset() {
        return offset;
    }

    @Override
    public final void setOffset(int offset) {
        this.offset = offset;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", scope=" + scope +
                ", offset=" + offset;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof AbstractDefinition<?> that)) return false;

        return name.equals(that.name) && type.equals(that.type);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }
}
