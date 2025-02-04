package ast.expression.binary;

import ast.expression.Expression;

public class IndexingExpression extends BinaryExpression {

    public IndexingExpression(int line, int col, String operator, Expression op1, Expression op2) {
        super(line, col, operator, op1, op2);
    }
}
