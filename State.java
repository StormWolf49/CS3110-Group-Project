/**
 * State. Part of a finite state machine.
 */
public interface State {

    // Create a transition for the state.
    State with(final Transition tr);

    // Attempts to transition to the next state by using the given character as the
    // rule.
    State transit(final CharSequence c);

    // A getter method that returns if the state is a final state or not.
    boolean isFinal();
}
