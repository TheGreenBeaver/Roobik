class Face {
    private int size;
    private int[][] matrix;

    Face(int size) {
        this.size = size;
        matrix = new int[size][size];
    }

    public void fill(int colourNumber) {
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                matrix[i][j] = colourNumber;
    }
}
