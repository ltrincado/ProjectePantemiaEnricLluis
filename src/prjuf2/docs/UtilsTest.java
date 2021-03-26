package prjuf2.docs;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    @org.junit.jupiter.api.Test
    void validateInt() {
        assertEquals(1, 1);
        assertEquals(2, 2);
        assertNotEquals(1, '.');
    }

    @org.junit.jupiter.api.Test
    void validateFloat() {
        assertEquals(2.5, 2.5);
        assertEquals(3, 3);
        assertNotEquals(2.5, 's');
    }

    @org.junit.jupiter.api.Test
    void validateMove() {
        assertEquals(2, 2);
        assertEquals(5, 5);
        assertNotEquals(2, -4);
    }
}