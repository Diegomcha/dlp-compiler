package ast.definition;

import ast.statement.Statement;
import ast.type.FunctionType;

import java.util.ArrayList;
import java.util.List;

public class FunctionDefinition extends AbstractDefinition<FunctionType> {

    private final List<VariableDefinition> defs;
    private final List<Statement> stmts;

    // NOTE: Here name should be a String not Variable because functions cannot be used as expressions.

    public FunctionDefinition(int line, int col, String name, FunctionType type, List<VariableDefinition> defs, List<Statement> stmts) {
        super(line, col, name, type);
        this.defs = new ArrayList<>(defs);
        this.stmts = new ArrayList<>(stmts);
    }


    @Override
    public String toString() {
        return "FunctionDefinition{" +
                super.toString() +
                ", defs=" + defs.size() +
                ", stmts=" + stmts.size() +
                '}';
    }
}
