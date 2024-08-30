package AI;

import java.util.*;

public class Model {
    private String memory = "";

    public void generate(String input) {
        this.memory = memory + " " + input;
        String[][] latinSquare = latinSquare();
        int min = 0; int max = latinSquare.length - 1;
        int randomNum = new Random().nextInt((max - min) + 1) + min;
        String[] output = latinSquare[randomNum];
        System.out.print("AI: ");
        for (int i = 0; i < randomNum; i++) {
            System.out.print(output[i] + " ");
        }
        System.out.println();
    }

    private String[][] latinSquare() {
        String[] words = this.memory.split(" ");
        Set<String> uniqueWordsSet = new LinkedHashSet<>(Arrays.asList(words));
        List<String> uniqueWordsList = new ArrayList<>(uniqueWordsSet);
        int n = uniqueWordsList.size();
        String[][] latinSquare = new String[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                latinSquare[i][j] = uniqueWordsList.get((i + j) % n);
            }
        }
        return latinSquare;
    }
}

