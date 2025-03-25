package ast.type;

import ast.node.ASTNode;

import java.util.List;

public interface Type extends ASTNode {
    Type squareBrackets(Type idxType);

    Type parenthesis(List<Type> argTypes);

    Type arithmetic(Type type);

    Type compare(Type type);

    Type logicCompare(Type type);

    Type percentage(Type type);

    Type cast(Type type);

    Type dot(String fieldName);

    Type exclamation();

    Type minus();

    void assign(Type type);

    void ret(Type type);

    void mustBeReadable();

    void mustBeWritable();

    void mustBeCondition();

    String typeExpression();
}
