package symboltable;

import java.util.*;

import ast.definition.Definition;

public class SymbolTable {

    private final List<Map<String, Definition>> table = new LinkedList<>();

    /**
     * Create a new symbol table with a global scope
     */
    public SymbolTable() {
        // Insert global scope
        table.add(new HashMap<>());
    }

    /**
     * New scope
     */
    public void set() {
        table.add(new HashMap<>());
    }

    /**
     * Remove current scope
     */
    public void reset() {
        table.removeLast();
    }

    /**
     * Insert definition in current scope
     *
     * @param definition Definition to insert
     * @return Whether the definition was inserted
     */
    public boolean insert(Definition definition) {
        Map<String, Definition> currentScope = table.getLast();

        if (!currentScope.containsKey(definition.getName())) {
            definition.setScope(table.size() - 1);
            currentScope.put(definition.getName(), definition);
            return true;
        }

        return false;
    }

    /**
     * Find definition in any scope
     *
     * @param id Identifier to find
     * @return Definition if found, null otherwise
     */
    public Definition find(String id) {
        for (int i = table.size() - 1; i >= 0; i--) {
            Map<String, Definition> currentScope = table.get(i);
            if (currentScope.containsKey(id))
                return currentScope.get(id);
        }

        return null;
    }

    /**
     * Find definition in current scope
     *
     * @param id Identifier to find
     * @return Definition if found, null otherwise
     */
    public Definition findInCurrentScope(String id) {
        Map<String, Definition> currentScope = table.getLast();
        return currentScope.get(id);
    }
}
