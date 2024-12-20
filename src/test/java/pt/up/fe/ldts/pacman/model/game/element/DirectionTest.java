package pt.up.fe.ldts.pacman.model.game.element;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DirectionTest {

    @Test
    void testIsOpposite() {
        assertTrue(Direction.UP.isOpposite(Direction.DOWN));
        assertTrue(Direction.DOWN.isOpposite(Direction.UP));
        assertTrue(Direction.LEFT.isOpposite(Direction.RIGHT));
        assertTrue(Direction.RIGHT.isOpposite(Direction.LEFT));

        assertFalse(Direction.UP.isOpposite(Direction.LEFT));
        assertFalse(Direction.UP.isOpposite(Direction.RIGHT));
        assertFalse(Direction.DOWN.isOpposite(Direction.LEFT));
        assertFalse(Direction.DOWN.isOpposite(Direction.RIGHT));
        assertFalse(Direction.LEFT.isOpposite(Direction.UP));
        assertFalse(Direction.RIGHT.isOpposite(Direction.UP));
    }

    @Test
    void testGetOpposite() {
        assertEquals(Direction.DOWN, Direction.UP.getOpposite());
        assertEquals(Direction.UP, Direction.DOWN.getOpposite());
        assertEquals(Direction.RIGHT, Direction.LEFT.getOpposite());
        assertEquals(Direction.LEFT, Direction.RIGHT.getOpposite());
    }
}
