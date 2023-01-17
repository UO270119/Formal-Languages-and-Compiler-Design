package Lab3.domain;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.*;

public class LexicalScanner {

    private final static String TOKENS_FILENAME = "C:\\Users\\UO270119\\Desktop\\Lab_formal_fixed\\Lab_formal\\Lab\\src\\Lab3\\input\\token.in";
    private final static String PIF_FILENAME = "C:\\Users\\UO270119\\Desktop\\Lab_formal_fixed\\Lab_formal\\Lab\\src\\Lab3\\output\\pif";
    private final static String ST_FILENAME = "C:\\Users\\UO270119\\Desktop\\Lab_formal_fixed\\Lab_formal\\Lab\\src\\Lab3\\output\\st";
    private final static String ROOT_FOLDER = "C:\\Users\\UO270119\\Desktop\\Lab_formal_fixed\\Lab_formal\\Lab\\src\\Lab3\\";



    private class DetectedToken {
        public final String token;
        public final int line;

        public DetectedToken(String token, int line) {
            this.token = token;
            this.line = line;
        }
    }


    private String filename;
    private List<String> tokenList = new ArrayList<String>();
    private List<String> separatorList = new ArrayList<String>();

    private List<DetectedToken> detectedTokens = new ArrayList<DetectedToken>();

    private Map<String, Integer> PIF = new HashMap<String, Integer>();
    private Map<Integer, String> ST = new HashMap<Integer, String>();

    private boolean isStringLexicallyCorrect = true;
    private boolean isCharLexicallyCorrect = true;

    // relational operators
    private List<String> simpleOperators = new ArrayList<String>();
    private List<String> doubleOperators = new ArrayList<String>();

    // constants
    private String stringConstant = "";
    private String charConstant = "";
    private int currentLine = 0;
    private int capacity = 97;

    private HashTable hashTable = new HashTable(capacity);

    public LexicalScanner(String filename) {
        this.filename = filename;

        // read relationals
        this.simpleOperators.add("<");
        this.simpleOperators.add(">");
        this.doubleOperators.add("<=");
        this.doubleOperators.add(">=");
        this.doubleOperators.add("==");

        // read tokens
        this.readTokens();

        // read separators
        this.readSeparators();
    }

    public void scan() {
        try {
            File object = new File(ROOT_FOLDER + this.filename);
            Scanner read = new Scanner(object);
            while (read.hasNextLine()) {
                String line = read.nextLine();
                Scanner data = new Scanner(line);
                currentLine++;
                while (data.hasNext()) {
                    String word = data.next();
                    boolean hasSeparator = false;

                    for (String separator : separatorList) {
                        if(word.contains(separator)) {
                            hasSeparator = true;
                            this.splitWordWithSeparator(word, separator, currentLine);
                            break;
                        }
                    }

                    if (!hasSeparator && !isStringLexicallyCorrect) {
                        stringConstant += word + " ";
                    }

                    if(!hasSeparator && !isCharLexicallyCorrect) {
                        charConstant += word + " ";
                    }

                    if(!hasSeparator && isStringLexicallyCorrect && isCharLexicallyCorrect) {
                        addDetectedToken(word, currentLine);
                    }
                }
            }
            read.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }

    private void readTokens() {
        try {
            File object = new File(TOKENS_FILENAME);
            Scanner read = new Scanner(object);
            while (read.hasNextLine()) {
                String data = read.nextLine();
                tokenList.add(data);
            }
            read.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }

    private void readSeparators() {
        try {
            File object = new File(TOKENS_FILENAME);
            Scanner read = new Scanner(object);
            for (int i = 0; i < 28; i++) {
                String data = read.nextLine();
                separatorList.add(data);
            }
        } catch (FileNotFoundException e) {
            System.out.println();
        }
    }

    private boolean isConstant(String token) {
        return token.matches("\\-?[1-9]+[0-9]*|0")
                || token.matches("\"[a-zA-Z0-9 _]+\"")
                || token.equals("True")
                || token.equals("False");
    }

    private boolean isStringConstant (String token) {
        if (token.charAt(0) == '"' && token.charAt(token.length() - 2) == '"') {
            if (token.length() == 2) // empty strings
                return true;
            String noQuote = token.substring(1, token.length() - 2);
            return noQuote.length() > 1;
        } else {
            return false;
        }
    }

    private boolean isCharConstant (String token) {
        if (String.valueOf(token.charAt(0)).equals("'") && String.valueOf(token.charAt(token.length() - 2)).equals("'")) {
            String noQuote = token.substring(1, token.length() - 2);
            return noQuote.length() <= 1;
        } else {
            return false;
        }
    }

    private boolean isIdentifier(String token) {
        return token.matches("(^[a-zA-Z][a-zA-Z0-9 _]*)");
    }

    private boolean isReservedOperatorOrSeparator (String token) {
        for (String t : this.tokenList) {
            if(t.equals(token)) {
                return true;
            }
        }
        return false;
    }


    private void addDetectedToken(String token) {
        this.detectedTokens.add(new DetectedToken(token, this.currentLine));
    }

    private void addDetectedToken(String token, int line) {
        this.detectedTokens.add(new DetectedToken(token, line));
    }


    public void classifyTokens() throws FileNotFoundException {
        // A pair is represented by a token and its line
        PrintWriter pw = new PrintWriter(PIF_FILENAME);
        pw.printf("%-20s %s\n", "Token", "ST_Pos");
        Integer lastLine = 0;

        for (DetectedToken pair : this.detectedTokens) {
            if (isReservedOperatorOrSeparator(pair.token)) {
                pw.printf("%-20s RESERVED\n", pair.token);
            } else if (isIdentifier(pair.token) || isConstant(pair.token) || isStringConstant(pair.token) || isCharConstant(pair.token)) {
                hashTable.insert(pair.token);      // index is the position from the symbol table
                pw.printf("%-20s %d\n", pair.token, pair.line);
            } else {
                System.out.println("Lexical error " + pair.token + " at line " + (pair.line));
            }
            lastLine = pair.line;
        }

        if(!isStringLexicallyCorrect) {
            System.out.println("Lexical error: double quotes not closed at line " + lastLine);
        }

        if(!isCharLexicallyCorrect) {
            System.out.println("Lexical error: Single quotes not closed at line " + lastLine);
        }

        pw.flush(); // close
    }

    public void writeToSymbolTable() throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(ST_FILENAME);
        pw.printf("%-20s %s\n", "Symbol Table as:", "Hash Table");
        pw.printf("%-20s %s\n", "Symbol", "ST_Pos");
        String[] symTable = hashTable.getSymbolTable();

        for (int i = 0; i < capacity; i++) {
            if(symTable[i] != null) {
                pw.printf("%-20s %s\n", symTable[i], i);
            }
        }

        pw.flush();
    }

    private void splitWordWithSeparator(String word, String separator, Integer line) {
        String[] splitList;

        boolean specialCase = false;
        boolean containsRelational = false;
        char doubleQuotes = '"';
        String stringDoubleQuotes = String.valueOf(doubleQuotes);

        if(word.contains(stringDoubleQuotes) && !isStringLexicallyCorrect) {
            specialCase = true;
            this.isStringLexicallyCorrect = true;
            if (word.charAt(word.length() - 1) == ';') {
                String newWord = word.substring(0, word.length() - 1);
                stringConstant += newWord + " ";
                addDetectedToken(stringConstant, line);
                addDetectedToken(";", line);
                stringConstant = "";
                return;
            } else {
                stringConstant += word + " ";
                addDetectedToken(stringConstant, line);
                stringConstant = "";
                return;
            }
        }

        if (!isStringLexicallyCorrect) {
            specialCase = true;
            stringConstant += word + " ";
            return;
        }

        if (separator.charAt(0) == '"' && isStringLexicallyCorrect) {
            specialCase = true;
            isStringLexicallyCorrect = false;
            stringConstant += word + " ";
            return;
        }

        if (word.contains("'") && !isCharLexicallyCorrect) {
            specialCase = true;
            this.isCharLexicallyCorrect = true;
            if (word.charAt(word.length() - 1) == ';') {
                String newWord = word.substring(0, word.length() - 1);
                charConstant += newWord + " ";
                addDetectedToken(charConstant, line);
                addDetectedToken(";", line);
                charConstant = "";
                return;
            } else {
                charConstant += word + " ";
                addDetectedToken(charConstant, line);
                charConstant = "";
                return;
            }
        }

        if (!isCharLexicallyCorrect) {
            specialCase = true;
            charConstant += word + " ";
            return;
        }

        if (separator.equals("'") && isCharLexicallyCorrect) {
            specialCase = true;
            isCharLexicallyCorrect = false;
            charConstant += word + " ";
            return;
        }

        if (separator.equals("(")) {
            specialCase = true;
            String[] RHS;
            String[] LHS;
            for (String specialSeparator : this.doubleOperators) {
                if (word.contains(specialSeparator)) {
                    containsRelational = true;
                    splitList = word.split(specialSeparator);
                    RHS = splitList[0].split("\\(");
                    LHS = splitList[1].split("\\)");
                    addDetectedToken("(", line);
                    addDetectedToken(RHS[1], line);
                    addDetectedToken(specialSeparator, line);
                    addDetectedToken(LHS[0], line);
                    addDetectedToken(")", line);
                }
            }
            for (String regularSeparator : this.simpleOperators) {
                if (word.contains(regularSeparator) && !containsRelational) {
                    containsRelational = true;
                    splitList = word.split(regularSeparator);
                    RHS = splitList[0].split("\\(");
                    LHS = splitList[1].split("\\)");
                    addDetectedToken("(", line);
                    addDetectedToken(RHS[1], line);
                    addDetectedToken(regularSeparator, line);
                    addDetectedToken(LHS[0], line);
                    addDetectedToken(")", line);
                }
            }
            if (!containsRelational) {
                splitList = word.split("\\(");
                splitList = splitList[1].split("\\)");
                addDetectedToken(separator, line);
                addDetectedToken(splitList[0], line);
                addDetectedToken(")", line);
            }
        }

        if (separator.equals(")")) {
            specialCase = true;
            splitList = word.split("\\)");
            addDetectedToken(splitList[0], line);
            addDetectedToken(separator, line);
        }

        if (separator.equals("[")) {
            specialCase = true;
            splitList = word.split("\\[");
            addDetectedToken(splitList[0], line);
            addDetectedToken(separator, line);
            String[] LHS = splitList[1].split("\\]");
            if (LHS.length == 1) {
                addDetectedToken(LHS[0], line);
                addDetectedToken("]", line);
            } else if (LHS.length == 2) {
                addDetectedToken(LHS[0], line);
                addDetectedToken("]", line);
                addDetectedToken(LHS[1], line);
            }
        }

        if (separator.equals("?")) {
            addDetectedToken(separator, line);
            specialCase = true;
        }

        if (separator.equals("+")) {
            addDetectedToken(separator, line);
            specialCase = true;
        }

        if (separator.equals(".")) {
            splitList = word.split("\\.");
            addDetectedToken(splitList[0], line);
            addDetectedToken(separator, line);
            specialCase = true;
        }

        if (!specialCase) {
            splitList = word.split(separator);
            if (splitList.length == 0) {
                addDetectedToken(separator, line);
            }
            if (splitList.length == 1) {
                addDetectedToken(splitList[0], line);
                addDetectedToken(separator, line);
            }
            if (splitList.length == 2) {
                if (!splitList[0].equals("")) {
                    addDetectedToken(splitList[0], line);
                }
                addDetectedToken(separator, line);
                addDetectedToken(splitList[1], line);
            }
        }
    }
}
