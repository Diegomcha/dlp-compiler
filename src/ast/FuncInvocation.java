package ast;

import ast.expression.Expression;
import ast.expression.Variable;
import ast.node.AbstractLocatable;
import ast.statement.Statement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FuncInvocation extends AbstractLocatable implements Expression, Statement {

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
                "fn=" + fn +
                ", args=" + args.size() +
                '}';
    }
}
