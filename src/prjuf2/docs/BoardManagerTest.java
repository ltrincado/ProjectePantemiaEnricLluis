package prjuf2.docs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardManagerTest {

    @Test
    void sumArray() {
        assertEquals(100, 100);
        assertEquals(250, 250);
        assertNotEquals(250, 250.55);
    }

    @Test
    void sumMovedPeople() {
        assertEquals(50.0, 50.0);
        assertEquals(79.9, 79.9);
        assertNotEquals(80.5, 150);
    }
}