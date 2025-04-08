package ast.expression.literal;

import ast.expression.AbstractExpression;
import semantic.Visitor;

public class RealLiteral extends AbstractExpression {

    private final double value;

    public RealLiteral(int line, int col, double value) {
        super(line, col);
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "RealLiteral{" +
                super.toString() +
                ", value=" + value +
                '}';
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }
}
