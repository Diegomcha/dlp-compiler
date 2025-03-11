package ast.statement.conditional;

import ast.expression.Expression;
import ast.statement.Statement;
import semantic.Visitor;

import java.util.List;

public class While extends AbstractConditional {
    public While(int line, int col, Expression condition, List<Statement> body) {
        super(line, col, condition, body);
    }

    @Override
    public String toString() {
        return "While{" + super.toString() + "}";
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }
}
