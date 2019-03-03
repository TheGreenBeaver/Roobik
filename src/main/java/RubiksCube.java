class RubiksCube {

    private int size;
    private Face[] faces;
    /*
    faces[0] = back
    faces[1] = left
    faces[2] = front
    faces[3] = right
    faces[4] = up
    faces[5] = down
    */

    RubiksCube(int size) {
        this.size = size;
        faces = new Face[6];
        for (int i = 0; i < 6; i++)
            faces[i] = new Face(size, i);
    }

    public void rotateWholeCube(int axis, int clockwise) {
        //0 = around y-axis, 1 = around x-axis, 2 = around z-axis
        //clockwise == 1 <=> rotate clockwise, clockwise == -1 <=> rotate counter-clockwise
        switch (axis) {
            case 0: {
                Face buffer = faces[0];
                for (int i = 0; i < 4; i++) {

                }
            }
        }
    }

    public void rotateWholeCube(int axis){

    }

//    public void rotateRowOrColumn(boolean isRow, int number, boolean clockwise) {
//        if (isRow) {
//            int[] buffer =
//        }
//    }

    public void getFace(int faceNumber) {
        int[][] faceToShow = faces[faceNumber].getMatrix();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++)
                System.out.print(faceToShow[i][j]+" ");
            System.out.println();
        }
    }
}
