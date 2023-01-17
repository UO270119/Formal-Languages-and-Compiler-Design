package Lab4.domain;

import java.util.List;

public class Transition {

    private String startingState;
    private String value;
    private List<String> endingState;

    public Transition() {

    }

    public void setStartingState(String startingState) {
        this.startingState = startingState;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setEndingState(List<String> endingState) {
        this.endingState = endingState;
    }

    public String getStartingState() {
        return startingState;
    }

    public String getValue() {
        return value;
    }

    public List<String> getEndingState() {
        return endingState;
    }

    @Override
    public String toString() {
        return "Transition{" +
                "startingState='" + startingState + '\'' +
                ", value='" + value + '\'' +
                ", endingState=" + endingState +
                '}';
    }
}
