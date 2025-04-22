package codegeneration;

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
import ast.type.Type;
import ast.type.builtin.IntType;
import codegeneration.util.AbstractCGVisitor;
import codegeneration.util.CodeGenerator;

/*
CODE TEMPLATES: value

value[[CharLiteral: expression1 -> CHAR_CONST]] =
    <pushb > (int) CHAR_CONST

value[[IntLiteral: expression1 -> INT_CONST]] =
    <pushi > INT_CONST

value[[RealLiteral: expression1 -> REAL_CONST]] =
    <pushf > REAL_CONST

value[[Variable: expression1 -> ID]] =
    address[[expression1]]
    <load> expression1.type.suffix()

value[[ArithmeticExpression: expression1 -> expression2 OP expression3]] =
    value[[expression2]]
    expression2.type.convertTo(expression1.type)
    value[[expression3]]
    expression3.type.convertTo(expression1.type)
    switch (OP) {
        case "+":
            <add> expression1.type.suffix
            break;
        case "-":
            <sub> expression1.type.suffix
            break;
        case "*":
            <mul> expression1.type.suffix
            break;
        case "/":
            <div> expression1.type.suffix
            break;
    }

value[[ReminderExpression: expression1 -> expression2 expression3]] =
    value[[expression2]]
    expression2.type.convertTo(expression1.type)
    value[[expression3]]
    expression3.type.convertTo(expression1.type)
    <mod> expression1.type.suffix

value[[ComparisonExpression: expression1 -> expression2 OP expression3]] =
    Type superType = expression2.type.supertype(expression1.type)
    value[[expression2]]
    expression2.type.convertTo(superType)
    value[[expression3]]
    expression3.type.convertTo(superType)
    switch(OP) {
        case ">":
            <gt>
            break;
        case "<":
            <lt>
            break;
        case ">=":
            <ge>
            break;
        case "<=":
            <le>
            break;
        case "==":
            <eq>
            break;
        case "!=":
            <ne>
            break;
    } superType.suffix()


value[[LogicalExpression: expression1 -> expression2 OP expression3]] =
    value[[expression2]]
    value[[expression3]]
    switch(OP) {
        case "&&":
            <and>
            break;
        case "||":
            <or>
            break;
    }

value[[Cast: expression1 -> type expression2]] =
    value[[expression2]]
    expression2.type.convertTo(type)

value[[Negation: expression1 -> expression2]] =
    value[[expression2]]
    <not>

value[[UnaryMinus: expression1 -> expression2]] =
    <pushi 0>
    IntType.getInstance().convertTo(expression1.type.getInstance())
    value[[expression2]]
    expression2.type.convertTo(expression1.type.getInstance())
    <sub> expression1.type.suffix()

// This is the same as Variable
value[[Indexing: expression -> expression1 expression2]] =
    address[[expression]]
    <load> expression.type.suffix()

// This is the same as Variable
value[[FieldAccess: expression -> expression1 ID]] =
    address[[expression]]
    <load> expression.type.suffix()

 */

public class ValueCGVisitor extends AbstractCGVisitor<Void, Void> {

    public ValueCGVisitor(CodeGenerator cg) {
        super(cg);
    }

    private void loadExpression(Expression expr) {
        expr.accept(this.cg.addrVisitor, null);
        this.cg.load(expr.getType());
    }

    @Override
    public Void visit(CharLiteral charLiteral, Void param) {
        this.cg.push((byte) charLiteral.getValue());
        return null;
    }

    @Override
    public Void visit(IntLiteral intLiteral, Void param) {
        this.cg.push(intLiteral.getValue());
        return null;
    }

    @Override
    public Void visit(RealLiteral realLiteral, Void param) {
        this.cg.push(realLiteral.getValue());
        return null;
    }

    @Override
    public Void visit(Variable var, Void param) {
        this.loadExpression(var);
        return null;
    }

    @Override
    public Void visit(ArithmeticExpression arithmetic, Void param) {
        arithmetic.getOp1().accept(this, null);
        arithmetic.getOp1().getType().convertTo(arithmetic.getType(), this.cg);

        arithmetic.getOp2().accept(this, null);
        arithmetic.getOp2().getType().convertTo(arithmetic.getType(), this.cg);

        this.cg.arithmetic(arithmetic.getOperator(), arithmetic.getType());
        return null;
    }

    @Override
    public Void visit(ReminderExpression reminder, Void param) {
        reminder.getOp1().accept(this, null);
        reminder.getOp1().getType().convertTo(reminder.getType(), this.cg);

        reminder.getOp2().accept(this, null);
        reminder.getOp2().getType().convertTo(reminder.getType(), this.cg);

        this.cg.mod(reminder.getType());
        return null;
    }

    @Override
    public Void visit(ComparisonExpression comparison, Void param) {
        Type superType = comparison.getType().supertype(comparison.getOp1().getType());

        comparison.getOp1().accept(this, null);
        comparison.getOp1().getType().convertTo(superType, this.cg);

        comparison.getOp2().accept(this, null);
        comparison.getOp2().getType().convertTo(superType, this.cg);

        this.cg.comparison(comparison.getOperator(), superType);
        return null;
    }

    @Override
    public Void visit(LogicalExpression logical, Void param) {
        logical.getOp1().accept(this, null);
        logical.getOp2().accept(this, null);
        this.cg.logical(logical.getOperator());
        return null;
    }

    @Override
    public Void visit(Cast cast, Void param) {
        cast.getExpr().accept(this, null);
        cast.getExpr().getType().convertTo(cast.getType(), this.cg);
        return null;
    }

    @Override
    public Void visit(Negation negation, Void param) {
        negation.getExpr().accept(this, null);
        this.cg.not();
        return null;
    }

    @Override
    public Void visit(UnaryMinus unaryMinus, Void param) {
        this.cg.push(0);
        IntType.getInstance().convertTo(unaryMinus.getType(), this.cg);
        unaryMinus.getExpr().accept(this, null);
        unaryMinus.getExpr().getType().convertTo(unaryMinus.getType(), this.cg);
        this.cg.sub(unaryMinus.getType());
        return null;
    }

    @Override
    public Void visit(Indexing idx, Void param) {
        this.loadExpression(idx);
        return null;
    }

    @Override
    public Void visit(FieldAccess fieldAccess, Void param) {
        this.loadExpression(fieldAccess);
        return null;
    }
}
