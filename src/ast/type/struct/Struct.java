package ast.type.struct;

import ast.type.Type;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Struct implements Type {

    private final List<StructField> fields;

    public Struct(List<StructField> fields) {
        // TODO: Check fields is not empty
        this.fields = new ArrayList<>(fields);
    }

    public List<StructField> getFields() {
        return Collections.unmodifiableList(fields);
    }
}
