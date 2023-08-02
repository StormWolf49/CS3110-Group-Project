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
        Integer num = data.nextInt();
        State[] states = new State[num];
        for(int index = 0; index < states.length; index++) {
            states[index] = new RtState();
        }
        while(data.hasNextInt()) {
            states[data.nextInt()] = new RtState(true);
        }
        for(int index = 0; index < states.length; index++) {
            System.out.println(states[index]);
        }
        alpha = new ArrayList<String>();
        String a;
        while(data.hasNext() && (a = data.next()).length() == 1) {
            alpha.add(a);
        }
        for(int index = 0; index < alpha.size(); index++) {
            System.out.println(alpha.get(index));
        }
        alp = new ArrayList<Integer>();
        while(data.hasNext()) {
            String str = data.next();
            String z = String.valueOf(str.charAt(0));
            String f = String.valueOf(str.charAt(1));
            if(isNumeric(z)) {
                alp.add(Integer.valueOf(z));
            }
            else if(isNumeric(f)) {
                alp.add(Integer.valueOf(f));
            }
        }
        for(int index = 0; index < alp.size(); index++) {
            System.out.println(alp.get(index));
        }
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}