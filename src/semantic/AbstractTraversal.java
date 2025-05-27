package semantic;

import ast.*;
import ast.expression.*;
import ast.expression.binary.*;
import ast.expression.literal.*;
import ast.expression.ternary.TernaryExpression;
import ast.expression.unary.*;
import ast.statement.*;
import ast.statement.unary.*;
import ast.statement.conditional.*;
import ast.type.*;
import ast.type.builtin.*;
import ast.type.struct.*;
import ast.definition.*;

public abstract class AbstractTraversal<TP, TR> implements Visitor<TP, TR> {

    @Override
    public TR visit(Program program, TP param) {
        program.getSentences().forEach(s -> s.accept(this, param));
        return null;
    }

    @Override
    public TR visit(FuncInvocation invocation, TP param) {
        invocation.getFn().accept(this, null);
        invocation.getArgs().forEach(a -> a.accept(this, param));
        return null;
    }

    @Override
    public TR visit(FunctionType fnType, TP param) {
        fnType.getParams().forEach(p -> p.accept(this, param));
        fnType.getReturnType().accept(this, param);
        return null;
    }

    @Override
    public TR visit(ErrorType errType, TP param) {
        return null;
    }

    @Override
    public TR visit(ArrayType arrType, TP param) {
        arrType.getType().accept(this, param);
        return null;
    }

    @Override
    public TR visit(CharType chType, TP param) {
        return null;
    }

    @Override
    public TR visit(IntType intType, TP param) {
        return null;
    }

    @Override
    public TR visit(RealType realType, TP param) {
        return null;
    }

    @Override
    public TR visit(VoidType voidType, TP param) {
        return null;
    }

    @Override
    public TR visit(StructField structField, TP param) {
        structField.getType().accept(this, param);
        return null;
    }

    @Override
    public TR visit(StructType structType, TP param) {
        structType.getFields().forEach(f -> f.accept(this, param));
        return null;
    }

    @Override
    public TR visit(Assignment assignment, TP param) {
        assignment.getAssigned().accept(this, param);
        assignment.getValue().accept(this, param);
        return null;
    }

    @Override
    public TR visit(Conditional conditional, TP param) {
        conditional.getCondition().accept(this, param);
        conditional.getBody().forEach(s -> s.accept(this, param));
        conditional.getElseBody().forEach(s -> s.accept(this, param));
        return null;
    }

    @Override
    public TR visit(While whl, TP param) {
        whl.getCondition().accept(this, param);
        whl.getBody().forEach(s -> s.accept(this, param));
        return null;
    }

    @Override
    public TR visit(Read read, TP param) {
        read.getExpr().accept(this, param);
        return null;
    }

    @Override
    public TR visit(Write write, TP param) {
        write.getExpr().accept(this, param);
        return null;
    }

    @Override
    public TR visit(Return ret, TP param) {
        ret.getExpr().accept(this, param);
        return null;
    }

    @Override
    public TR visit(Variable var, TP param) {
        return null;
    }

    @Override
    public TR visit(Indexing idx, TP param) {
        idx.getElement().accept(this, param);
        idx.getIndex().accept(this, param);
        return null;
    }

    @Override
    public TR visit(ArithmeticExpression arithmetic, TP param) {
        arithmetic.getOp1().accept(this, param);
        arithmetic.getOp2().accept(this, param);
        return null;
    }

    @Override
    public TR visit(ComparisonExpression comparison, TP param) {
        comparison.getOp1().accept(this, param);
        comparison.getOp2().accept(this, param);
        return null;
    }

    @Override
    public TR visit(LogicalExpression logical, TP param) {
        logical.getOp1().accept(this, param);
        logical.getOp2().accept(this, param);
        return null;
    }

    @Override
    public TR visit(ReminderExpression reminder, TP param) {
        reminder.getOp1().accept(this, param);
        reminder.getOp2().accept(this, param);
        return null;
    }

    @Override
    public TR visit(CharLiteral charLiteral, TP param) {
        return null;
    }

    @Override
    public TR visit(IntLiteral intLiteral, TP param) {
        return null;
    }

    @Override
    public TR visit(RealLiteral realLiteral, TP param) {
        return null;
    }

    @Override
    public TR visit(Cast cast, TP param) {
        cast.getType().accept(this, param);
        cast.getExpr().accept(this, param);
        return null;
    }

    @Override
    public TR visit(FieldAccess fieldAccess, TP param) {
        fieldAccess.getExpr().accept(this, param);
        return null;
    }

    @Override
    public TR visit(Negation negation, TP param) {
        negation.getExpr().accept(this, param);
        return null;
    }

    @Override
    public TR visit(UnaryMinus unaryMinus, TP param) {
        unaryMinus.getExpr().accept(this, param);
        return null;
    }

    @Override
    public TR visit(FunctionDefinition fnDef, TP param) {
        fnDef.getType().accept(this, param);
        fnDef.getDefs().forEach(p -> p.accept(this, param));
        fnDef.getStmts().forEach(s -> s.accept(this, param));
        return null;
    }

    @Override
    public TR visit(VariableDefinition varDef, TP param) {
        varDef.getType().accept(this, param);
        return null;
    }

    @Override
    public TR visit(TernaryExpression ternaryExpression, TP param) {
        ternaryExpression.getCondition().accept(this, param);
        ternaryExpression.getTrueExpr().accept(this, param);
        ternaryExpression.getFalseExpr().accept(this, param);
        return null;
    }
}
