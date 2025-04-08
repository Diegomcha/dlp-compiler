package codegeneration;

/*
CODE TEMPLATES: address

address[[Variable: expression1 -> ID]] =
    if (expression1.definition.scope != 0)
        <pusha> bp

    <pusha> expression1.definition.offset

    if (expression1.definition.scope != 0)
        <addi>

 */


public class AddressCGVisitor {
}
