package ast.definition;

import ast.expression.Expression;
import ast.type.Type;
import semantic.Visitor;

public class VarInitialization extends AbstractDefinition<Type> {

    private final Expression expr;

    public VarInitialization(int line, int col, String name, Expression expr) {
        super(line, col, name);
        this.expr = expr;
    }

    public Expression getExpr() {
        return expr;
    }

    @Override
    public String toString() {
        return "VarInitialization{" +
                super.toString() +
                ", expr=" + expr +
                '}';
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }
}
