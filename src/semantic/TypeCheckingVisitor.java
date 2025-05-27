package semantic;

import ast.FuncInvocation;
import ast.definition.VarInitialization;
import ast.definition.FunctionDefinition;
import ast.expression.Expression;
import ast.expression.Indexing;
import ast.expression.Variable;
import ast.expression.binary.ArithmeticExpression;
import ast.expression.binary.ComparisonExpression;
import ast.expression.binary.LogicalExpression;
import ast.expression.binary.ReminderExpression;
import ast.expression.literal.CharLiteral;
import ast.expression.literal.IntLiteral;
import ast.expression.literal.RealLiteral;
import ast.expression.unary.Cast;
import ast.expression.unary.FieldAccess;
import ast.expression.unary.Negation;
import ast.expression.unary.UnaryMinus;
import ast.statement.Assignment;
import ast.statement.conditional.Conditional;
import ast.statement.conditional.While;
import ast.statement.unary.Read;
import ast.statement.unary.Return;
import ast.statement.unary.Write;
import ast.type.ErrorType;
import ast.type.Type;
import ast.type.builtin.CharType;
import ast.type.builtin.IntType;
import ast.type.builtin.RealType;
/*
        TypeChecking AG.

// EXPRESSIONS

P:
 Variable: expression -> ID
R:
 if (expression.definition != null)
    expression.type = expression.definition.type;
 // Otherwise an error will be set by the IdentificationVisitor

P:
 Indexing: expression1 -> expression2 expression3
R:
 expression1.type = expression2.type.squareBrackets(expression3.type);

P:
 FuncInvocation: expression1 -> expression2 expression*
R:
 expression1.type = expression2.type.parenthesis(
     expression*
         .stream()
         .map(e -> e.type)
         .toList()
 );

P:
 ArithmeticExpression: expression1 -> expression2 expression3
R:
 expression1.type = expression2.type.arithmetic(expression3.type);

P:
 ComparisonExpression: expression1 -> expression2 OP expression3
R:
 expression1.type = expression2.type.compare(expression3.type);

P:
 LogicalExpression: expression1 -> expression2 OP expression3
R:
 expression1.type = expression2.type.logicCompare(expression3.type);

P:
 ReminderExpression: expression1 -> expression2 expression3
R:
 expression1.type = expression2.type.percentage(expression3.type);

P:
 CharLiteral: expression -> CHAR_CONSTANT
R:
 expression.type = CharType.getInstance();

P:
 IntLiteral: expression -> INT_CONSTANT
R:
 expression.type = IntType.getInstance();

P:
 RealLiteral: expression -> REAL_CONSTANT
R:
 expression.type = RealType.getInstance();

P:
 Cast: expression1 -> type expression2
R:
 expression1.type = expression2.type.cast(type);

P:
 FieldAccess: expression1 -> expression2 ID
R:
 expression1.type = expression2.type.dot(ID);

P:
 Negation: expression1 -> expression2
R:
 expression1.type = expression2.type.exclamation();

P:
 UnaryMinus: expression1 -> expression2
R:
 expression1.type = expression2.type.minus();

// STATEMENTS

P:
 FuncInvocation: statement -> expression expression*
R:
 expression.type.parenthesis(
     expression*
         .stream()
         .map(e -> e.type)
         .toList()
 );

P:
 While: statement -> expression statement*
R:
 expression.type.mustBeCondition();
 statement*.stream().forEach(s -> s.returnType = statement.returnType);

P:
 Conditional: statement -> expression statement1* statement2*
R:
 expression.type.mustBeCondition();
 statement1*.stream().forEach(s -> s.returnType = statement.returnType);
 statement2*.stream().forEach(s -> s.returnType = statement.returnType);

P:
 Assignment: statement -> expression1 expression2
R:
 // L-value is already checked
 expression1.type.assign(expression2.type);

P:
 Return: statement -> expression
R:
 expression.type.ret(statement.returnType);

P:
 Read: statement -> expression
R:
 expression.type.mustBeReadable();

P:
 Write: statement -> expression
R:
 expression.type.mustBeWritable();

// DEFINITIONS

P:
 FunctionDefinition: definition -> type ID vardef* statement*
R:
 statement*.stream().forEach(s -> s.returnType = type.returnType);

P:
 VarInitialization: definition -> ID expression
R:
 expression.type.mustBeAssignable();
 definition.type = expression.type;

*/

public class TypeCheckingVisitor extends AbstractTraversal<Type, Void> {

    @Override
    public Void visit(FuncInvocation invocation, Type returnType) {
        super.visit(invocation, returnType);

        invocation.setLValue(false);
        invocation.setType(invocation.getFn().getType().parenthesis(invocation.getArgs().stream().map(Expression::getType).toList(), invocation));

        return null;
    }

    @Override
    public Void visit(Assignment assignment, Type returnType) {
        super.visit(assignment, returnType);

        // L-value expected error
        if (!assignment.getAssigned().getLValue())
            new ErrorType(assignment.getAssigned().getLine(), assignment.getAssigned().getCol(), "L-value expected");

        assignment.getAssigned().getType().assign(assignment.getValue().getType(), assignment);

        return null;
    }

    @Override
    public Void visit(Read read, Type returnType) {
        super.visit(read, returnType);

        // L-value expected error
        if (!read.getExpr().getLValue())
            new ErrorType(read.getExpr().getLine(), read.getExpr().getCol(), "L-value expected");

        read.getExpr().getType().mustBeReadable(read);

        return null;
    }

    @Override
    public Void visit(Variable var, Type returnType) {
        super.visit(var, returnType);

        var.setLValue(true);
        if (var.getDefinition() != null)
            var.setType(var.getDefinition().getType());
        // Else an error type is already set to type in the 'IdentificationVisitor'

        return null;
    }

    @Override
    public Void visit(Indexing idx, Type returnType) {
        super.visit(idx, returnType);

        idx.setLValue(true);
        idx.setType(idx.getElement().getType().squareBrackets(idx.getIndex().getType(), idx));

        return null;
    }

    @Override
    public Void visit(ArithmeticExpression arithmetic, Type returnType) {
        super.visit(arithmetic, returnType);

        arithmetic.setLValue(false);
        arithmetic.setType(arithmetic.getOp1().getType().arithmetic(arithmetic.getOp2().getType(), arithmetic));

        return null;
    }

    @Override
    public Void visit(ComparisonExpression comparison, Type returnType) {
        super.visit(comparison, returnType);

        comparison.setLValue(false);
        comparison.setType(comparison.getOp1().getType().compare(comparison.getOp2().getType(), comparison));

        return null;
    }

    @Override
    public Void visit(LogicalExpression logical, Type returnType) {
        super.visit(logical, returnType);

        logical.setLValue(false);
        logical.setType(logical.getOp1().getType().logicCompare(logical.getOp2().getType(), logical));

        return null;
    }

    @Override
    public Void visit(ReminderExpression reminder, Type returnType) {
        super.visit(reminder, returnType);

        reminder.setLValue(false);
        reminder.setType(reminder.getOp1().getType().percentage(reminder.getOp2().getType(), reminder));

        return null;
    }

    @Override
    public Void visit(CharLiteral charLiteral, Type returnType) {
        super.visit(charLiteral, returnType);

        charLiteral.setLValue(false);
        charLiteral.setType(CharType.getInstance());

        return null;
    }

    @Override
    public Void visit(IntLiteral intLiteral, Type returnType) {
        super.visit(intLiteral, returnType);

        intLiteral.setLValue(false);
        intLiteral.setType(IntType.getInstance());

        return null;
    }

    @Override
    public Void visit(RealLiteral realLiteral, Type returnType) {
        super.visit(realLiteral, returnType);

        realLiteral.setLValue(false);
        realLiteral.setType(RealType.getInstance());

        return null;
    }

    @Override
    public Void visit(Cast cast, Type returnType) {
        super.visit(cast, returnType);

        cast.setLValue(false);
        cast.setType(cast.getExpr().getType().cast(cast.getType(), cast));

        return null;
    }

    @Override
    public Void visit(FieldAccess fieldAccess, Type returnType) {
        super.visit(fieldAccess, returnType);

        fieldAccess.setLValue(true);
        fieldAccess.setType(fieldAccess.getExpr().getType().dot(fieldAccess.getProperty(), fieldAccess));

        return null;
    }

    @Override
    public Void visit(Negation negation, Type returnType) {
        super.visit(negation, returnType);

        negation.setLValue(false);
        negation.setType(negation.getExpr().getType().exclamation(negation));

        return null;
    }

    @Override
    public Void visit(UnaryMinus unaryMinus, Type returnType) {
        super.visit(unaryMinus, returnType);

        unaryMinus.setLValue(false);
        unaryMinus.setType(unaryMinus.getExpr().getType().minus(unaryMinus));

        return null;
    }

    @Override
    public Void visit(While whl, Type returnType) {
        // Pass the return type to the body
        super.visit(whl, returnType);

        whl.getCondition().getType().mustBeCondition(whl);

        return null;
    }

    @Override
    public Void visit(Conditional cond, Type returnType) {
        // Pass the return type to the bodies
        super.visit(cond, returnType);

        cond.getCondition().getType().mustBeCondition(cond);

        return null;
    }

    @Override
    public Void visit(Return ret, Type returnType) {
        super.visit(ret, returnType);

        // Check return type matches
        ret.getExpr().getType().ret(returnType, ret);

        return null;
    }

    @Override
    public Void visit(Write write, Type returnType) {
        super.visit(write, returnType);

        write.getExpr().getType().mustBeWritable(write);

        return null;
    }

    @Override
    public Void visit(FunctionDefinition fnDef, Type returnType) {
        // Passing returnType
        return super.visit(fnDef, fnDef.getType().getReturnType());
    }

    @Override
    public Void visit(VarInitialization varInitialization, Type param) {
        super.visit(varInitialization, param);

        // Type must be assignable
        varInitialization.getExpr().getType().mustBeAssignable(varInitialization);
        // Type inference
        varInitialization.setType(varInitialization.getExpr().getType());

        return null;
    }
}
