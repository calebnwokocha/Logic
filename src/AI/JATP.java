package AI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class JATP {
    private final HashMap<Object, Object> memory = new HashMap<>();

    public void input(Object x, Object y) {
        if (memory.containsKey(x)) {
            output(x);
        } else {
            memory.put(x, y);
            analyse(x);
        }
    }

    private void output(Object object) {
        System.out.println("Generated Equation: " + object + " = " + memory.get(object) + " = " + memory.get(memory.get(object)));
        System.out.println("HashMap Memory: " + memory);
    }

    private void analyse(Object object) {
        ArrayList<Object> keys = new ArrayList<>(memory.keySet());
        for (Object key : keys) {
            Object value = memory.get(key);
            if (!key.equals(object) && value.equals(memory.get(object))) {
                memory.replace(object, key);
            }
        }
    }

    public static void main(String[] args) {
        JATP JATP = new JATP();
        System.out.println("JAVA AUTOMATED THEOREM PROVER (JATP)");
        System.out.println();
        System.out.println("Creator: Caleb Princewill Nwokocha");
        System.out.println();
        System.out.println("Theorem: Every equation has a left object and a right object.");
        System.out.println("Example 1: a is left object of a=b and b is its right object.");
        System.out.println("Example 2: a is left object of a=a and a is its right object.");
        System.out.println("Example 3: a+b is left object of a+b=b+a and b+a is its right object.");
        System.out.println("Example 4: a+b is left object of a+b=a+b and a+b is its right object.");
        System.out.println();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("left object: ");
            String inputX = scanner.nextLine();
            if (inputX.equalsIgnoreCase("exit")) {
                break;
            }

            System.out.print("right object: ");
            String inputY = scanner.nextLine();

            JATP.input(inputX, inputY);
        }

        scanner.close();
    }
}
