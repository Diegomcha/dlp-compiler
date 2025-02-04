package ast.statement.conditional;

import ast.expression.Expression;
import ast.statement.Statement;

import java.util.List;

public class While extends AbstractConditional {
    public While(int line, int col, Expression condition, List<Statement> body) {
        super(line, col, condition, body);
    }
}
