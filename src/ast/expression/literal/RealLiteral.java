package ast.expression.literal;

import ast.expression.Expression;
import ast.node.AbstractLocatable;

public class RealLiteral extends AbstractLocatable implements Expression {

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
}
