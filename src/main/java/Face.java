class Face {
    private int size;
    private int[][] matrix;

    Face(int size, int colourNumber) {
        this.size = size;
        matrix = new int[size][size];
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                matrix[i][j] = colourNumber;
    }

    int[][] getMatrix(){
        return matrix;
    }

    void setMatrix(int[][] newMatrix) {
        matrix = newMatrix;
    }
    // 90 degrees rotation
    void rotate(boolean clockwiseOrNot) {
        int[][] rotated = new int[size][size];
        if (clockwiseOrNot) {
            for (int i = 0; i < size; i++)
                for (int j = 0; j < size; j++)
                    rotated[i][j] = matrix[size - j - 1][i];
        } else {
            for (int i = 0; i < size; i++)
                for (int j = 0; j < size; j++) {
                    rotated[i][j] = matrix[j][size - i - 1];
                }
        }
        matrix = rotated;
    }
    // 180 degrees rotation
    void rotate() {
        int[][] rotated = new int[size][size];
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                rotated[i][j] = matrix[size - 1 - j][size - 1 - i];
        matrix = rotated;
    }
}
