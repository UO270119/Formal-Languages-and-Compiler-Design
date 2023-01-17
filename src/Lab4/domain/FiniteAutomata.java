package Lab4.domain;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FiniteAutomata {

    private List<String> setOfStates;
    private List<String> alphabet;
    private List<Transition> transitionList;
    private List<String> finalStates;
    private String initialState;

    private String filename;

    private final static String FINITE_AUTOMATA = "C:\\Users\\UO270119\\Desktop\\Lab_formal_fixed\\Lab_formal\\Lab\\src\\Lab4\\input\\fa.in";

    public FiniteAutomata(String filename) {
        this.filename = filename;
        this.setOfStates = new ArrayList<String>();
        this.alphabet = new ArrayList<String>();
        this.transitionList = new ArrayList<Transition>();
        this.finalStates = new ArrayList<String>();
        this.initialState = "";
    }

    public void readFromFile() throws FileNotFoundException {
        File f = new File(this.filename);
        Scanner scanner = new Scanner(f);

        String setOfStatesTxt = scanner.nextLine();
        String setOfStates = scanner.nextLine();
        this.setOfStates = Arrays.asList(setOfStates.split(","));

        String alphabetTxt = scanner.nextLine();
        String alphabet = scanner.nextLine();
        this.alphabet = Arrays.asList(alphabet.split(","));

        String transitionTxt = scanner.nextLine();
        String transition = "";

        // process all transitions

        while(true) {
            transition = scanner.nextLine();
            if(transition.equals("FINAL STATES")) {
                break;
            }

            List<String> transitions = Arrays.asList(transition.split(","));
            Transition model = new Transition();
            model.setStartingState(transitions.get(0));
            model.setValue(transitions.get(1));
            List<String> endingStates = new ArrayList<String>();
            for (int i = 2; i < transitions.size(); i++) {
                endingStates.add(transitions.get(i));
            }
            model.setEndingState(endingStates);

            this.transitionList.add(model);
        }

        // compute the final states

        String finalStates = scanner.nextLine();
        this.finalStates = Arrays.asList(finalStates.split(";"));

        // read the initial state
        String initialState = scanner.nextLine();
        this.initialState = scanner.nextLine();

        scanner.close();
    }

    public boolean isDFA() {
        for (Transition t : transitionList) {
            if (t.getEndingState().size() > 1) {
                return false;
            }
        }
        return true;
    }

    public boolean isAccepted (String sequence) {
        String currentState = this.initialState;
        String[] s = sequence.split("");
        for (String character : s) {
            String nextState = nextState(currentState, character);

            System.out.println(currentState + " " + character + " " + nextState);

            // if no state
            if(nextState.equals("No state")) {
                return false;
            }

            currentState = nextState;

        }

        // if final state
        return this.finalStates.contains(currentState);
    }

    private String nextState (String startState, String value) {
        for(Transition t: transitionList) {
            if (t.getStartingState().equals(startState) && t.getValue().equals(value)) {
                if(t.getEndingState().size() == 1) {
                    return t.getEndingState().get(0);
                }
            }
        }
        return "No state";
    }

    public List<String> getSetOfStates() {
        return setOfStates;
    }

    public List<String> getAlphabet() {
        return alphabet;
    }

    public List<Transition> getTransitionList() {
        return transitionList;
    }

    public List<String> getFinalStates() {
        return finalStates;
    }

    public String getInitialState() {
        return initialState;
    }
}
