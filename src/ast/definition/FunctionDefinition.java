package ast.definition;

import ast.statement.Statement;
import ast.type.FunctionType;
import semantic.Visitor;

import java.util.ArrayList;
import java.util.Collections;
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

    public List<VariableDefinition> getDefs() {
        return Collections.unmodifiableList(defs);
    }

    public List<Statement> getStmts() {
        return Collections.unmodifiableList(stmts);
    }

    @Override
    public String toString() {
        return "FunctionDefinition{" +
                super.toString() +
                ", defs=" + defs.size() +
                ", stmts=" + stmts.size() +
                '}';
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }
}
