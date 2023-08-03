import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Test class that runs the tests for 5 FAs so as to determine if given strings
 * belong to the associated language for each FA.
 */
public class Test {

    static Integer states;
    static Boolean[] finalSBooleans;
    static ArrayList<String> alpha;
    static ArrayList<Integer> alp;

    // Test method that runs all 4 required FAs, as well as the example FA that was
    // provided which was used in intial testing of program.
    public static void main(String[] args) throws FileNotFoundException {
        // Example FA
        Scanner data = new Scanner(new File("M1.txt"));
        System.out.println("\nTest FA:");
        setup(data);
        data.close();

        // M1 FA
        data = new Scanner(new File("M1.txt"));
        System.out.println("\nM1 FA:");
        setup(data);
        data.close();

        // M2 FA
        data = new Scanner(new File("M2.txt"));
        System.out.println("\nM2 FA:");
        setup(data);
        data.close();

        // M3 FA
        data = new Scanner(new File("M3.txt"));
        System.out.println("\nM3 FA:");
        setup(data);
        data.close();

        // M4 FA
        data = new Scanner(new File("M4.txt"));
        System.out.println("\nM4 FA:");
        setup(data);
        data.close();
    }

    // Contains the algorithm that parses the inputted .txt file and creates the
    // corresponding FA and tests strings provided.
    public static void setup(Scanner data) {
        // Creates the Array holding the states.
        Integer num = data.nextInt();
        State[] states = new State[num];

        // Populates the array while also outputting info about the states.
        System.out.println("Inputted Finite State Automation Info:");
        for (int index = 0; index < states.length; index++) {
            states[index] = new RtState();
            if (index == 0) {
                System.out.print("1) Set of States: {State " + index + ", ");
            } else if (index == states.length - 1) {
                System.out.print("State " + index + "}, Initial State is State 0 (Default)\n");
            } else {
                System.out.print("State " + index);
            }
        }

        // Assigns which states are final states as well as outputting that info.
        System.out.print("2) Set of Final State(s): {");
        while (data.hasNextInt()) {
            Integer index = data.nextInt();
            states[index] = new RtState(true);
            if (index == states.length - 1) {
                System.out.print("State " + index + "}\n");
            } else {
                System.out.print("State " + index + ", ");
            }
        }

        // Takes in the alphabet for the language and also outputs that info.
        alpha = new ArrayList<>();
        String a;
        while (data.hasNext() && (a = data.next()).length() == 1) {
            alpha.add(a);
        }
        System.out.print("3) Alphabet set: {");
        for (int index = 0; index < alpha.size(); index++) {
            if (index == states.length - 1) {
                System.out.print(alpha.get(index) + "}\n");
            } else {
                System.out.print(alpha.get(index) + ", ");
            }
        }

        // Sets up the transitions and also outputs that info.
        System.out.println("4) Transitions:");
        String str;
        while (data.hasNext() && !((str = data.next()).equals("-----"))) {
            if (str.length() > 1) {
                Integer f = Integer.valueOf(String.valueOf(str.charAt(1)));
                String t = String.valueOf(str.charAt(3));
                Integer e = Integer.valueOf(String.valueOf(str.charAt(5)));
                states[f] = states[f].with(new RtTransition(t, states[e]));
                System.out.println(f + " " + t + " " + e);
            }
        }

        // Tests the test strings given and outputs them along with wether they're
        // accepted by the FA.
        FiniteStateMachine machine = new RtFiniteStateMachine(states[0]);
        System.out.println("\nResult of Test Strings: ");
        while (data.hasNext()) {
            try {
                String word = data.next();
                System.out.print(word + "     ");
                for (int i = 0; i < word.length(); i++) {
                    machine = machine.switchState(String.valueOf(word.charAt(i)));
                }
                if (machine.canStop()) {
                    System.out.println("Accept");
                } else {

                    System.out.println("Reject");
                }
            } catch (IllegalArgumentException iae) {
                System.out.print("Reject");
            }
        }
        System.out.println("");
    }
}