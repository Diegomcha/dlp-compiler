package codegeneration;

import ast.expression.Indexing;
import ast.expression.Variable;
import ast.expression.unary.FieldAccess;
import ast.type.struct.StructType;
import codegeneration.util.AbstractCGVisitor;
import codegeneration.util.CodeGenerator;

/*
CODE TEMPLATES: address

address[[Variable: expression1 -> ID]] =
    if (expression1.definition.scope != 0) {
        <pusha bp>
        <pushi > expression1.definition.offset
        <addi>
    } else
        <pusha> expression1.definition.offset

address[[Indexing: expression -> expression1 expression2]] =
    address[[expression1]]
    <pushi > expression.type.numberOfBytes()
    value[[expression2]]
    <muli>
    <addi>

address[[FieldAccess: expression -> expression1 ID]] =
    address[[expression1]]
    <pushi > expression1.type.findField(ID).offset
    <addi>

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

    @Override
    public Void visit(Indexing idx, Void param) {
        idx.getElement().accept(this, null);
        this.cg.push(idx.getType().numberOfBytes());
        idx.getIndex().accept(this.cg.valVisitor, null);
        this.cg.mul();
        this.cg.add();

        return null;
    }

    @Override
    public Void visit(FieldAccess fieldAccess, Void param) {
        fieldAccess.getExpr().accept(this, null);
        this.cg.push(((StructType) fieldAccess.getExpr().getType()).findField(fieldAccess.getProperty()).getOffset());
        this.cg.add();

        return null;
    }
}
