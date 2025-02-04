package ast.definition;

import ast.node.Locatable;
import ast.type.Type;

public interface Definition extends Locatable {
    String getName();

    Type getType();
}
