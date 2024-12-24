package pt.up.fe.ldts.pacman.model.game;

import com.googlecode.lanterna.TerminalPosition;
import org.junit.jupiter.api.Test;
import pt.up.fe.ldts.pacman.model.Position;

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
        assertEquals(position, position);
    }

    @Test
    void testEqualsWithNull() {
        Position position = new Position(1, 1);
        assertNotEquals(null, position);
    }

    @Test
    void testEqualsWithDifferentClass() {
        Position position = new Position(1, 1);
        assertNotEquals("not a Position object", position);
    }

    @Test
    void testEqualsWithDifferentX() {
        Position position1 = new Position(1, 2);
        Position position2 = new Position(2, 2);
        assertNotEquals(position1, position2);
    }

    @Test
    void testEqualsWithDifferentY() {
        Position position1 = new Position(1, 2);
        Position position2 = new Position(1, 3);
        assertNotEquals(position1, position2);
    }

    @Test
    void testEqualsWithSameCoordinates() {
        Position position1 = new Position(1, 2);
        Position position2 = new Position(1, 2);
        assertEquals(position1, position2);
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

    @Test
    void testDirectionMethods() {
        Position position = new Position(5, 5);
        assertEquals(new Position(4, 5), position.getLeft());
        assertEquals(new Position(6, 5), position.getRight());
        assertEquals(new Position(5, 4), position.getUp());
        assertEquals(new Position(5, 6), position.getDown());
    }

    @Test
    void testSquaredDistance() {
        Position position1 = new Position(1, 1);
        Position position2 = new Position(4, 5);
        assertEquals(25, position1.squaredDistance(position2));
        assertEquals(25, position2.squaredDistance(position1));
    }

    @Test
    void testToTerminalPosition() {
        Position position = new Position(7, 3);
        TerminalPosition terminalPosition = position.toTerminalPosition();
        assertEquals(7, terminalPosition.getColumn());
        assertEquals(3, terminalPosition.getRow());
    }
}
