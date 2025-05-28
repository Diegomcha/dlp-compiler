package semantic;

import ast.*;
import ast.definition.*;
import ast.expression.*;
import ast.expression.binary.*;
import ast.expression.literal.*;
import ast.expression.unary.*;
import ast.expression.unary.UnaryArithmetic;
import ast.statement.*;
import ast.statement.conditional.*;
import ast.statement.unary.*;
import ast.type.*;
import ast.type.builtin.*;
import ast.type.struct.*;

public interface Visitor<TP, TR> {

    TR visit(Program program, TP param);

    TR visit(FuncInvocation invocation, TP param);

    // TYPES

    TR visit(FunctionType fnType, TP param);

    TR visit(ErrorType errType, TP param);

    TR visit(ArrayType arrType, TP param);

    TR visit(CharType chType, TP param);

    TR visit(IntType intType, TP param);

    TR visit(RealType realType, TP param);

    TR visit(VoidType voidType, TP param);

    TR visit(StructField structField, TP param);

    TR visit(StructType structType, TP param);

    // STATEMENTS

    TR visit(Assignment assignment, TP param);

    TR visit(Conditional conditional, TP param);

    TR visit(While whl, TP param);

    TR visit(Read read, TP param);

    TR visit(Write write, TP param);

    TR visit(Return ret, TP param);

    // EXPRESSIONS

    TR visit(Variable var, TP param);

    TR visit(Indexing idx, TP param);

    TR visit(ArithmeticExpression arithmetic, TP param);

    TR visit(UnaryArithmetic arithmeticUnary, TP param);

    TR visit(ComparisonExpression comparison, TP param);

    TR visit(LogicalExpression logical, TP param);

    TR visit(ReminderExpression reminder, TP param);

    TR visit(CharLiteral charLiteral, TP param);

    TR visit(IntLiteral intLiteral, TP param);

    TR visit(RealLiteral realLiteral, TP param);

    TR visit(Cast cast, TP param);

    TR visit(FieldAccess fieldAccess, TP param);

    TR visit(Negation negation, TP param);

    TR visit(UnaryMinus unaryMinus, TP param);

    // DEFINITIONS

    TR visit(FunctionDefinition fnDef, TP param);

    TR visit(VariableDefinition varDef, TP param);

}
