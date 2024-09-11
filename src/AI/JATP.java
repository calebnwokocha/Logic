package AI;

import java.io.*;
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
        this.load();
    }

    public void input(Object leftObject, Object rightObject) {
        if (this.memory.containsKey(leftObject)) {
            output(leftObject);
        } else {
            this.memory.put(leftObject, rightObject);
            prove(leftObject);
        }
    }

    private void output(Object leftObject) {
        System.out.println("equation found: " + leftObject + "=" + this.memory.get(leftObject)
                + "=" + this.memory.get(this.memory.get(leftObject)));
        System.out.println("memory map: " + this.memory);
    }

    private void prove(Object leftObject) {
        ArrayList<Object> leftObjects = new ArrayList<>(this.memory.keySet());
        for (Object object : leftObjects) {
            Object rightObject = this.memory.get(object);
            if (!object.equals(leftObject) && rightObject.equals(this.memory.get(leftObject))) {
                this.memory.replace(leftObject, object);
            }
        }
    }

    private void save() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("memory"))) {
            oos.writeObject(memory);
            System.out.println("memory map saved");
        } catch (IOException e) {
            System.out.println("error saving memory map: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void load() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("memory"))) {
            HashMap<Object, Object> loadedMemory = (HashMap<Object, Object>) ois.readObject();
            memory.clear();
            memory.putAll(loadedMemory);
            System.out.println("memory map loaded");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("error loading memory map: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        JATP JATP = new JATP();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("left object: ");
            String leftObject = scanner.nextLine();
            System.out.print("right object: ");
            String rightObject = scanner.nextLine();

            if (leftObject.equalsIgnoreCase("exit") ||
                    rightObject.equalsIgnoreCase("exit") ) {
                break;
            } else if (leftObject.equalsIgnoreCase("save") ||
                    rightObject.equalsIgnoreCase("save") ) {
                JATP.save();
            } else {
                JATP.input(leftObject, rightObject);
            }
        }

        scanner.close();
    }
}
