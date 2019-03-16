import java.util.Arrays;

public abstract class UsefulOperations {
    public static void equalizeMatrix(int[][] from, int[][] to) {
        for (int i = 0; i < from.length; i++) {
            to[i] = Arrays.copyOf(from[i], from.length);
        }
    }
    public static void revertArray(int[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            int buffer = array[i];
            array[i] = array[array.length - i - 1];
            array[array.length - i - 1] = buffer;
        }
    }
}
