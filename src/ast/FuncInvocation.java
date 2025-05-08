package ast;

import ast.expression.AbstractExpression;
import ast.expression.Expression;
import ast.expression.Variable;
import ast.statement.Statement;
import semantic.Visitor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FuncInvocation extends AbstractExpression implements Statement {

    private final Variable fn;
    private final List<Expression> args;

    public FuncInvocation(int line, int col, Variable fn, List<Expression> args) {
        super(line, col);
        this.fn = fn;
        this.args = new ArrayList<>(args);
    }

    public Variable getFn() {
        return fn;
    }

    public List<Expression> getArgs() {
        return Collections.unmodifiableList(args);
    }

    @Override
    public String toString() {
        return "FuncInvocation{" +
                super.toString() +
                ", fn=" + fn +
                ", args=" + args.size() +
                '}';
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }

}
