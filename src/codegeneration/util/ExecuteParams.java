package codegeneration.util;

import ast.type.Type;

public record ExecuteParams(Type returnType, int localByteSum, int paramByteSum) {
}
