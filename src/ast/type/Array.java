package ast.type;

public class Array implements Type {

    private final Type type;
    private final int size;

    public Array(Type type, int size) {
        this.type = type;
        this.size = size;
    }

    public Type getType() {
        return type;
    }

    public int getSize() {
        return size;
    }
}
