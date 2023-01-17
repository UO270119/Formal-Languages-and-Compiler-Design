package Lab2;

import Lab2.domain.HashTable;

public class Main {

    public static void main(String[] args) {
        HashTable ht = new HashTable(10);

        // Print the initial status
        System.out.println(ht.toString());

        // Insert some elements
        ht.insert("3");
        ht.insert("5");
        ht.insert("22");

        System.out.println(ht.toString());

        ht.insert("18");
        ht.insert("21");
        ht.insert("15");

        System.out.println(ht.toString());

        ht.insert("7");
        ht.insert("9");

        // Inserting element already present in symbol table, should fail
        ht.insert("15");

        System.out.println(ht.toString());
    }
}
