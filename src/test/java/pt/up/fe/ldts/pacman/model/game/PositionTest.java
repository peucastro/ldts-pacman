package pt.up.fe.ldts.pacman.model.game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    @Test
    void testConstructorAndGetters() {
        Position position = new Position(5, 10);
        assertEquals(5, position.getX());
        assertEquals(10, position.getY());
    }

    @Test
    void testSetX() {
        Position position = new Position(0, 0);
        position.setX(7);
        assertEquals(7, position.getX());
    }

    @Test
    void testSetY() {
        Position position = new Position(0, 0);
        position.setY(8);
        assertEquals(8, position.getY());
    }

    @Test
    void testSetPosition() {
        Position position1 = new Position(1, 2);
        Position position2 = new Position(3, 4);
        position1.setPosition(position2);

        assertEquals(3, position1.getX());
        assertEquals(4, position1.getY());
        assertEquals(position2, position1);
    }

    @Test
    void testEqualsWithSameObject() {
        Position position = new Position(1, 1);
        assertTrue(position.equals(position));
    }

    @Test
    void testEqualsWithNull() {
        Position position = new Position(1, 1);
        assertFalse(position.equals(null));
    }

    @Test
    void testEqualsWithDifferentClass() {
        Position position = new Position(1, 1);
        assertFalse(position.equals("Not a Position object"));
    }

    @Test
    void testEqualsWithDifferentX() {
        Position position1 = new Position(1, 2);
        Position position2 = new Position(2, 2);
        assertFalse(position1.equals(position2));
    }

    @Test
    void testEqualsWithDifferentY() {
        Position position1 = new Position(1, 2);
        Position position2 = new Position(1, 3);
        assertFalse(position1.equals(position2));
    }

    @Test
    void testEqualsWithSameCoordinates() {
        Position position1 = new Position(1, 2);
        Position position2 = new Position(1, 2);
        assertTrue(position1.equals(position2));
    }

    @Test
    void testHashCodeConsistency() {
        Position position1 = new Position(5, 5);
        Position position2 = new Position(5, 5);
        assertEquals(position1.hashCode(), position2.hashCode());
    }

    @Test
    void testHashCodeDifferentValues() {
        Position position1 = new Position(1, 2);
        Position position2 = new Position(2, 1);
        assertNotEquals(position1.hashCode(), position2.hashCode());
    }

    @Test
    void testToString() {
        Position position = new Position(3, 7);
        assertEquals("(3, 7)", position.toString());
    }
}
