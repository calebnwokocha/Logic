package AI;

import java.util.*;

public class Model {
    private String memory = "";

    public void generate(String input) {
        memory += " " + input;
        String[][] latinSquare = latinSquare();
        int randomNum = new Random().nextInt(latinSquare.length);
        System.out.print("AI: ");
        for (int i = 0; i < randomNum; i++) {
            System.out.print(latinSquare[i][i] + " ");
        }
        System.out.println();
    }

    private String[][] latinSquare() {
        String[] words = memory.trim().split("\\s+");
        List<String> uniqueWords = new ArrayList<>(new LinkedHashSet<>(Arrays.asList(words)));
        int n = uniqueWords.size();
        String[][] latinSquare = new String[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                latinSquare[i][j] = uniqueWords.get((i + j) % n);
            }
        }
        return latinSquare;
    }
}
