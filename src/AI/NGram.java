/*
import java.lang.reflect.Array;

public class HigherDimensionalTensor {

    // Method to create a tensor with d dimensions, each of size n
    public static Object createTensor(int n, int dimensions) {
        if (dimensions < 1) {
            throw new IllegalArgumentException("Number of dimensions must be at least 1.");
        }
        return createTensorRecursive(n, dimensions);
    }

    // Recursive method to create a tensor of given dimensions
    private static Object createTensorRecursive(int n, int dimensions) {
        if (dimensions == 1) {
            return new int[n];
        }
        Object array = Array.newInstance(int.class, n);
        for (int i = 0; i < n; i++) {
            Array.set(array, i, createTensorRecursive(n, dimensions - 1));
        }
        return array;
    }

    // Utility method to print the tensor
    private static void printTensor(Object tensor, int dimensions, int depth) {
        if (dimensions == 1) {
            int[] array = (int[]) tensor;
            for (int value : array) {
                System.out.print(value + " ");
            }
        } else {
            int length = Array.getLength(tensor);
            for (int i = 0; i < length; i++) {
                if (depth > 0) {
                    printTensor(Array.get(tensor, i), dimensions - 1, depth - 1);
                } else {
                    System.out.println();
                }
            }
        }
    }

    public static void main(String[] args) {
        int n = 2; // Size of each dimension
        int dimensions = 4; // Number of dimensions

        // Create and print a higher-dimensional tensor
        Object tensor = createTensor(n, dimensions);
        System.out.println("Tensor:");
        printTensor(tensor, dimensions, dimensions);
    }
}
*/
