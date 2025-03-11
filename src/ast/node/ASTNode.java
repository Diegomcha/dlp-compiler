package ast.node;

import semantic.Visitor;

public interface ASTNode {

    <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param);
}
