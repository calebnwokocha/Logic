package AI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class JATP {
    private final HashMap<Object, Object> memory = new HashMap<>();

    public JATP() {
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
    }

    public void input(Object leftObject, Object rightObject) {
        if (memory.containsKey(leftObject)) {
            disprove(leftObject, rightObject);
            output(leftObject);
        } else {
            memory.put(leftObject, rightObject);
            prove(leftObject);
        }
    }

    private void output(Object leftObject) {
        System.out.println("equation generated: " + leftObject + "=" + memory.get(leftObject)
                + "=" + memory.get(memory.get(leftObject)));
        System.out.println("memory map: " + memory);
    }

    private void prove(Object leftObject) {
        ArrayList<Object> leftObjects = new ArrayList<>(memory.keySet());
        for (Object object : leftObjects) {
            Object rightObject = memory.get(object);
            if (!object.equals(leftObject) && rightObject.equals(memory.get(leftObject))) {
                memory.replace(leftObject, object);
            }
        }
    }

    private void disprove(Object leftObject, Object rightObject) {
        if (memory.containsKey(leftObject) && !memory.get(leftObject).equals(rightObject)) {
            System.out.println("contradiction detected: " + leftObject + "≠" + rightObject);
        }
    }

    public static void main(String[] args) {
        JATP JATP = new JATP();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("left object: ");
            String leftObject = scanner.nextLine();
            if (leftObject.equalsIgnoreCase("exit")) {
                break;
            }

            System.out.print("right object: ");
            String rightObject = scanner.nextLine();

            JATP.input(leftObject, rightObject);
        }

        scanner.close();
    }
}
