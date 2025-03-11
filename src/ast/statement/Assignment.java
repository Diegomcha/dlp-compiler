package ast.statement;

import ast.expression.Expression;
import ast.node.AbstractLocatable;
import semantic.Visitor;

public class Assignment extends AbstractLocatable implements Statement {

    private final Expression assigned;
    private final Expression value;

    public Assignment(int line, int col, Expression assigned, Expression value) {
        super(line, col);
        this.assigned = assigned;
        this.value = value;
    }

    public Expression getAssigned() {
        return assigned;
    }

    public Expression getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "assigned=" + assigned +
                ", value=" + value +
                '}';
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }
}
