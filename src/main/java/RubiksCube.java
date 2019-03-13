import java.lang.reflect.Array;
import java.util.Arrays;

public class RubiksCube {

    private static final int FACES_AMOUNT = 6;
    private static final int[][] ROTATION_ORDERS_CLOCKWISE = {{0, 1, 2, 3}, {0, 4, 2, 5}, {1, 5, 3, 4}};
    private static final int[][] FACES_TO_ROTATE_AROUND_SELF = {{4, 5}, {1, 3}, {2, 0}};

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
    //clockwiseness is checked watching into the head of axis!!!

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
            faces[ROTATION_ORDERS_CLOCKWISE[axis][3]].setMatrix(buffer);
        } else {
            UsefulOperations.equalizeMatrix(faces[ROTATION_ORDERS_CLOCKWISE[axis][3]].getFace(), buffer);
            for (int i = 3; i > 0; i--) {
                faces[ROTATION_ORDERS_CLOCKWISE[axis][i]].setFace(faces[ROTATION_ORDERS_CLOCKWISE[axis][i - 1]]);
            }
            faces[ROTATION_ORDERS_CLOCKWISE[axis][0]].setMatrix(buffer);
        }
        for (int i = 0; i < FACES_AMOUNT; i++) {
            faces[i].rotate(clockwise);
        }
    }

    //180 degrees rotation
    public void rotateWholeCube(int axis) {
        this.rotateWholeCube(axis, true);
        this.rotateWholeCube(axis, true);
    }

    // 90 degrees rotation
    public void rotateLayer(int axis, int layerNumber, boolean clockwise) {
        //0 = around y-axis, 1 = around x-axis, 2 = around z-axis
        this.checkLayer(layerNumber);
        this.checkAxis(axis);
        boolean horizontal = axis == 0;
        if (layerNumber != 1)
            faces[FACES_TO_ROTATE_AROUND_SELF[axis][layerNumber - layerNumber / 2]].rotate(clockwise);
        if (clockwise) {
            int[] buffer = Arrays.copyOf(faces[ROTATION_ORDERS_CLOCKWISE[axis][0]].getLine(layerNumber, horizontal), size);
            for (int i = 0; i < 3; i++) {
                faces[ROTATION_ORDERS_CLOCKWISE[axis][i]]
                        .setLine(
                                faces[ROTATION_ORDERS_CLOCKWISE[axis][i + 1]].getLine(layerNumber, horizontal),
                                layerNumber,
                                horizontal
                        );
            }
            faces[ROTATION_ORDERS_CLOCKWISE[axis][3]].setLine(buffer, layerNumber, horizontal);
        } else {
            int[] buffer = Arrays.copyOf(faces[ROTATION_ORDERS_CLOCKWISE[axis][3]].getLine(layerNumber, horizontal), size);
            for (int i = 3; i > 0; i--) {
                faces[ROTATION_ORDERS_CLOCKWISE[axis][i]]
                        .setLine(
                                faces[ROTATION_ORDERS_CLOCKWISE[axis][i - 1]].getLine(layerNumber, horizontal),
                                layerNumber,
                                horizontal
                        );
            }
            faces[ROTATION_ORDERS_CLOCKWISE[axis][0]].setLine(buffer, layerNumber, horizontal);
        }
    }

    // 180 degrees rotation
    public void rotateLayer(int axis, int layerNumber) {
        this.rotateLayer(axis, layerNumber, true);
        this.rotateLayer(axis, layerNumber, true);
    }

    public int[][] getFace(int faceNumber) {
        if (faceNumber < 0 || faceNumber >= FACES_AMOUNT)
            throw new IllegalArgumentException("The value of faceNumber can only be in 0-" + (FACES_AMOUNT - 1) +
                    " range. Actually was " + faceNumber);
        return faces[faceNumber].getFace();
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
