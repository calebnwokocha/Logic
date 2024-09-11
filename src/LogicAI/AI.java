package LogicAI;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AI {
    private final List<byte[]> memory = new ArrayList<>();
    private List<byte[]> attention = new ArrayList<>();

    public void train(String input, String objective) {
        byte[] codes = encode(input);
        memory.add(codes);
        attend();
        optimize(objective);

        System.out.println("AI: " + decode(codes));
    }

    private byte[] encode (String string) {
        return string.getBytes(StandardCharsets.UTF_8);
    }

    private String decode (byte[] bytes) {
        return new String(bytes);
    }

    private void attend () {
        for (int i = 0; i < memory.size(); i++) {
            for (int j = 0; j < memory.get(i).length; j++) {
                attention.get(i)[j] = 0;
                for (int k = 0; k < memory.get(i).length; k++) {
                    try {
                        attention.get(i)[j] += (byte) (memory.get(i)[k] * memory.get(k)[j]);
                    } catch (NullPointerException e) {
                        attention.get(i)[j] = 0;
                    }
                }
            }
        }
    }

    private void optimize (String objective) {
// Simulated Backpropagation Process

        // Encode the objective
        byte[] objectiveCodes = encode(objective);

        // Calculate loss (a simplified version using the sum of differences between memory and objective)
        double loss = 0.0;
        for (int i = 0; i < memory.size(); i++) {
            for (int j = 0; j < memory.get(i).length; j++) {
                double diff = memory.get(i)[j] - objectiveCodes[j % objectiveCodes.length];
                loss += diff * diff; // Squared error
            }
        }
        loss /= memory.size(); // Average loss

        System.out.println("Loss before optimization: " + loss);
/*
        // Backpropagate to update the weights
        double learningRate = 0.01;
        for (int i = 0; i < weights.size(); i++) {
            double gradient = 0.0;
            for (int j = 0; j < memory.size(); j++) {
                gradient += (memory.get(j)[i % memory.get(j).length] - objectiveCodes[i % objectiveCodes.length]);
            }
            gradient /= memory.size(); // Average gradient

            // Update weight based on gradient descent
            weights.set(i, weights.get(i) - learningRate * gradient);
        }

        // After optimization, print the updated weights
        System.out.println("Updated weights: " + weights);*/
    }

    public static void main(String[] args) {
        AI model = new AI();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("You: ");
            String text = scanner.nextLine();
            //model.train(text);
        }
    }
}
