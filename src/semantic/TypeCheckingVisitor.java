package semantic;

import ast.FuncInvocation;
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

*/

public class TypeCheckingVisitor extends AbstractTraversal {

    @Override
    public Void visit(FuncInvocation invocation, Void param) {
        super.visit(invocation, param);

        invocation.setLValue(false);
        invocation.setType(invocation.getFn().getType().parenthesis(invocation.getArgs().stream().map(Expression::getType).toList()));

        return null;
    }

    @Override
    public Void visit(Assignment assignment, Void param) {
        super.visit(assignment, param);

        // L-value expected error
        if (!assignment.getAssigned().getLValue())
            new ErrorType(assignment.getAssigned().getLine(), assignment.getAssigned().getCol(), "L-value expected");

        assignment.getAssigned().getType().assign(assignment.getValue().getType());

        return null;
    }

    @Override
    public Void visit(Read read, Void param) {
        super.visit(read, param);

        // L-value expected error
        if (!read.getExpr().getLValue())
            new ErrorType(read.getExpr().getLine(), read.getExpr().getCol(), "L-value expected");

        read.getExpr().getType().mustBeReadable();

        return null;
    }

    @Override
    public Void visit(Variable var, Void param) {
        super.visit(var, param);

        var.setLValue(true);
        if (var.getDefinition() != null)
            var.setType(var.getDefinition().getType());
        // Else an error type is already set to type in the 'IdentificationVisitor'

        return null;
    }

    @Override
    public Void visit(Indexing idx, Void param) {
        super.visit(idx, param);

        idx.setLValue(true);
        idx.setType(idx.getElement().getType().squareBrackets(idx.getIndex().getType()));

        return null;
    }

    @Override
    public Void visit(ArithmeticExpression arithmetic, Void param) {
        super.visit(arithmetic, param);

        arithmetic.setLValue(false);
        arithmetic.setType(arithmetic.getOp1().getType().arithmetic(arithmetic.getOp2().getType()));

        return null;
    }

    @Override
    public Void visit(ComparisonExpression comparison, Void param) {
        super.visit(comparison, param);

        comparison.setLValue(false);
        comparison.setType(comparison.getOp1().getType().compare(comparison.getOp2().getType()));

        return null;
    }

    @Override
    public Void visit(LogicalExpression logical, Void param) {
        super.visit(logical, param);

        logical.setLValue(false);
        logical.setType(logical.getOp1().getType().logicCompare(logical.getOp2().getType()));

        return null;
    }

    @Override
    public Void visit(ReminderExpression reminder, Void param) {
        super.visit(reminder, param);

        reminder.setLValue(false);
        reminder.setType(reminder.getOp1().getType().percentage(reminder.getOp2().getType()));

        return null;
    }

    @Override
    public Void visit(CharLiteral charLiteral, Void param) {
        super.visit(charLiteral, param);

        charLiteral.setLValue(false);
        charLiteral.setType(CharType.getInstance());

        return null;
    }

    @Override
    public Void visit(IntLiteral intLiteral, Void param) {
        super.visit(intLiteral, param);

        intLiteral.setLValue(false);
        intLiteral.setType(IntType.getInstance());

        return null;
    }

    @Override
    public Void visit(RealLiteral realLiteral, Void param) {
        super.visit(realLiteral, param);

        realLiteral.setLValue(false);
        realLiteral.setType(RealType.getInstance());

        return null;
    }

    @Override
    public Void visit(Cast cast, Void param) {
        super.visit(cast, param);

        cast.setLValue(false);
        cast.setType(cast.getExpr().getType().cast(cast.getType()));

        return null;
    }

    @Override
    public Void visit(FieldAccess fieldAccess, Void param) {
        super.visit(fieldAccess, param);

        fieldAccess.setLValue(true);
        fieldAccess.setType(fieldAccess.getExpr().getType().dot(fieldAccess.getProperty()));

        return null;
    }

    @Override
    public Void visit(Negation negation, Void param) {
        super.visit(negation, param);

        negation.setLValue(false);
        negation.setType(negation.getExpr().getType().exclamation());

        return null;
    }

    @Override
    public Void visit(UnaryMinus unaryMinus, Void param) {
        super.visit(unaryMinus, param);

        unaryMinus.setLValue(false);
        unaryMinus.setType(unaryMinus.getExpr().getType().minus());

        return null;
    }

    @Override
    public Void visit(While whl, Void param) {
        // Inherited attribute...
        whl.getBody().forEach(st -> st.setReturnType(whl.getReturnType()));

        super.visit(whl, param);

        whl.getCondition().getType().mustBeCondition();

        return null;
    }

    @Override
    public Void visit(Conditional cond, Void param) {
        // Inherited attribute...
        cond.getBody().forEach(st -> st.setReturnType(cond.getReturnType()));
        cond.getElseBody().forEach(st -> st.setReturnType(cond.getReturnType()));

        super.visit(cond, param);

        cond.getCondition().getType().mustBeCondition();

        return null;
    }

    @Override
    public Void visit(Return ret, Void param) {
        super.visit(ret, param);

        ret.getExpr().getType().ret(ret.getReturnType());

        return null;
    }

    @Override
    public Void visit(Write write, Void param) {
        super.visit(write, param);

        write.getExpr().getType().mustBeWritable();

        return null;
    }

    @Override
    public Void visit(FunctionDefinition fnDef, Void param) {
        // Inherited attributes...
        fnDef.getStmts().forEach(st -> st.setReturnType(fnDef.getType().getReturnType()));

        return super.visit(fnDef, param);
    }
}
