package ast.expression.unary;

import ast.expression.Expression;
import semantic.Visitor;

public class UnaryArithmetic extends UnaryExpression {

    private final String operator;
    private final boolean postfix;

    public UnaryArithmetic(int line, int col, Expression expr, String operator, boolean postfix) {
        super(line, col, expr);
        this.operator = operator;
        this.postfix = postfix;
    }

    public final String getOperator() {
        return operator;
    }

    public final boolean isPostfix() {
        return postfix;
    }

    @Override
    public String toString() {
        return "ArithmeticUnary{" +
                super.toString() +
                ", operator=" + operator +
                ", postfix=" + postfix +
                '}';
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }
}
