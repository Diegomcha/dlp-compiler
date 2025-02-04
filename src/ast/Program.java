package ast;

import ast.definition.Definition;
import ast.node.ASTNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Program implements ASTNode {

    private final List<Definition> sentences;

    public Program(List<Definition> sentences) {
        // TODO: Check sentences is not empty
        this.sentences = new ArrayList<>(sentences);
    }

    public List<Definition> getSentences() {
        return Collections.unmodifiableList(sentences);
    }
}
