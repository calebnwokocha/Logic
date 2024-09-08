package AI;

import java.util.*;

public class Model {
    private String memory = "";

    public void generate(String input) {
        memory += " " + input;
        String[][] latinSquare = latinSquare();
        int stochasticValue = new Random().nextInt(latinSquare.length);
        System.out.print("AI: ");
        for (int value = 0; value < stochasticValue; value++) {
            System.out.print(latinSquare[stochasticValue][value] + " ");
        }
        System.out.println();
    }

    private String[][] latinSquare() {
        String[] words = memory.trim().split("\\s+");
        int n = words.length;
        String[][] latinSquare = new String[n][n];

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                latinSquare[row][col] = words[(row + col) % n];
            }
        }

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                System.out.print(latinSquare[row][col] + " ");
            } System.out.println();
        }

        return latinSquare;
    }
}
