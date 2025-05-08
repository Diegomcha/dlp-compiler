package semantic;

import ast.definition.Definition;
import ast.definition.FunctionDefinition;
import ast.definition.VariableDefinition;
import ast.expression.Variable;
import ast.type.ErrorType;
import symboltable.SymbolTable;

public class IdentificationVisitor extends AbstractTraversal<Void, Void> {

    private final SymbolTable symbolTable = new SymbolTable();

    @Override
    public Void visit(VariableDefinition def, Void param) {
        super.visit(def, param);

        if (!symbolTable.insert(def))
            new ErrorType(def.getLine(), def.getCol(), "Variable '" + def.getName() + "' already defined");

        return null;
    }

    @Override
    public Void visit(Variable var, Void param) {
        super.visit(var, param);

        Definition def = symbolTable.find(var.getName());
        if (def == null)
            var.setType(new ErrorType(var.getLine(), var.getCol(), "Variable '" + var.getName() + "' not defined"));
        else
            var.setDefinition(def);

        return null;
    }

    @Override
    public Void visit(FunctionDefinition fnDef, Void param) {
        if (!symbolTable.insert(fnDef))
            new ErrorType(fnDef.getLine(), fnDef.getCol(), "Function '" + fnDef.getName() + "' already defined");

        // New scope for the function body
        symbolTable.set();

        super.visit(fnDef, param);

        // Close the function scope
        symbolTable.reset();

        return null;
    }

    // NOTE: Since we don't have definitions inside if/while this code is unnecessary.
    //       I leave it here for future reference.

    //    @Override
    //    public Void visit(Conditional conditional, Void param) {
    //        // New scope for the conditional body + condition
    //        symbolTable.set();
    //
    //        conditional.getCondition().accept(this, null);
    //        // NOTE: New scope could be opened here since we don't have assignment as expressions
    //        conditional.getBody().forEach(s -> s.accept(this, null));
    //
    //        // Close the conditional scope & open else scope
    //        symbolTable.reset();
    //        symbolTable.set();
    //
    //        conditional.getElseBody().forEach(s -> s.accept(this, null));
    //
    //        // Close the else scope
    //        symbolTable.reset();
    //
    //        return null;
    //    }

    //    @Override
    //    public Void visit(While whl, Void param) {
    //        // New scope for the while body
    //        symbolTable.set();
    //
    //        super.visit(whl, param);
    //
    //        // Close the while scope
    //        symbolTable.reset();
    //
    //        return null;
    //    }
}
