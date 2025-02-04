package ast.statement.conditional;

import ast.expression.Expression;
import ast.statement.Statement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Conditional extends AbstractConditional {

    private final List<Statement> elseBody;

    public Conditional(int line, int col, Expression condition, List<Statement> body, List<Statement> elseBody) {
        super(line, col, condition, body);
        this.elseBody = new ArrayList<>(elseBody);
    }

    public List<Statement> getElseBody() {
        return Collections.unmodifiableList(elseBody);
    }
}
