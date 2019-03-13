class RubiksCube {

    private static final int FACES_AMOUNT = 6;
    private static final int[] Y_AXIS_FACES_ORDER_CLOCKWISE = {0, 4, 2, 5};
    private static final int[] Z_AXIS_FACES_ORDER_CLOCKWISE = {4, 1, 5, 3};
    private static final int[] Y_AXIS_FACES_ORDER_COUNTER_CLOCKWISE = {0, 5, 2, 4};
    private static final int[] Z_AXIS_FACES_ORDER_COUNTER_CLOCKWISE = {4, 3, 5, 1};

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
                int[] buffer = faces[0].getLine(layerNumber, false);
                if (clockwise) {
                    for (int i = 0; i < 2; i++) {
                        faces[Y_AXIS_FACES_ORDER_CLOCKWISE[i]]
                                .setLine(faces[Y_AXIS_FACES_ORDER_CLOCKWISE[i + 1]].getLine(layerNumber, false),
                                        layerNumber, false);
                    }
                    faces[5].setLine(buffer, layerNumber, false);
                } else {
                    for (int i = 0; i < 2; i++) {
                        faces[Y_AXIS_FACES_ORDER_COUNTER_CLOCKWISE[i]]
                                .setLine(faces[Y_AXIS_FACES_ORDER_COUNTER_CLOCKWISE[i + 1]].getLine(layerNumber, false),
                                        layerNumber, false);
                    }
                    faces[4].setLine(buffer, layerNumber, false);
                }
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
                    for (int i = 0; i < 2; i++) {
                        faces[Y_AXIS_FACES_ORDER_CLOCKWISE[i]].setFace(faces[Y_AXIS_FACES_ORDER_CLOCKWISE[i + 1]]);
                    }
                    faces[5].setMatrix(buffer);
                } else {
                    for (int i = 0; i < 2; i++) {
                        faces[Y_AXIS_FACES_ORDER_COUNTER_CLOCKWISE[i]].setFace(faces[Y_AXIS_FACES_ORDER_COUNTER_CLOCKWISE[i + 1]]);
                    }
                    faces[4].setMatrix(buffer);
                }
                break;
            }
            case 2: {
                faces[2].rotate(clockwise);
                faces[0].rotate(clockwise);
                int[][] buffer = faces[4].getFace();
                if (clockwise) {
                    for (int i = 0; i < 2; i++) {
                        faces[Z_AXIS_FACES_ORDER_CLOCKWISE[i]].setFace(faces[Z_AXIS_FACES_ORDER_CLOCKWISE[i + 1]]);
                    }
                    faces[3].setMatrix(buffer);
                }
                else {
                    for (int i = 0; i < 2; i++) {
                        faces[Z_AXIS_FACES_ORDER_COUNTER_CLOCKWISE[i]].setFace(faces[Z_AXIS_FACES_ORDER_COUNTER_CLOCKWISE[i + 1]]);
                    }
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
            throw new IllegalArgumentException("The value of faceNumber can only be in 0-" + (FACES_AMOUNT - 1) +
                    " range. Actually was " + faceNumber);
        return faces[faceNumber].getFace();
    }
}
