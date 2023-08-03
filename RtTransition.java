/**
 * Transition in finite state machine.
 */
public final class RtTransition implements Transition {

    private String rule;
    private State next;

    // Creates a transition object that holds the transition information as a single
    // object whe given the rule and the next state.
    public RtTransition(String rule, State next) {
        this.rule = rule;
        this.next = next;
    }

    // Returns the state this transition is leading to.
    public State state() {
        return this.next;
    }

    // Returns a bolean value according to if the character inputted matches the
    // rule for this transition.
    public boolean isPossible(CharSequence c) {
        return this.rule.equalsIgnoreCase(String.valueOf(c));
    }

}
