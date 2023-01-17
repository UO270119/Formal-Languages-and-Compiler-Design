package Lab3;

import Lab3.domain.LexicalScanner;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        LexicalScanner scanner = new LexicalScanner("programs/p1.txt");
        scanner.scan();

        scanner.classifyTokens();

        scanner.writeToSymbolTable();
    }
}
