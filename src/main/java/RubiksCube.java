import java.util.Arrays;

public class RubiksCube {

    private static final int FACES_AMOUNT = 6;
    private static final int[][] ROTATION_ORDERS_CLOCKWISE = {{0, 1, 2, 3}, {4, 0, 5, 2}, {4, 3, 5, 1}};
    private static final int[][] FACES_TO_ROTATE_AROUND_SELF = {{4, 5}, {1, 3}, {0, 2}};

    private int size;
    private Face[] faces;
    // faces[0] = back
    // faces[1] = left
    // faces[2] = front
    // faces[3] = right
    // faces[4] = up
    // faces[5] = down
    // clockwiseness is checked watching into the head of axis
    // layers on y-axis are counted from up to down, on x-axis - from left to right, on z-axis - from back to front
    // 0 = around y-axis, 1 = around x-axis, 2 = around z-axis
    // y-axis goes upwards, x-axis goes leftwards, z-axis goes away from viewer

    public RubiksCube(int size) {
        this.size = size;
        faces = new Face[FACES_AMOUNT];
        for (int i = 0; i < FACES_AMOUNT; i++)
            faces[i] = new Face(size, i);
    }

    private void checkAxis(int axis) {
        if (axis < 0 || axis > 2) {
            throw new IllegalArgumentException("The value of axis can only be 0, 1 or 2. Actually was " + axis);
        }
    }

    private void checkLayer(int layerNumber) {
        if (layerNumber < 0 || layerNumber >= size) {
            throw new IllegalArgumentException("The value of layerNumber can only be in 0-"
                    + (size - 1) + " range. Actually was " + layerNumber);
        }
    }

    // 90 degrees rotation
    public void rotateWholeCube(int axis, boolean clockwise) {
        this.checkAxis(axis);
        int[][] buffer = new int[size][size];
        if (clockwise) {
            UsefulOperations.equalizeMatrix(faces[ROTATION_ORDERS_CLOCKWISE[axis][0]].getFace(), buffer);
            for (int i = 0; i < 3; i++) {
                faces[ROTATION_ORDERS_CLOCKWISE[axis][i]].setFace(faces[ROTATION_ORDERS_CLOCKWISE[axis][i + 1]]);
            }
            faces[ROTATION_ORDERS_CLOCKWISE[axis][3]].setFace(buffer);
        } else {
            UsefulOperations.equalizeMatrix(faces[ROTATION_ORDERS_CLOCKWISE[axis][3]].getFace(), buffer);
            for (int i = 3; i > 0; i--) {
                faces[ROTATION_ORDERS_CLOCKWISE[axis][i]].setFace(faces[ROTATION_ORDERS_CLOCKWISE[axis][i - 1]]);
            }
            faces[ROTATION_ORDERS_CLOCKWISE[axis][0]].setFace(buffer);
        }
        for (int i = 0; i < FACES_AMOUNT; i++) {
            faces[i].rotate(clockwise);
        }
    }

    // 180 degrees rotation
    public void rotateWholeCube(int axis) {
        this.rotateWholeCube(axis, true);
        this.rotateWholeCube(axis, true);
    }

    // 90 degrees rotation
    public void rotateLayer(int axis, int layerNumber, boolean clockwise) {
        this.checkLayer(layerNumber);
        this.checkAxis(axis);
        boolean horizontal = axis == 0;

        // rotation of surrounding faces (ones that do not get divided in layers)
        if (layerNumber == 0)
            faces[FACES_TO_ROTATE_AROUND_SELF[axis][0]].rotate(clockwise);
        if (layerNumber == size - 1)
            faces[FACES_TO_ROTATE_AROUND_SELF[axis][1]].rotate(!clockwise);

        int[] newLine;
        int[] buffer;
        int layerNumberTo;
        int layerNumberFrom;
        boolean directionTo;
        boolean directionFrom;

        // clockwise rotation
        if (clockwise) {
            buffer = Arrays.copyOf(faces[ROTATION_ORDERS_CLOCKWISE[axis][0]].getLine(layerNumber, axis != 1), size);
            for (int i = 0; i < 3; i++) {
                if (axis == 2) {
                    directionTo = i % 2 == 0;
                    directionFrom = !directionTo;
                    if (i == 0) {
                        layerNumberTo = layerNumber;
                    } else {
                        layerNumberTo = size - layerNumber - 1;
                    }
                    if (i == 2) {
                        layerNumberFrom = layerNumber;
                    } else {
                        layerNumberFrom = size - layerNumber - 1;
                    }
                } else {
                    directionFrom = horizontal;
                    directionTo = horizontal;
                    layerNumberFrom = layerNumber;
                    layerNumberTo = layerNumber;
                }
                newLine = Arrays.copyOf(faces[ROTATION_ORDERS_CLOCKWISE[axis][i+1]].getLine(layerNumberFrom, directionFrom), size);
                if (axis == 1 && i < 2 || axis == 2 && i == 1) {
                    UsefulOperations.revertArray(newLine);
                }
                faces[ROTATION_ORDERS_CLOCKWISE[axis][i]].setLine(newLine, layerNumberTo, directionTo);
            }
            if (axis == 2) {
                UsefulOperations.revertArray(buffer);
            }
            faces[ROTATION_ORDERS_CLOCKWISE[axis][3]].setLine(buffer, layerNumber, horizontal);

        } else { // counter-clockwise rotation
            buffer = Arrays.copyOf(faces[ROTATION_ORDERS_CLOCKWISE[axis][3]].getLine(layerNumber, horizontal), size);
            for (int i = 3; i > 0; i--) {
                if (axis == 2) {
                    directionTo = i % 2 == 0;
                    directionFrom = !directionTo;
                    if (i == 3) {
                        layerNumberTo = layerNumber;
                    } else {
                        layerNumberTo = size - layerNumber - 1;
                    }
                    if (i == 1) {
                        layerNumberFrom = layerNumber;
                    } else {
                        layerNumberFrom = size - layerNumber - 1;
                    }
                } else {
                    layerNumberFrom = layerNumber;
                    layerNumberTo = layerNumber;
                    directionFrom = horizontal;
                    directionTo = horizontal;
                }
                newLine = Arrays.copyOf(faces[ROTATION_ORDERS_CLOCKWISE[axis][i - 1]].getLine(layerNumberFrom, directionFrom), size);
                if (axis == 1 && i > 1 || axis == 2 && i == 2) {
                    UsefulOperations.revertArray(newLine);
                }
                faces[ROTATION_ORDERS_CLOCKWISE[axis][i]].setLine(newLine, layerNumberTo, directionTo);
            }
            if (axis == 2) {
                UsefulOperations.revertArray(buffer);
            }
            faces[ROTATION_ORDERS_CLOCKWISE[axis][0]].setLine(buffer, layerNumber, axis != 1);
        }
    }

    // 180 degrees rotation
    public void rotateLayer(int axis, int layerNumber) {
        this.rotateLayer(axis, layerNumber, true);
        this.rotateLayer(axis, layerNumber, true);
    }

    public String getFace(int faceNumber) {
        if (faceNumber < 0 || faceNumber >= FACES_AMOUNT)
            throw new IllegalArgumentException("The value of faceNumber can only be in 0-" + (FACES_AMOUNT - 1) +
                    " range. Actually was " + faceNumber);
        StringBuilder face = new StringBuilder();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                face.append(faces[faceNumber].getFace()[i][j]).append(" ");
            }
            face.append("\n");
        }
        return face.toString();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size * 4; j++) {
                result.append(" ");
            }
            for (int j = 0; j < size; j++) {
                result.append(faces[4].getLine(i, true)[j]).append(" ");
            }
            for (int j = 0; j < size * 2 - 1; j++) {
                result.append(" ");
            }
            result.append("\n");
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < 4; j++) {
                if (j == 0) {
                    for (int k = size - 1; k >= 0; k--) {
                        result.append(faces[j].getLine(i, true)[k]).append(" ");
                    }
                } else {
                    for (int k = 0; k < size; k++) {
                        result.append(faces[j].getLine(i, true)[k]).append(" ");
                    }
                }
            }
            result.append("\n");
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size * 4; j++) {
                result.append(" ");
            }
            for (int j = 0; j < size; j++) {
                result.append(faces[5].getLine(i, true)[j]).append(" ");
            }
            for (int j = 0; j < size * 2 - 1; j++) {
                result.append(" ");
            }
            result.append("\n");
        }
        return result.toString();
    }
}
