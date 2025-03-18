package ast.definition;

import ast.node.Locatable;

public interface Definition extends Locatable {

    int getScope();

    void setScope(int scope);

    String getName();
}
