package semantic;

import ast.*;
import ast.expression.*;
import ast.expression.binary.*;
import ast.expression.literal.*;
import ast.expression.unary.*;
import ast.statement.*;
import ast.statement.unary.*;
import ast.type.*;
/*
        TypeChecking AG.

// EXPRESSIONS

P:
 Variable: expression -> ID
R:
 expression.type = expression.definition != null ? expression.definition.type : null; // TODO: Should the type be null if no definition, the error is already thrown by the identification visitor???

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
 expression.type.return(statement.returnType);

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

        return null;
    }

    @Override
    public Void visit(Assignment assignment, Void param) {
        super.visit(assignment, param);

        // L-value expected error
        if (!assignment.getAssigned().getLValue())
            new ErrorType(assignment.getAssigned().getLine(), assignment.getAssigned().getCol(), "L-value expected");

        return null;
    }

    @Override
    public Void visit(Read read, Void param) {
        super.visit(read, param);

        // L-value expected error
        if (!read.getExpr().getLValue())
            new ErrorType(read.getExpr().getLine(), read.getExpr().getCol(), "L-value expected");

        return null;
    }

    @Override
    public Void visit(Variable var, Void param) {
        super.visit(var, param);

        var.setLValue(true);
        return null;
    }

    @Override
    public Void visit(Indexing idx, Void param) {
        super.visit(idx, param);

        idx.setLValue(true);

        return null;
    }

    @Override
    public Void visit(ArithmeticExpression arithmetic, Void param) {
        super.visit(arithmetic, param);

        arithmetic.setLValue(false);

        return null;
    }

    @Override
    public Void visit(ComparisonExpression comparison, Void param) {
        super.visit(comparison, param);

        comparison.setLValue(false);

        return null;
    }

    @Override
    public Void visit(LogicalExpression logical, Void param) {
        super.visit(logical, param);

        logical.setLValue(false);

        return null;
    }

    @Override
    public Void visit(ReminderExpression reminder, Void param) {
        super.visit(reminder, param);

        reminder.setLValue(false);

        return null;
    }

    @Override
    public Void visit(CharLiteral charLiteral, Void param) {
        super.visit(charLiteral, param);

        charLiteral.setLValue(false);

        return null;
    }

    @Override
    public Void visit(IntLiteral intLiteral, Void param) {
        super.visit(intLiteral, param);

        intLiteral.setLValue(false);

        return null;
    }

    @Override
    public Void visit(RealLiteral realLiteral, Void param) {
        super.visit(realLiteral, param);

        realLiteral.setLValue(false);

        return null;
    }

    @Override
    public Void visit(Cast cast, Void param) {
        super.visit(cast, param);

        cast.setLValue(false);

        return null;
    }

    @Override
    public Void visit(FieldAccess fieldAccess, Void param) {
        super.visit(fieldAccess, param);

        fieldAccess.setLValue(true);

        return null;
    }

    @Override
    public Void visit(Negation negation, Void param) {
        super.visit(negation, param);

        negation.setLValue(false);

        return null;
    }

    @Override
    public Void visit(UnaryMinus unaryMinus, Void param) {
        super.visit(unaryMinus, param);

        unaryMinus.setLValue(false);

        return null;
    }
}
