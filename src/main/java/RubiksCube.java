class RubiksCube {

    private static final int FACES_AMOUNT = 6;

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
        faces = new Face[FACES_AMOUNT];
        for (int i = 0; i < FACES_AMOUNT; i++)
            faces[i] = new Face(size, i);
    }

    // 90 degrees rotation
    void rotateLayer(int axis, int layerNumber, boolean clockwise) {
        //0 = around y-axis, 1 = around x-axis, 2 = around z-axis
        if (layerNumber < 0 || layerNumber >= size)
            throw new IllegalArgumentException("The value of layerNumber can only be in 0-"
                    + (size - 1) + " range. Actually was " + layerNumber);
        switch (axis) {
            case 0: {
                if (clockwise) {
                    int[] buffer = faces[0].getLine(layerNumber, true);
                    for (int i = 0; i < 3; i++) {
                        faces[i].setLine(faces[i + 1].getLine(layerNumber, true), layerNumber, true);
                    }
                    faces[3].setLine(buffer, layerNumber, true);
                } else {
                    int[] buffer = faces[3].getLine(layerNumber, true);
                    for (int i = 3; i > 0; i--) {
                        faces[i].setLine(faces[i - 1].getLine(layerNumber, true), layerNumber, true);
                    }
                    faces[0].setLine(buffer, layerNumber, true);
                }
                break;
            }
            case 1: {
                break;
            }
            case 2: {
                break;
            }
            default: {
                throw new IllegalArgumentException("The value of axis can only be 0, 1 or 2. Actually was " + axis);
            }
        }
    }

    // 180 degrees rotation
    void rotateLayer(int axis, int layerNumber) {
        if (layerNumber < 0 || layerNumber >= size)
            throw new IllegalArgumentException("The value of layerNumber can only be in 0-"
                    + (size - 1) + " range. Actually was " + layerNumber);
        switch (axis) {
            case 0: {
                break;
            }
            case 1: {
                break;
            }
            case 2: {
                break;
            }
            default: {
                throw new IllegalArgumentException("The value of axis can only be 0, 1 or 2. Actually was " + axis);
            }
        }
    }

    // 90 degrees rotation
    void rotateWholeCube(int axis, boolean clockwise) {
        //0 = around y-axis, 1 = around x-axis, 2 = around z-axis
        switch (axis) {
            case 0: {
                faces[4].rotate(clockwise);
                faces[5].rotate(clockwise);
                if (clockwise) {
                    int[][] buffer = faces[0].getFace();
                    for (int i = 0; i < 3; i++) {
                        faces[i].setFace(faces[i + 1]);
                    }
                    faces[3].setMatrix(buffer);
                } else {
                    int[][] buffer = faces[3].getFace();
                    for (int i = 3; i > 0; i--) {
                        faces[i].setFace(faces[i - 1]);
                    }
                    faces[0].setMatrix(buffer);
                }
                break;
            }
            case 1: {
                faces[1].rotate(clockwise);
                faces[3].rotate(clockwise);
                int[][] buffer = faces[0].getFace();
                if (clockwise) {
                    faces[0].setFace(faces[4]);
                    faces[4].setFace(faces[2]);
                    faces[2].setFace(faces[5]);
                    faces[5].setMatrix(buffer);
                } else {
                    faces[0].setFace(faces[5]);
                    faces[5].setFace(faces[2]);
                    faces[2].setFace(faces[4]);
                    faces[4].setMatrix(buffer);
                }
                break;
            }
            case 2: {
                faces[2].rotate(clockwise);
                faces[0].rotate(clockwise);
                int[][] buffer = faces[4].getFace();
                if (clockwise) {
                    faces[4].setFace(faces[1]);
                    faces[1].setFace(faces[5]);
                    faces[5].setFace(faces[3]);
                    faces[3].setMatrix(buffer);
                }
                else {
                    faces[4].setFace(faces[3]);
                    faces[3].setFace(faces[5]);
                    faces[5].setFace(faces[1]);
                    faces[1].setMatrix(buffer);
                }
                break;
            }
            default: {
                throw new IllegalArgumentException("The value of axis can only be 0, 1 or 2. Actually was " + axis);
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
            default: {
                throw new IllegalArgumentException("The value of axis can only be 0, 1 or 2. Actually was " + axis);
            }
        }
    }

    int[][] getFace(int faceNumber) {
        if (faceNumber < 0 || faceNumber >= FACES_AMOUNT)
            throw new IllegalArgumentException("The value of faceNumber can only be in 0-5 range. Actually was " + faceNumber);
        return faces[faceNumber].getFace();
    }
}
