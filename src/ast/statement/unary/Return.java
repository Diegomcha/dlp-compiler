package ast.statement.unary;

import ast.expression.Expression;
import semantic.Visitor;

public class Return extends UnaryStatement {
    public Return(int line, int col, Expression expr) {
        super(line, col, expr);
    }

    @Override
    public String toString() {
        return "Return{" + super.toString() + "}";
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }
}
