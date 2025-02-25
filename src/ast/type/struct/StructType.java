package ast.type.struct;

import ast.type.Type;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StructType implements Type {

    private final List<StructField> fields;

    public StructType(List<StructField> fields) {
        // TODO: Check fields is not empty
        this.fields = new ArrayList<>(fields);
    }

    public List<StructField> getFields() {
        return Collections.unmodifiableList(fields);
    }
}
