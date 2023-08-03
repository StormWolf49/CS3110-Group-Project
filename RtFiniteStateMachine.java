/**
 * Default implementation of a finite state machine.
 * This class is immutable and thread-safe.
 */
public final class RtFiniteStateMachine implements FiniteStateMachine {

    private State current;

    // Constructs the FA machine by being given the initial state, with the
    // implication that all the transitions have already been created.
    public RtFiniteStateMachine(final State initial) {
        this.current = initial;
    }

    // Attempts to move to another state through the submitted character, and
    // returns a new version of the FA machine.
    public FiniteStateMachine switchState(final CharSequence c) {
        return new RtFiniteStateMachine(this.current.transit(c));
    }

    // Checks to see if the current state of the FA machine is a final state, and
    // implied to be only called after a string has been completly been tested.
    public boolean canStop() {
        return this.current.isFinal();
    }

}
