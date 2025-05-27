package ast.type.struct;

import ast.node.Locatable;
import ast.type.AbstractType;
import ast.type.ErrorType;
import ast.type.Type;
import semantic.Visitor;

import java.util.*;

public class StructType extends AbstractType {

    private final List<StructField> fields;

    public StructType(List<StructField> fields) {
        assert (!fields.isEmpty());
        this.fields = new ArrayList<>(fields);

        // Find duplicated fields in the struct
        for (int i = 0; i < this.fields.size(); i++) {
            StructField field1 = this.fields.get(i);
            for (int j = i + 1; j < this.fields.size(); j++) {
                StructField field2 = this.fields.get(j);

                if (Objects.equals(field1.getName(), field2.getName()))
                    // Error is tied to the duplicated field
                    new ErrorType(field2.getLine(), field2.getCol(),
                            "Duplicated field '" +
                                    field2.getName() +
                                    "' in struct");
            }
        }
    }

    public List<StructField> getFields() {
        return Collections.unmodifiableList(fields);
    }

    public StructField findField(String name) {
        return fields.stream().filter(f -> f.getName().equals(name)).findFirst().orElseThrow();
    }

    @Override
    public String toString() {
        return "StructType{" +
                "fields=" + fields.size() +
                '}';
    }

    @Override
    public String typeExpression() {
        return "struct {" + String.join(", ", fields.stream().map(StructField::typeExpression).toList()) + "}";
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }

    @Override
    public Type dot(String fieldName, Locatable location) {
        Optional<StructField> field = fields.stream().filter(f -> f.getName().equals(fieldName)).findFirst();

        if (field.isEmpty())
            return new ErrorType(location, String.format("Field '%s' is not defined", fieldName));

        return field.get().getType();
    }

    @Override
    public void assign(Type type, Locatable location) {
        if (!this.equals(type))
            super.assign(type, location);
    }

    @Override
    public int numberOfBytes() {
        return fields.stream().mapToInt(f -> f.getType().numberOfBytes()).sum();
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof StructType that)) return false;

        return fields.equals(that.fields);
    }

    @Override
    public int hashCode() {
        return fields.hashCode();
    }
}
