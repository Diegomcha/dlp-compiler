package codegeneration;

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

 */

public class ValueCGVisitor {
}
