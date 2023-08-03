import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Test {
    static Integer states;
    static Boolean[] finalSBooleans;
    static ArrayList<String> alpha;
    static ArrayList<Integer> alp;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner data = new Scanner(new File("M1.txt"));
        setup(data);
    }

    public static void setup(Scanner data) {
        System.out.println("Inputted Finite State Automation Info:\n1) Set of States: {");
        Integer num = data.nextInt();
        State[] states = new State[num];
        for (int index = 0; index < states.length; index++) {
            states[index] = new RtState();
            if (index == 0) {
                System.out.print("State " + index + ", ");
            }
            else if (index == states.length - 1) {
                System.out.print("State " + index + "}, Initial State is State 0 (Default)\n");
            }
            else {
                System.out.print("State " + index);
            }
        }
        while (data.hasNextInt()) {
            states[data.nextInt()] = new RtState(true);
        }
        /* for (int index = 0; index < states.length; index++) {
            System.out.println(states[index]);
        } */
        alpha = new ArrayList<>();
        String a;
        while (data.hasNext() && (a = data.next()).length() == 1) {
            alpha.add(a);
        }
        for (int index = 0; index < alpha.size(); index++) {
            System.out.println(alpha.get(index));
        }
        String str;
        while (data.hasNext() && !((str = data.next()).equals("-----"))) {
            if (str.length() > 1) {
                Integer f = Integer.valueOf(String.valueOf(str.charAt(1)));
                String t = String.valueOf(str.charAt(3));
                Integer e = Integer.valueOf(String.valueOf(str.charAt(5)));
                states[f] = states[f].with(new RtTransition(t, states[e]));
            }
        }
        FiniteStateMachine machine = new RtFiniteStateMachine(states[0]);
        while (data.hasNext()) {
            String word = data.next();
            for (int i = 0; i < word.length(); i++) {
                machine = machine.switchState(String.valueOf(word.charAt(i)));
            }
            System.out.println(machine.canStop());
        }
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}