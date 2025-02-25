package ast.definition;

import ast.statement.Statement;
import ast.type.Type;

import java.util.ArrayList;
import java.util.List;

public class FunctionDefinition extends AbstractDefinition {

    private final List<VariableDefinition> defs;
    private final List<Statement> stmts;

    public FunctionDefinition(int line, int col, String name, Type type, List<VariableDefinition> defs, List<Statement> stmts) {
        super(line, col, name, type);
        this.defs = new ArrayList<>(defs);
        this.stmts = new ArrayList<>(stmts);
    }

    public List<VariableDefinition> getDefs() {
        return defs;
    }

    public List<Statement> getStmts() {
        return stmts;
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
