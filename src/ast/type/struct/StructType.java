package ast.type.struct;

import ast.type.Type;

import java.util.ArrayList;
import java.util.List;

public class StructType implements Type {

    private final List<StructField> fields;

    public StructType(List<StructField> fields) {
        assert (!fields.isEmpty());
        this.fields = new ArrayList<>(fields);
    }

    @Override
    public String toString() {
        return "StructType{" +
                "fields=" + fields.size() +
                '}';
    }
}
