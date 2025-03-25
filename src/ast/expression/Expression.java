package ast.expression;

import ast.node.Locatable;
import ast.type.Type;

public interface Expression extends Locatable {
    boolean getLValue();

    void setLValue(boolean lValue);

    Type getType();

    void setType(Type type);
}
