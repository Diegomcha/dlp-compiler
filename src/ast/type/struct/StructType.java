package ast.type.struct;

import ast.type.ErrorType;
import ast.type.Type;

import java.util.ArrayList;
import java.util.List;

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

                if (field1.equals(field2))
                    // Error is tied to the duplicated field
                    new ErrorType(field2.getLine(), field2.getCol(),
                            "Duplicated field '" +
                                    field2.getName() +
                                    "' in struct");
            }
        }
    }

    @Override
    public String toString() {
        return "StructType{" +
                "fields=" + fields.size() +
                '}';
    }
}
