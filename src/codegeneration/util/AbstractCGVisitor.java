package codegeneration.util;

import ast.*;
import ast.expression.*;
import ast.expression.binary.*;
import ast.expression.literal.*;
import ast.expression.unary.*;
import ast.statement.*;
import ast.statement.unary.*;
import ast.statement.conditional.*;
import ast.type.*;
import ast.type.builtin.*;
import ast.type.struct.*;
import ast.definition.*;
import semantic.Visitor;

public abstract class AbstractCGVisitor<TP, TR> implements Visitor<TP, TR> {

    protected final CodeGenerator cg;

    public AbstractCGVisitor(CodeGenerator cg) {
        this.cg = cg;
    }

    private TR defaultImpl() {
        throw new UnsupportedOperationException("This method should have never been called.");
    }

    @Override
    public TR visit(Program program, TP param) {
        return this.defaultImpl();
    }

    @Override
    public TR visit(FuncInvocation invocation, TP param) {
        return this.defaultImpl();
    }

    @Override
    public TR visit(FunctionType fnType, TP param) {
        return this.defaultImpl();
    }

    @Override
    public TR visit(ErrorType errType, TP param) {
        return this.defaultImpl();
    }

    @Override
    public TR visit(ArrayType arrType, TP param) {
        return this.defaultImpl();
    }

    @Override
    public TR visit(CharType chType, TP param) {
        return this.defaultImpl();
    }

    @Override
    public TR visit(IntType intType, TP param) {
        return this.defaultImpl();
    }

    @Override
    public TR visit(RealType realType, TP param) {
        return this.defaultImpl();
    }

    @Override
    public TR visit(VoidType voidType, TP param) {
        return this.defaultImpl();
    }

    @Override
    public TR visit(StructField structField, TP param) {
        return this.defaultImpl();
    }

    @Override
    public TR visit(StructType structType, TP param) {
        return this.defaultImpl();
    }

    @Override
    public TR visit(Assignment assignment, TP param) {
        return this.defaultImpl();
    }

    @Override
    public TR visit(Conditional conditional, TP param) {
        return this.defaultImpl();
    }

    @Override
    public TR visit(While whl, TP param) {
        return this.defaultImpl();
    }

    @Override
    public TR visit(Read read, TP param) {
        return this.defaultImpl();
    }

    @Override
    public TR visit(Write write, TP param) {
        return this.defaultImpl();
    }

    @Override
    public TR visit(Return ret, TP param) {
        return this.defaultImpl();
    }

    @Override
    public TR visit(Variable var, TP param) {
        return this.defaultImpl();
    }

    @Override
    public TR visit(Indexing idx, TP param) {
        return this.defaultImpl();
    }

    @Override
    public TR visit(ArithmeticExpression arithmetic, TP param) {
        return this.defaultImpl();
    }

    @Override
    public TR visit(ComparisonExpression comparison, TP param) {
        return this.defaultImpl();
    }

    @Override
    public TR visit(LogicalExpression logical, TP param) {
        return this.defaultImpl();
    }

    @Override
    public TR visit(ReminderExpression reminder, TP param) {
        return this.defaultImpl();
    }

    @Override
    public TR visit(CharLiteral charLiteral, TP param) {
        return this.defaultImpl();
    }

    @Override
    public TR visit(IntLiteral intLiteral, TP param) {
        return this.defaultImpl();
    }

    @Override
    public TR visit(RealLiteral realLiteral, TP param) {
        return this.defaultImpl();
    }

    @Override
    public TR visit(Cast cast, TP param) {
        return this.defaultImpl();
    }

    @Override
    public TR visit(FieldAccess fieldAccess, TP param) {
        return this.defaultImpl();
    }

    @Override
    public TR visit(Negation negation, TP param) {
        return this.defaultImpl();
    }

    @Override
    public TR visit(UnaryMinus unaryMinus, TP param) {
        return this.defaultImpl();
    }

    @Override
    public TR visit(FunctionDefinition fnDef, TP param) {
        return this.defaultImpl();
    }

    @Override
    public TR visit(VariableDefinition varDef, TP param) {
        return this.defaultImpl();
    }

    @Override
    public TR visit(UnaryArithmetic arithmeticUnary, TP param) {
        return this.defaultImpl();
    }
}
