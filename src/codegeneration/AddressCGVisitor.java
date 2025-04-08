package codegeneration;

import ast.expression.Variable;
import codegeneration.util.AbstractCGVisitor;
import codegeneration.util.CodeGenerator;

/*
CODE TEMPLATES: address

address[[Variable: expression1 -> ID]] =
    if (expression1.definition.scope != 0) {
        <pusha> bp
        <pushi> expression1.definition.offset
        <addi>
    } else
        <pusha> expression1.definition.offset

 */


public class AddressCGVisitor extends AbstractCGVisitor<Void, Void> {

    public AddressCGVisitor(CodeGenerator cg) {
        super(cg);
    }

    @Override
    public Void visit(Variable var, Void param) {
        if (var.getDefinition().getScope() != 0) {
            this.cg.pushBP();
            this.cg.push(var.getDefinition().getOffset());
            this.cg.add();
        } else
            this.cg.pusha(var.getDefinition().getOffset());

        return null;
    }
}
