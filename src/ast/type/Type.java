package ast.type;

import ast.node.ASTNode;
import ast.node.Locatable;
import codegeneration.util.CodeGenerator;

import java.util.List;

public interface Type extends ASTNode {
    Type squareBrackets(Type idxType, Locatable location);

    Type parenthesis(List<Type> argTypes, Locatable location);

    Type arithmetic(Type type, Locatable location);

    Type compare(Type type, Locatable location);

    Type logicCompare(Type type, Locatable location);

    Type percentage(Type type, Locatable location);

    Type cast(Type type, Locatable location);

    Type colon (Type type, Locatable location);

    Type dot(String fieldName, Locatable location);

    Type exclamation(Locatable location);

    Type minus(Locatable location);

    void assign(Type type, Locatable location);

    void ret(Type type, Locatable location);

    void mustBeReadable(Locatable location);

    void mustBeWritable(Locatable location);

    void mustBeCondition(Locatable location);

    String typeExpression();

    int numberOfBytes();

    void convertTo(Type type, CodeGenerator cg);

    Type supertype(Type type);

    String suffix();

    boolean isCompatibleWith(Type type);
}
