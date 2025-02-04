package ast.expression.binary;

import ast.expression.Expression;

public class ComparationExpression extends BinaryExpression {

    public ComparationExpression(int line, int col, String operator, Expression op1, Expression op2) {
        super(line, col, operator, op1, op2);
    }
}
