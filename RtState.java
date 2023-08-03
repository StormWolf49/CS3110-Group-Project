import java.util.ArrayList;
import java.util.List;

/**
 * State in a finite state machine.
 */
public final class RtState implements State {

    private List<Transition> transitions;
    private boolean isFinal;

    // Creates a new state with the default value of not being a final state.
    public RtState() {
        this(false);
    }

    // Creates a new state with the user inputted choice if its a final state.
    public RtState(final boolean isFinal) {
        this.transitions = new ArrayList<>();
        this.isFinal = isFinal;
    }

    // Attempts to transition to the next state by using the given character as the
    // rule.
    public State transit(final CharSequence c) {
        return transitions
                .stream()
                .filter(t -> t.isPossible(c))
                .map(Transition::state)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Input not accepted: " + c));
    }

    // A getter method that returns if the state is a final state or not.
    public boolean isFinal() {
        return this.isFinal;
    }

    // Create a transition for the state.
    @Override
    public State with(Transition tr) {
        this.transitions.add(tr);
        return this;
    }

}
