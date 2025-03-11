package ast.statement.conditional;

import ast.expression.Expression;
import ast.node.AbstractLocatable;
import ast.statement.Statement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AbstractConditional extends AbstractLocatable implements Statement {

    private final Expression condition;
    private final List<Statement> body;

    protected AbstractConditional(int line, int col, Expression condition, List<Statement> body) {
        super(line, col);
        this.condition = condition;
        this.body = new ArrayList<>(body);
    }

    public Expression getCondition() {
        return condition;
    }

    public List<Statement> getBody() {
        return Collections.unmodifiableList(body);
    }

    @Override
    public String toString() {
        return super.toString() +
                ", condition=" + condition +
                ", body=" + body.size();
    }
}
