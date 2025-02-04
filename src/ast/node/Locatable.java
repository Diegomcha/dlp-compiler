package ast.node;

public interface Locatable extends ASTNode {
    int getLine();

    int getCol();
}
