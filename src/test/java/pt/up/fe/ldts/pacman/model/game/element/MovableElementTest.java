package pt.up.fe.ldts.pacman.model.game.element;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.up.fe.ldts.pacman.model.Position;

public class MovableElementTest {

    private MovableElement movableElement;

    @BeforeEach
    void setUp() {
        movableElement = new MovableElement(new Position(5, 5)) {
        };
    }

    @Test
    void testInitialPosition() {
        Assertions.assertEquals(new Position(5, 5), movableElement.getPosition());
    }

    @Test
    void testInitialDirection() {
        Assertions.assertEquals(Direction.LEFT, movableElement.getDirection());
    }

    @Test
    void testSetDirection() {
        movableElement.setDirection(Direction.UP);
        Assertions.assertEquals(Direction.UP, movableElement.getDirection());
    }

    @Test
    void testCounterIncrement() {
        for (int i = 0; i < 10; i++) {
            movableElement.incrementCounter();
            Assertions.assertEquals(i + 1, movableElement.getCounter());
        }
        Position expectedPosition = movableElement.getPosition().getLeft();
        movableElement.incrementCounter();
        Assertions.assertEquals(0, movableElement.getCounter());
        Assertions.assertEquals(expectedPosition, movableElement.getPosition());
    }

    @Test
    void testGetCounterX() {
        movableElement.setDirection(Direction.LEFT);
        movableElement.setCounter(5);
        Assertions.assertEquals(-5, movableElement.getCounterX());

        movableElement.setDirection(Direction.RIGHT);
        Assertions.assertEquals(5, movableElement.getCounterX());
    }

    @Test
    void testGetCounterY() {
        movableElement.setDirection(Direction.UP);
        movableElement.setCounter(3);
        Assertions.assertEquals(-3, movableElement.getCounterY());

        movableElement.setDirection(Direction.DOWN);
        Assertions.assertEquals(3, movableElement.getCounterY());
    }

    @Test
    void testGetNextPosition() {
        movableElement.setDirection(Direction.UP);
        Assertions.assertEquals(new Position(5, 4), movableElement.getNextPosition());

        movableElement.setDirection(Direction.DOWN);
        Assertions.assertEquals(new Position(5, 6), movableElement.getNextPosition());

        movableElement.setDirection(Direction.LEFT);
        Assertions.assertEquals(new Position(4, 5), movableElement.getNextPosition());

        movableElement.setDirection(Direction.RIGHT);
        Assertions.assertEquals(new Position(6, 5), movableElement.getNextPosition());
    }

    @Test
    void testInvertDirection() {
        Position expected = movableElement.getPosition().getLeft();
        movableElement.setDirection(Direction.LEFT);
        movableElement.setCounter(5);
        movableElement.invertDirection();

        Assertions.assertEquals(expected, movableElement.getPosition());
        Assertions.assertEquals(Direction.RIGHT, movableElement.getDirection());
        Assertions.assertEquals(11 - 5, movableElement.getCounter());
    }


    @Test
    void testGetRealPosition() {
        movableElement.setDirection(Direction.LEFT);
        movableElement.setCounter(5);
        Assertions.assertEquals(new Position(5 * 11 - 5, 5 * 11), movableElement.getRealPosition());

        movableElement.setDirection(Direction.UP);
        movableElement.setCounter(3);
        Assertions.assertEquals(new Position(5 * 11, 5 * 11 - 3), movableElement.getRealPosition());
    }

    @Test
    void testCollidingWith() {
        MovableElement other = new MovableElement(new Position(5, 5)) {
        };

        Assertions.assertTrue(movableElement.collidingWith(other));

        other.setPosition(new Position(5, 6));
        other.setCounter(6);

        Assertions.assertFalse(movableElement.collidingWith(other));

        other.setPosition(new Position(10, 10));
        other.setCounter(0);

        Assertions.assertFalse(movableElement.collidingWith(other));
    }

    @Test
    void testSetSpeed() {
        movableElement.setSpeed(5);
        Assertions.assertEquals(5, movableElement.getSpeed());
    }
}
