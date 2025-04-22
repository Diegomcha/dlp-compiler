package codegeneration;

import ast.Program;
import ast.definition.FunctionDefinition;
import ast.definition.VariableDefinition;
import ast.statement.Assignment;
import ast.statement.conditional.Conditional;
import ast.statement.conditional.While;
import ast.statement.unary.Read;
import ast.statement.unary.Write;
import ast.type.builtin.VoidType;
import codegeneration.util.AbstractCGVisitor;
import codegeneration.util.CodeGenerator;

/*
CODE TEMPLATES: execute

execute[[Read: statement -> expression]] =
    address[[expression]]
    <in> expression.type.suffix
    <store> expression.type.suffix()

execute[[Write: statement -> expression]] =
    value[[expression]]
    <out> expression.type.suffix()

execute[[Assignment: statement -> expression1 expression2]] =
    address[[expression1]]
    value[[expression2]]
    <store> expression1.type.suffix()

execute[[VariableDefinition: definition -> type ID]] =
    <' * > definition.type.typeExpression() < > ID < (offset> definition.offset <)>

execute[[FunctionDefinition: definition -> ID fnType vardefinition* statement*]] =
    ID <:>
    <' * Parameters>
    fnType.params.forEach(def -> execute[[def]])
    <' * Local variables>
    vardefinition*.forEach(def -> execute[[def]])
    <enter > vardefinition*.map(def -> def.type.numberOfBytes()).sum()
    statement*.forEach(st -> execute[[st]])
    if (fnType.returnType == VoidType.getInstance())
        <ret 0, > vardefinition*.map(def -> def.type.numberOfBytes()).sum() <, > fnType.params.map(def -> def.type.numberOfBytes()).sum()

execute[[Program: program -> definition*]] =
    <' * Global variables>
    definition*.filter(def -> def instanceof VariableDefinition).forEach(def -> execute[[def]])
    <call main>
    <halt>
    definition*.filter(def -> def instanceof FunctionDefinition).forEach(def -> execute[[def]])

execute[[While: statement -> expression statement*]] =
    String condLabel = cg.nextLabel(),
           exitLabel = cg.nextLabel();

    condLabel <:>
    value[[expression]]
    <jz > exitLabel
    statement*.forEach(st -> execute[[st]])
    <jmp > condLabel
    exitLabel <:>

execute[[Conditional: statement -> expression statementB* statementE*]] =
    String elseLabel = cg.nextLabel(),
           exitLabel = cg.nextLabel();

    value[[expression]]
    <jz > elseLabel
    statementB*.forEach(st -> execute[[st]]);
    <jmp > exitLabel
    elseLabel <:>
    statementE*.forEach(st -> execute[[st]]);
    exitLabel <:>

 */


public class ExecuteCGVisitor extends AbstractCGVisitor<Void, Void> {

    public ExecuteCGVisitor(CodeGenerator cg) {
        super(cg);
    }

    @Override
    public Void visit(Read read, Void param) {
        this.cg.pragmaLine(read.getLine());
        this.cg.comment("- Read:");

        read.getExpr().accept(this.cg.addrVisitor, null);
        this.cg.in(read.getExpr().getType());
        this.cg.store(read.getExpr().getType());
        return null;
    }

    @Override
    public Void visit(Write write, Void param) {
        this.cg.pragmaLine(write.getLine());
        this.cg.comment("- Write:");

        write.getExpr().accept(this.cg.valVisitor, null);
        this.cg.out(write.getExpr().getType());
        return null;
    }

    @Override
    public Void visit(Assignment assignment, Void param) {
        this.cg.pragmaLine(assignment.getLine());
        this.cg.comment("- Assignment:");

        assignment.getAssigned().accept(this.cg.addrVisitor, null);
        assignment.getValue().accept(this.cg.valVisitor, null);
        this.cg.store(assignment.getAssigned().getType());
        return null;
    }

    @Override
    public Void visit(VariableDefinition varDef, Void param) {
        this.cg.comment(String.format("* %s %s (offset %d)", varDef.getType().typeExpression(), varDef.getName(), varDef.getOffset()));
        return null;
    }

    @Override
    public Void visit(FunctionDefinition fnDef, Void param) {
        int paramByteSum = fnDef.getType().getParams().stream()
                .mapToInt(def -> def.getType().numberOfBytes())
                .sum();
        int localByteSum = fnDef.getDefs().stream()
                .mapToInt(def -> def.getType().numberOfBytes())
                .sum();

        this.cg.pragmaLine(fnDef.getLine());
        this.cg.label(fnDef.getName());
        this.cg.incrementIndent();

        this.cg.comment("* Parameters:");
        fnDef.getType().getParams().forEach(def -> def.accept(this, null));
        this.cg.comment("* Local variables:");
        fnDef.getDefs().forEach(def -> def.accept(this, null));
        this.cg.enter(localByteSum);
        this.cg.newLine();

        fnDef.getStmts().forEach(def -> {
            def.accept(this, null);
            this.cg.newLine();
        });

        if (fnDef.getType().getReturnType().equals(VoidType.getInstance()))
            this.cg.ret(0, localByteSum, paramByteSum);

        this.cg.decrementIndent();

        return null;
    }

    @Override
    public Void visit(Program program, Void param) {
        this.cg.comment("* Global variables:");
        program.getSentences().stream().filter(s -> s instanceof VariableDefinition).forEach(s -> s.accept(this, null));
        this.cg.newLine();

        this.cg.comment("Entrypoint:");
        this.cg.call("main");
        this.cg.halt();
        this.cg.newLine();

        program.getSentences().stream().filter(s -> s instanceof FunctionDefinition).forEach(s -> {
            s.accept(this, null);
            this.cg.newLine();
        });
        return null;
    }

    @Override
    public Void visit(While whl, Void param) {
        this.cg.pragmaLine(whl.getLine());
        this.cg.comment("- While:");

        String condLabel = this.cg.nextLabel();
        String exitLabel = this.cg.nextLabel();

        // Condition
        this.cg.label(condLabel);
        whl.getCondition().accept(this.cg.valVisitor, null);
        this.cg.jz(exitLabel);
        this.cg.newLine();

        // Body
        this.cg.incrementIndent();
        whl.getBody().forEach(st -> {
            st.accept(this, null);
            this.cg.newLine();
        });
        this.cg.jmp(condLabel);
        this.cg.decrementIndent();

        this.cg.decrementIndent();
        this.cg.label(exitLabel);

        return null;
    }

    @Override
    public Void visit(Conditional conditional, Void param) {
        String elseLabel = this.cg.nextLabel();
        String exitLabel = this.cg.nextLabel();

        this.cg.pragmaLine(conditional.getLine());
        this.cg.comment("- Conditional:");

        // Condition
        conditional.getCondition().accept(this.cg.valVisitor, null);
        this.cg.jz(elseLabel);
        this.cg.newLine();

        // Body
        this.cg.incrementIndent();
        conditional.getBody().forEach(st -> {
            st.accept(this, null);
            this.cg.newLine();
        });
        this.cg.jmp(exitLabel);
        this.cg.decrementIndent();
        this.cg.newLine();

        // Else
        this.cg.label(elseLabel);
        this.cg.incrementIndent();
        conditional.getElseBody().forEach(st -> {
            st.accept(this, null);
            this.cg.newLine();
        });
        this.cg.decrementIndent();
        this.cg.label(exitLabel);

        return null;
    }
}
