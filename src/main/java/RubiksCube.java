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
    // 90 degrees rotation
    void rotateLayer(boolean horizontal, int layerNumber, boolean clockwiseOrNot) {

    }
    // 180 degrees rotation
    void rotateLayer(boolean horizontal, int layerNumber) {

    }
    // 90 degrees rotation
    void rotateWholeCube(int axis, boolean clockwiseOrNot) {
        //0 = around y-axis, 1 = around x-axis, 2 = around z-axis
        switch (axis) {
            case 0: {
                if (clockwiseOrNot) {
                    int[][] buffer = faces[0].getMatrix();
                    for (int i = 0; i < 3; i++) {
                        faces[i].setMatrix(faces[i + 1].getMatrix());
                    }
                    faces[3].setMatrix(buffer);

                }
                else {
                    int[][] buffer = faces[3].getMatrix();
                    for (int i = 3; i > 0; i--) {
                        faces[i].setMatrix(faces[i - 1].getMatrix());
                    }
                    faces[0].setMatrix(buffer);
                }
                faces[4].rotate(clockwiseOrNot);
                faces[5].rotate(clockwiseOrNot);
                break;
            }
            case 1: {
                faces[1].rotate(clockwiseOrNot);
                faces[3].rotate(clockwiseOrNot);
                int[][] buffer = faces[0].getMatrix();
                if (clockwiseOrNot) {
                    faces[0].setMatrix(faces[4].getMatrix());
                    faces[4].setMatrix(faces[2].getMatrix());
                    faces[2].setMatrix(faces[5].getMatrix());
                    faces[5].setMatrix(buffer);
                }
                else {
                    faces[0].setMatrix(faces[5].getMatrix());
                    faces[5].setMatrix(faces[2].getMatrix());
                    faces[2].setMatrix(faces[4].getMatrix());
                    faces[4].setMatrix(buffer);
                }
                break;
            }
            case 2: {
                faces[2].rotate(clockwiseOrNot);
                faces[0].rotate(clockwiseOrNot);
                int[][] buffer = faces[4].getMatrix();
                if (clockwiseOrNot) {
                    faces[4].setMatrix(faces[1].getMatrix());
                    faces[1].setMatrix(faces[5].getMatrix());
                    faces[5].setMatrix(faces[3].getMatrix());
                    faces[3].setMatrix(buffer);
                }
                else {
                    faces[4].setMatrix(faces[3].getMatrix());
                    faces[3].setMatrix(faces[5].getMatrix());
                    faces[5].setMatrix(faces[1].getMatrix());
                    faces[1].setMatrix(buffer);
                }
                break;
            }
        }
    }
    //180 degrees rotation
    public void rotateWholeCube(int axis) {
        switch (axis) {
            case 0: {
                faces[4].rotate();
                faces[5].rotate();
            }
            case 1: {
                faces[1].rotate();
                faces[3].rotate();
            }
            case 2: {
                faces[0].rotate();
                faces[2].rotate();
            }
        }
    }

    void getFace(int faceNumber) {
        int[][] faceToShow = faces[faceNumber].getMatrix();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++)
                System.out.print(faceToShow[i][j]+" ");
            System.out.println();
        }
    }
}
