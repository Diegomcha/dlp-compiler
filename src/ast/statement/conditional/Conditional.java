package ast.statement.conditional;

import ast.expression.Expression;
import ast.statement.Statement;

import java.util.ArrayList;
import java.util.List;

public class Conditional extends AbstractConditional {

    private final List<Statement> elseBody;

    public Conditional(int line, int col, Expression condition, List<Statement> body, List<Statement> elseBody) {
        super(line, col, condition, body);
        this.elseBody = new ArrayList<>(elseBody);
    }

    @Override
    public String toString() {
        return "Conditional{" +
                super.toString() +
                ", elseBody=" + elseBody.size() +
                '}';
    }
}
