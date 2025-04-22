package ast.type;

import ast.node.Locatable;
import ast.type.builtin.IntType;
import semantic.Visitor;

public class ArrayType extends AbstractType {

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

    public Type getType() {
        return type;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "ArrayType{" +
                "type=" + type +
                ", size=" + size +
                '}';
    }

    @Override
    public String typeExpression() {
        return "array[" + this.type.typeExpression() + "]";
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }

    @Override
    public Type squareBrackets(Type idxType, Locatable location) {
        if (idxType instanceof IntType)
            return this.type;

        return super.squareBrackets(idxType, location);
    }

    @Override
    public int numberOfBytes() {
        return type.numberOfBytes() * this.size;
    }
}
