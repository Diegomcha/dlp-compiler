package ast.statement;

import ast.node.AbstractLocatable;
import ast.type.Type;

public abstract class AbstractStatement extends AbstractLocatable implements Statement {

    private Type returnType;

    protected AbstractStatement(int line, int col) {
        super(line, col);
    }

    @Override
    public Type getReturnType() {
        return this.returnType;
    }

    @Override
    public void setReturnType(Type type) {
        this.returnType = type;
    }

    @Override
    public String toString() {
        return super.toString() + ", returnType: " + returnType;
    }
}
