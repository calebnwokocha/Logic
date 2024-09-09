package AI;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class NGram {
    private Map<Long, String[]> map = new HashMap<>();


    public boolean model(String input, int gram) {
        makeGram(input, gram);
        return false;
    }

    // Tokenization method to convert a word to a unique long value
    public long tokenize(String word) {
        long number = 0;
        int position = 1;  // Start with position 1 for each character
        for (char ch : word.toCharArray()) {
            int value = ch - 'a' + 1;  // Convert 'a' = 1, ..., 'z' = 26
            number += (long) (value * Math.pow(100, word.length() - position));  // Multiply by a power of 100
            position++;
        }
        return number;
    }

    public void makeGram(String input, int gram) {
        String[] words = input.trim().split("\\s+");
        long[] keys = new long[words.length];

        for (int i = 0; i < keys.length; i++) {
            keys[i] = tokenize(words[i]);

            // Initialize the array to hold the n-gram including the next word
            String[] grams = new String[gram + 1];  // Increase size to hold next word

            // Create the n-gram by looking at the current word, previous words, and the next word
            for (int j = 0; j < gram; j++) {
                int index = i - gram + j + 1;  // Calculate the correct index for the n-gram
                if (index >= 0 && index < words.length) {
                    grams[j] = words[index];  // Fill in previous and current words
                } else {
                    grams[j] = null;  // Handle cases where the n-gram goes out of bounds
                }
            }

            // Include the next word in the last position of the array (if available)
            if (i + 1 < words.length) {
                grams[gram] = words[i + 1];  // Include the next word
            } else {
                grams[gram] = null;  // If there's no next word, fill with null
            }

            // Print the generated n-gram for debugging
            System.out.println("N-gram for word '" + words[i] + "': " + Arrays.toString(grams));

            // Put the tokenized word and its n-gram in the map
            map.put(keys[i], grams);

        }
    }

    public static void main(String[] args) {
        // Example usage of the NGram class
        System.out.print(new NGram().model("Hello world today is awesome. Hello my friend today", 4));
    }
}
