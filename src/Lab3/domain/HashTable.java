package Lab3.domain;

import java.util.Arrays;

public class HashTable {

    /**
     * The symbol table will be represented as a hash table.
     * The possible collisions will be solved using a linked list.
     * The hash function we will use will be computed calculating the modulus
     * of the ASCII code of the character from the corresponding token, divided
     * by T, which is the size of the token list.
     */

    private String[] symbolTable;
    private int capacity;

    public HashTable(int capacity) {
        this.capacity = capacity;
        this.symbolTable = new String[capacity];
    }

    /**
     * This function computes the modulus of the ASCII token code by the capacity (size)
     * @param identifier
     * @return
     */

    private int hashingFunction(String identifier) {
        int sum = 0;
        for(int i = 0; i < identifier.length(); i++) {
            sum += identifier.charAt(i);
        }
        return sum % this.capacity;
    }

    public boolean insert(String identifier) {

        // Check if already in sym table
        for (String s : symbolTable) {
            if (s != null && s.equals(identifier)) {
                System.out.println("Already in sym table.");
                return false;
            }
        }

        int hashValue = hashingFunction(identifier);
        if (symbolTable[hashValue] == null) {
            symbolTable[hashValue] = identifier;
            System.out.println("Insert " + identifier + " at position " + hashValue);
            return true;
        }

        // Else, we have a collision
        int nextAvailablePosition = hashValue;
        while (symbolTable[nextAvailablePosition] != null) {
            nextAvailablePosition++;
        }

        if (symbolTable[nextAvailablePosition] == null) {
            symbolTable[nextAvailablePosition] = identifier;
            System.out.println("Insert " + identifier + " at position " + nextAvailablePosition);
            return true;
        }

        System.out.println("Insert failed.");
        return false;
    }

    public String[] getSymbolTable() {
        return symbolTable;
    }

    @Override
    public String toString() {
        return "HashTable{" +
                "symTable=" + Arrays.toString(symbolTable) +
                '}';
    }
}
