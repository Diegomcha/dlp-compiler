package codegeneration;

/*
    Offset AG.

// globalBytesSum = 0;

// GLOBALS

P:  VariableDefinition: definition -> type ID
R:  if (definition.scope == 0) {
        definition.offset = globalBytesSum;
        globalBytesSum += type.numberOfBytes();
    }

// RECORD FIELDS

P:  StructType: type -> structField*
R:  int fieldsBytesSum = 0;
    for (StructField field : structField*) {
        field.offset = fieldsBytesSum;
        fieldsBytesSum += field.type.numberOfBytes();
    }

// LOCALS

P:  FunctionDefinition: definition -> type ID varDefinition* statement*
R:  int localBytesSum = 0;
    for (VariableDefinition var : varDefinition*) {
        localBytesSum += var.type.numberOfBytes();
        var.offset = -localBytesSum;
    }

// PARAMS

P:  FunctionType: type -> type varDefinition*
R:  int paramsOffsetSum = 4; // to skip control info
    for (VariableDefinition var : varDefinition*.reversed()) { // right-to-left
        var.offset = paramsOffsetSum;
        paramsOffsetSum += var.type.numberOfBytes();
    }


 */

import ast.definition.FunctionDefinition;
import ast.definition.VariableDefinition;
import ast.type.FunctionType;
import ast.type.struct.StructField;
import ast.type.struct.StructType;
import semantic.AbstractTraversal;

public class OffsetVisitor extends AbstractTraversal<Void, Void> {

    private int globalBytesSum = 0;

    @Override
    public Void visit(VariableDefinition varDef, Void param) {
        super.visit(varDef, param);

        if (varDef.getScope() == 0) {
            varDef.setOffset(globalBytesSum);
            globalBytesSum += varDef.getType().numberOfBytes();
        }

        return null;
    }

    @Override
    public Void visit(StructType structType, Void param) {
        super.visit(structType, param);

        int fieldsBytesSum = 0;
        for (StructField field : structType.getFields()) {
            field.setOffset(fieldsBytesSum);
            fieldsBytesSum += field.getType().numberOfBytes();
        }

        return null;
    }

    @Override
    public Void visit(FunctionDefinition fnDef, Void param) {
        super.visit(fnDef, param);

        int localBytesSum = 0;
        for (VariableDefinition var : fnDef.getDefs()) {
            localBytesSum += var.getType().numberOfBytes();
            var.setOffset(-localBytesSum);
        }

        return null;
    }

    @Override
    public Void visit(FunctionType fnType, Void param) {
        super.visit(fnType, param);

        int paramsOffsetSum = 4; // to skip control info
        for (VariableDefinition var : fnType.getParams().reversed()) { // right-to-left
            var.setOffset(paramsOffsetSum);
            paramsOffsetSum += var.getType().numberOfBytes();
        }

        return null;
    }

}
