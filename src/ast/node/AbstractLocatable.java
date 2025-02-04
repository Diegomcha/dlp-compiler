package ast.node;

public abstract class AbstractLocatable implements Locatable{

    private final int line;
    private final int col;

    protected AbstractLocatable(int line, int col) {
        this.line = line;
        this.col = col;
    }

    @Override
    public int getLine() {
        return this.line;
    }

    @Override
    public int getCol() {
        return this.col;
    }
}
