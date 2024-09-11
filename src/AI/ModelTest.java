package AI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ModelTest {
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
        System.out.println("Output: " + object + " " + memory.get(object) + " " + memory.get(memory.get(object)));
        System.out.println("Memory: " + memory);
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
        ModelTest modelTest = new ModelTest();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("type an object (or type 'exit' to quit): ");
            String inputX = scanner.nextLine();
            if (inputX.equalsIgnoreCase("exit")) {
                break;
            }

            System.out.print("type an object: ");
            String inputY = scanner.nextLine();

            modelTest.input(inputX, inputY);
        }

        scanner.close();
    }
}
