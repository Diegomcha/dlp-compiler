package semantic;

import ast.*;
import ast.expression.*;
import ast.expression.binary.*;
import ast.expression.literal.*;
import ast.expression.unary.*;
import ast.statement.*;
import ast.statement.unary.*;
import ast.type.*;

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
