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
//    private Face up;
//    private Face down;
//    private Face left;
//    private Face right;
//    private Face front;
//    private Face back;

    RubiksCube(int size) {
        this.size = size;
        faces = new Face[6];
        for (int i = 0; i < 6; i++)
            faces[i] = new Face(size, i);
    }

    public void rotateRow(int number, boolean clockwise) {

    }

    public int[][] getFace(int faceNumber) {
        return faces[faceNumber].getMatrix();
    }
}
