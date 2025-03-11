package ast.type.struct;

import ast.type.ErrorType;
import ast.type.Type;
import semantic.Visitor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class StructType implements Type {

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
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }
}
