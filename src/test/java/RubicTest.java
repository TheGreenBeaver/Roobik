import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class RubicTest {

    @Test
    void test() {
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
