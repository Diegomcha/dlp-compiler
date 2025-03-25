package ast.type.struct;

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

        // TODO: this should be externalized to be used with the rest of Definition
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
    public Type dot(String fieldName) {
        Optional<StructField> field = fields.stream().filter(f -> f.getName().equals(fieldName)).findFirst();

        if (field.isEmpty())
            return new ErrorType(String.format("Field '%s' is not defined", fieldName));

        return field.get().getType();
    }
}
