package codegeneration;

/*
CODE TEMPLATES: execute

execute[[Read: statement -> expression]] =
    address[[expression]]
    <in> expression.type.suffix
    <store> expression.type.suffix()

execute[[Write: statement -> expression]] =
    value[[expression]]
    <out> expression.type.suffix()

execute[[Assignment: statement -> expression1 expression2]] =
    address[[expression1]]
    value[[expression2]]
    <store> expression1.type.suffix()

execute[[VariableDefinition: definition -> type ID]] =
    <' * > definition.type.typeExpression() < > ID < (offset> definition.offset <)>

// TODO: Add code for enter (reserving space)
execute[[FunctionDefinition: definition -> ID fnType vardefinition* statement*]] =
    ID <:>
    <' * Parameters>
    fnType.params.forEach(def -> execute[[def]])
    <' * Local variables>
    vardefinition*.forEach(def -> execute[[def]])
    <enter > vardefinition*.map(def -> def.type.numberOfBytes).sum()
    if (fnType.returnType == VoidType.getInstance())
        <ret 0, > vardefinition*.map(def -> def.type.numberOfBytes).sum() <, > fnType.params.map(def -> def.type.numberOfBytes).sum()

execute[[Program: program -> vardefintion* fndefinition*]] =
    <' * Global variables>
    vardefinition*.forEach(def -> execute[[def]])
    <call main>
    <halt>
    fndefinition*.forEach(def -> execute[[def]])

 */


public class ExecuteCGVisitor {
}
