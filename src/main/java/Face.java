import java.util.Arrays;

class Face {

    private static final int SIZE_LOWER_BOUND = 2;

    private int size;
    private int[][] matrix;

    Face(int size, int colourNumber) {
        this.size = size;
        this.checkSize();
        matrix = new int[size][size];
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                matrix[i][j] = colourNumber;
    }

    private void checkSize() {
        if (size < SIZE_LOWER_BOUND)
            throw new IllegalArgumentException("The size of the cube can't be less than 2. Actual size is " + size);
    }

    int[][] getFace(){
        return matrix;
    }

    void setMatrix(int[][] newMatrix) {
        for (int i = 0; i < size; i++) {
            matrix[i] = Arrays.copyOf(newMatrix[i], size);
        }
    }

    void setFace(Face newFace) {
        this.setMatrix(newFace.getFace());
    }

    // 90 degrees rotation
    void rotate(boolean clockwise) {
        int[][] rotated = new int[size][size];
        if (clockwise) {
            for (int i = 0; i < size; i++)
                for (int j = 0; j < size; j++)
                    rotated[i][j] = matrix[size - j - 1][i];
        } else {
            for (int i = 0; i < size; i++)
                for (int j = 0; j < size; j++)
                    rotated[i][j] = matrix[j][size - i - 1];
        }
        this.setMatrix(rotated);
    }

    // 180 degrees rotation
    void rotate() {
        int[][] rotated = new int[size][size];
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                rotated[i][j] = matrix[size - 1 - j][size - 1 - i];
        this.setMatrix(rotated);
    }

    int[] getLine(int number, boolean horizontal) {
        if (horizontal)
            return matrix[number];
        else {
            int[] result = new int[size];
            for (int i = 0; i < size; i++)
                result[i] = matrix[i][number];
            return result;
        }
    }

    void setLine(int[] newLine, int number, boolean horizontal) {
        if (horizontal)
            matrix[number] = Arrays.copyOf(newLine, size);
        else
            for (int i = 0; i < size; i++)
                matrix[i][number] = newLine[i];
    }
}
