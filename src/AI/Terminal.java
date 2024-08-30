package AI;

import java.util.Scanner;

public class Terminal {
    public static void main(String[] args) {
        Model model = new Model();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Prompt: ");
            String text = scanner.nextLine();
            model.generate(text);
        }
    }
}

