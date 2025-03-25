package ast.definition;

import ast.node.Locatable;
import ast.type.Type;

public interface Definition extends Locatable {

    int getScope();

    void setScope(int scope);

    String getName();

    Type getType();
}
