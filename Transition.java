/**
 * Transition in a finite State machine.
 */
public interface Transition {

    // Returns a bolean value according to if the character inputted matches the
    // rule for this transition.
    boolean isPossible(final CharSequence c);

    // Returns the state this transition is leading to.
    State state();
}
