package ast.definition;

import ast.node.AbstractLocatable;
import ast.type.Type;

import java.util.Objects;

public abstract class AbstractDefinition<T extends Type> extends AbstractLocatable implements Definition {

    private final String name;
    private final T type;

    protected AbstractDefinition(int line, int col, String name, T type) {
        super(line, col);
        this.name = name;
        this.type = type;
    }

    public final String getName() {
        return name;
    }

    public final T getType() {
        return type;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", name='" + name + '\'' +
                ", type=" + type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractDefinition<?> that)) return false;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
