package codegeneration.util;

import ast.type.Type;
import codegeneration.AddressCGVisitor;
import codegeneration.ValueCGVisitor;

import java.io.FileWriter;
import java.io.IOException;

public class CodeGenerator implements AutoCloseable {
    private final FileWriter fw;

    public final AddressCGVisitor addrVisitor = new AddressCGVisitor(this);
    public final ValueCGVisitor valVisitor = new ValueCGVisitor(this);

    private int indent = 0;
    private int labelID = 0;

    public CodeGenerator(String source, String destination) throws IOException {
        this.fw = new FileWriter(destination);
        this.pragmaSource(source);
        this.newLine();
    }

    @Override
    public void close() throws IOException {
        this.fw.close();
    }

    // * Indent

    public void incrementIndent() {
        this.indent++;
    }

    public void decrementIndent() {
        this.indent--;
    }

    // * Labels

    public String nextLabel() {
        return "label_" + labelID++;
    }

    // * Utility methods

    private void writeLine(String line) {
        this.writeLine(line, false);
    }

    private void writeLine(String line, boolean ignoreIndent) {
        try {
            if (!ignoreIndent)
                for (int i = 0; i < this.indent; i++)
                    this.fw.write("\t");

            this.fw.write(line + "\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void comment(String string) {
        this.writeLine("' " + string);
    }

    public void pragmaLine(int line) {
        this.writeLine("#line " + line, true);
    }

    private void pragmaSource(String source) {
        this.writeLine("#source \"" + source + "\"", true);
    }

    public void newLine() {
        this.writeLine("", true);
    }

    // * Methods

    // IO

    public void in(Type type) {
        this.writeLine("in" + type.suffix());
    }

    public void out(Type type) {
        this.writeLine("out" + type.suffix());
    }

    // Stack ops

    public void push(byte value) {
        this.writeLine("pushb " + value);
    }

    public void push(int value) {
        this.writeLine("pushi " + value);
    }

    public void push(double value) {
        this.writeLine("pushf " + value);
    }

    public void pusha(int value) {
        this.writeLine("pusha " + value);
    }

    public void pushBP() {
        this.writeLine("pusha bp");
    }

    public void pop(Type type) {
        this.writeLine("pop" + type.suffix());
    }

    // Mem ops

    public void store(Type type) {
        this.writeLine("store" + type.suffix());
    }

    public void load(Type type) {
        this.writeLine("load" + type.suffix());
    }

    // Control flow

    public void label(String name) {
        this.writeLine(name + ":");
    }

    public void enter(int size) {
        this.writeLine("enter " + size);
    }

    public void ret(int returnBytes, int localByteSum, int paramByteSum) {
        this.writeLine("ret " + returnBytes + ", " + localByteSum + ", " + paramByteSum);
    }

    public void call(String id) {
        this.writeLine("call " + id);
    }

    public void halt() {
        this.writeLine("halt");
    }

    public void jz(String label) {
        this.writeLine("jz " + label);
    }

    public void jmp(String label) {
        this.writeLine("jmp " + label);
    }

    // Operations

    public void add() {
        this.writeLine("add"); // This method is only used with offsets
    }

    public void mul() {
        this.writeLine("mul"); // This method is only used with indexing ops.
    }

    public void mod(Type type) {
        this.writeLine("mod" + type.suffix());
    }

    public void sub(Type type) {
        this.writeLine("sub" + type.suffix());
    }

    public void not() {
        this.writeLine("not");
    }

    public void arithmetic(String operator, Type type) {
        this.writeLine(switch (operator) {
            case "+" -> "add";
            case "-" -> "sub";
            case "*" -> "mul";
            case "/" -> "div";
            default -> throw new UnsupportedOperationException("Unknown operator: " + operator);
        } + type.suffix());
    }

    public void comparison(String operator, Type type) {
        this.writeLine(switch (operator) {
            case ">" -> "gt";
            case "<" -> "lt";
            case ">=" -> "ge";
            case "<=" -> "le";
            case "==" -> "eq";
            case "!=" -> "ne";
            default -> throw new UnsupportedOperationException("Unknown operator: " + operator);
        } + type.suffix());
    }

    public void logical(String operator) {
        this.writeLine(switch (operator) {
            case "&&" -> "and";
            case "||" -> "or";
            default -> throw new UnsupportedOperationException("Unknown operator: " + operator);
        });
    }

    // Conversions

    public void b2i() {
        this.writeLine("b2i");
    }

    public void i2f() {
        this.writeLine("i2f");
    }

    public void i2b() {
        this.writeLine("i2b");
    }

    public void f2i() {
        this.writeLine("f2i");
    }

}
