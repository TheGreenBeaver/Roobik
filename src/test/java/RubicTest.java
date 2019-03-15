import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class RubicTest {

    private static RubiksCube cube2;
    private static RubiksCube cube3;
    private static RubiksCube cube4;

    @BeforeAll
    static void createSomeCubes() {
        cube2 = new RubiksCube(2);
        cube3 = new RubiksCube(3);
        cube4 = new RubiksCube(4);
    }

    private void createIllegalCube() {
        RubiksCube cube1 = new RubiksCube(1);
    }

    @Test
    void testIllegalCubeCreation() {
        assertThrows(IllegalArgumentException.class, this::createIllegalCube);
    }

    @Test
    void testWholeCubeRotation() {
        cube3.rotateWholeCube(0);
        assertEquals("            4 4 4      \n" +
                "            4 4 4      \n" +
                "            4 4 4      \n" +
                "2 2 2 3 3 3 0 0 0 1 1 1 \n" +
                "2 2 2 3 3 3 0 0 0 1 1 1 \n" +
                "2 2 2 3 3 3 0 0 0 1 1 1 \n" +
                "            5 5 5      \n" +
                "            5 5 5      \n" +
                "            5 5 5      \n", cube3.toString());
        cube2.rotateWholeCube(2, false);
        assertEquals("        3 3    \n" +
                "        3 3    \n" +
                "0 0 4 4 2 2 5 5 \n" +
                "0 0 4 4 2 2 5 5 \n" +
                "        1 1    \n" +
                "        1 1    \n", cube2.toString());
        cube4.rotateWholeCube(1, true);
        assertEquals("                2 2 2 2        \n" +
                "                2 2 2 2        \n" +
                "                2 2 2 2        \n" +
                "                2 2 2 2        \n" +
                "4 4 4 4 1 1 1 1 5 5 5 5 3 3 3 3 \n" +
                "4 4 4 4 1 1 1 1 5 5 5 5 3 3 3 3 \n" +
                "4 4 4 4 1 1 1 1 5 5 5 5 3 3 3 3 \n" +
                "4 4 4 4 1 1 1 1 5 5 5 5 3 3 3 3 \n" +
                "                0 0 0 0        \n" +
                "                0 0 0 0        \n" +
                "                0 0 0 0        \n" +
                "                0 0 0 0        \n", cube4.toString());
    }

    private void illegalWholeCubeRotation() {
        cube3.rotateWholeCube(4, true);
    }

    @Test
    void testIllegalWholeCubeRotation() {
        assertThrows(IllegalArgumentException.class, this::illegalWholeCubeRotation);
    }

    private  void illegalAxisLayerRotation() {
        cube3.rotateLayer(-1, 2);
    }

    private void illegalLayerLayerRotation() {
        cube4.rotateLayer(0, 4, true);
    }

    @Test
    void testIllegalLayerRotations() {
        assertThrows(IllegalArgumentException.class, this::illegalAxisLayerRotation);
        assertThrows(IllegalArgumentException.class, this::illegalLayerLayerRotation);
    }

    @Test
    void testLayerRotation() {
        RubiksCube cube = new RubiksCube(3);
        cube.rotateLayer(0, 1, true);
        cube.rotateLayer(1, 0, false);
        assertEquals("            0 4 4      \n" +
                "            1 4 4      \n" +
                "            0 4 4      \n" +
                "0 0 5 1 2 1 4 2 2 3 3 3 \n" +
                "1 1 5 1 2 1 4 3 3 0 0 0 \n" +
                "0 0 5 1 2 1 4 2 2 3 3 3 \n" +
                "            2 5 5      \n" +
                "            3 5 5      \n" +
                "            2 5 5      \n", cube.toString());
    }
}
