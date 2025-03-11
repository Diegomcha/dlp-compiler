package semantic;

import ast.*;
import ast.definition.*;
import ast.expression.*;
import ast.expression.binary.*;
import ast.expression.literal.*;
import ast.expression.unary.*;
import ast.statement.*;
import ast.statement.conditional.*;
import ast.statement.unary.*;
import ast.type.*;
import ast.type.builtin.*;
import ast.type.struct.*;

public class TypeCheckingVisitor implements Visitor<Void, Void> {

    @Override
    public Void visit(Program program, Void param) {
        program.getSentences().forEach(s -> s.accept(this, null));
        return null;
    }

    @Override
    public Void visit(FuncInvocation invocation, Void param) {
        invocation.getFn().accept(this, null);
        invocation.getArgs().forEach(a -> a.accept(this, null));

        invocation.setLValue(false);

        return null;
    }

    @Override
    public Void visit(FunctionType fnType, Void param) {
        fnType.getParams().forEach(p -> p.accept(this, null));
        fnType.getReturnType().accept(this, null);
        return null;
    }

    @Override
    public Void visit(ErrorType errType, Void param) {
        return null;
    }

    @Override
    public Void visit(ArrayType arrType, Void param) {
        arrType.getType().accept(this, null);
        return null;
    }

    @Override
    public Void visit(CharType chType, Void param) {
        return null;
    }

    @Override
    public Void visit(IntType intType, Void param) {
        return null;
    }

    @Override
    public Void visit(RealType realType, Void param) {
        return null;
    }

    @Override
    public Void visit(VoidType voidType, Void param) {
        return null;
    }

    @Override
    public Void visit(StructField structField, Void param) {
        structField.getType().accept(this, null);
        return null;
    }

    @Override
    public Void visit(StructType structType, Void param) {
        structType.getFields().forEach(f -> f.accept(this, null));
        return null;
    }

    @Override
    public Void visit(Assignment assignment, Void param) {
        assignment.getAssigned().accept(this, null);
        assignment.getValue().accept(this, null);

        // L-value expected error
        if (!assignment.getAssigned().getLValue())
            new ErrorType(assignment.getAssigned().getLine(), assignment.getAssigned().getCol(), "L-value expected");

        return null;
    }

    @Override
    public Void visit(Conditional conditional, Void param) {
        conditional.getCondition().accept(this, null);
        conditional.getBody().forEach(s -> s.accept(this, null));
        conditional.getElseBody().forEach(s -> s.accept(this, null));
        return null;
    }

    @Override
    public Void visit(While whl, Void param) {
        whl.getCondition().accept(this, null);
        whl.getBody().forEach(s -> s.accept(this, null));
        return null;
    }

    @Override
    public Void visit(Read read, Void param) {
        read.getExpr().accept(this, null);

        // L-value expected error
        if (!read.getExpr().getLValue())
            new ErrorType(read.getExpr().getLine(), read.getExpr().getCol(), "L-value expected");

        return null;
    }

    @Override
    public Void visit(Write write, Void param) {
        write.getExpr().accept(this, null);
        return null;
    }

    @Override
    public Void visit(Return ret, Void param) {
        ret.getExpr().accept(this, null);
        return null;
    }

    @Override
    public Void visit(Variable var, Void param) {
        var.setLValue(true);
        return null;
    }

    @Override
    public Void visit(Indexing idx, Void param) {
        idx.getElement().accept(this, null);
        idx.getIndex().accept(this, null);

        idx.setLValue(true);

        return null;
    }

    @Override
    public Void visit(ArithmeticExpression arithmetic, Void param) {
        arithmetic.getOp1().accept(this, null);
        arithmetic.getOp2().accept(this, null);

        arithmetic.setLValue(false);

        return null;
    }

    @Override
    public Void visit(ComparisonExpression comparison, Void param) {
        comparison.getOp1().accept(this, null);
        comparison.getOp2().accept(this, null);

        comparison.setLValue(false);

        return null;
    }

    @Override
    public Void visit(LogicalExpression logical, Void param) {
        logical.getOp1().accept(this, null);
        logical.getOp2().accept(this, null);

        logical.setLValue(false);

        return null;
    }

    @Override
    public Void visit(ReminderExpression reminder, Void param) {
        reminder.getOp1().accept(this, null);
        reminder.getOp2().accept(this, null);

        reminder.setLValue(false);

        return null;
    }

    @Override
    public Void visit(CharLiteral charLiteral, Void param) {
        charLiteral.setLValue(false);

        return null;
    }

    @Override
    public Void visit(IntLiteral intLiteral, Void param) {
        intLiteral.setLValue(false);

        return null;
    }

    @Override
    public Void visit(RealLiteral realLiteral, Void param) {
        realLiteral.setLValue(false);

        return null;
    }

    @Override
    public Void visit(Cast cast, Void param) {
        cast.getType().accept(this, null);
        cast.getExpr().accept(this, null);

        cast.setLValue(false);

        return null;
    }

    @Override
    public Void visit(FieldAccess fieldAccess, Void param) {
        fieldAccess.getExpr().accept(this, null);

        fieldAccess.setLValue(true);

        return null;
    }

    @Override
    public Void visit(Negation negation, Void param) {
        negation.getExpr().accept(this, null);

        negation.setLValue(false);

        return null;
    }

    @Override
    public Void visit(UnaryMinus unaryMinus, Void param) {
        unaryMinus.getExpr().accept(this, null);

        unaryMinus.setLValue(false);

        return null;
    }

    @Override
    public Void visit(FunctionDefinition fnDef, Void param) {
        fnDef.getType().accept(this, null);
        fnDef.getDefs().forEach(p -> p.accept(this, null));
        fnDef.getStmts().forEach(s -> s.accept(this, null));
        return null;
    }

    @Override
    public Void visit(VariableDefinition varDef, Void param) {
        varDef.getType().accept(this, null);
        return null;
    }
}
