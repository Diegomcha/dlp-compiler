package ast.expression.ternary;

import ast.expression.AbstractExpression;
import ast.expression.Expression;
import semantic.Visitor;

public class TernaryExpression extends AbstractExpression {

    private final Expression condition;
    private final Expression trueExpr;
    private final Expression falseExpr;

    public TernaryExpression(int line, int col, Expression condition, Expression trueExpr, Expression falseExpr) {
        super(line, col);
        this.condition = condition;
        this.trueExpr = trueExpr;
        this.falseExpr = falseExpr;
    }

    public Expression getCondition() {
        return condition;
    }

    public Expression getTrueExpr() {
        return trueExpr;
    }

    public Expression getFalseExpr() {
        return falseExpr;
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }
}
