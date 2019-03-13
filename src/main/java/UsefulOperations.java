import java.util.Arrays;

public abstract class UsefulOperations {
    public static void equalizeMatrix(int[][] from, int[][] to) {
        for (int i = 0; i < from.length; i++) {
            to[i] = Arrays.copyOf(from[i], from.length);
        }
    }
}
