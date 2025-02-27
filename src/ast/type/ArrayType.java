package ast.type;

public class ArrayType implements Type {

    private Type type;
    private final int size;

    public static ArrayType create(Type type, int size) {
        if (type instanceof ArrayType arrayType) {
            arrayType.type = new ArrayType(arrayType.type, size);
            return arrayType;
        } else return new ArrayType(type, size);
    }

    private ArrayType(Type type, int size) {
        this.type = type;
        this.size = size;
    }

    @Override
    public String toString() {
        return "ArrayType{" +
                "type=" + type +
                ", size=" + size +
                '}';
    }
}
