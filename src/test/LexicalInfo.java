package test;

class LexicalInfo {

    private final int line, column, tokenKey;
    private final String lexeme;
    public final Object semanticValue;

    public LexicalInfo(int line, int column, String lexeme, int tokenKey, Object semanticValue) {
        this.line = line;
        this.column = column;
        this.lexeme = lexeme;
        this.tokenKey = tokenKey;
        this.semanticValue = semanticValue;
    }

    @Override
    public String toString() {
        return "(line " + this.line +
                ", column " + this.column +
                ", lexeme '" + this.lexeme + "'" +
                ", token key " + this.tokenKey +
                ", semantic value " + this.semanticValue +
                ")";
    }

    public void assertEquals(int line, int column, String lexeme, int tokenKey, Object semanticValue) {
        assert this.line == line : "Assert failed in token " + this + ". Expected line " + this.line + ", obtained " + line + ".";
        assert this.column == column : "Assert failed in token " + this + ". Expected column " + this.column + ", obtained " + column + ".";
        assert this.lexeme.equals(lexeme) : "Assert failed in token " + this + ". Expected lexeme " + this.lexeme + ", obtained " + lexeme + ".";
        assert this.tokenKey == tokenKey : "Assert failed in token " + this + ". Expected token name " + this.tokenKey + ", obtained " + tokenKey + ".";
        assert this.semanticValue.equals(semanticValue) : "Assert failed in token " + this + ". Expected semanticValue " + this.semanticValue + ", obtained " + semanticValue + ".";
    }
}
