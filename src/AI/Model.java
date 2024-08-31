package AI;

import java.util.*;

public class Model {
    private String memory = "";

    public void generate(String input) {
        memory += " " + input;
        String[][] latinSquare = latinSquare();
        int stochasticNum = new Random().nextInt(latinSquare.length);
        System.out.print("AI: ");
        for (int i = 0; i < stochasticNum; i++) {
            System.out.print(latinSquare[stochasticNum][i] + " ");
        }
        System.out.println();
    }

    private String[][] latinSquare() {
        String[] words = memory.trim().split("\\s+");
        int n = words.length;
        String[][] latinSquare = new String[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                latinSquare[i][j] = words[(i + j) % n];
            }
        }
        return latinSquare;
    }
}
