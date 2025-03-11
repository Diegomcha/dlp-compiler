package ast.expression;

import ast.node.Locatable;

public interface Expression extends Locatable {
    boolean getLValue();

    void setLValue(boolean lValue);
}
