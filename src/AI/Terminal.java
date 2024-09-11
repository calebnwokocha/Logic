package AI;

import java.util.Scanner;

public class Terminal {
    public static void main(String[] args) {
        ModelTest model = new ModelTest();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("You: ");
            String text = scanner.nextLine();
            //model.generate(text);
        }
    }
}

