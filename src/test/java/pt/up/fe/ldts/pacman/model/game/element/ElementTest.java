package pt.up.fe.ldts.pacman.model.game.element;

import org.junit.jupiter.api.Test;
import pt.up.fe.ldts.pacman.model.Element;
import pt.up.fe.ldts.pacman.model.Position;
import pt.up.fe.ldts.pacman.model.game.element.ghost.*;
import pt.up.fe.ldts.pacman.model.game.element.collectibles.*;
import pt.up.fe.ldts.pacman.model.game.element.pacman.Pacman;

import static org.junit.jupiter.api.Assertions.*;

public class ElementTest {
    @Test
    void testElementGetPosition() {
        Element a = new Pacman(new Position(0,0));
        Element b = new Cherry(new Position(10,10));

        assertEquals(new Position(0, 0), a.getPosition());
        assertEquals(new Position(10, 10), b.getPosition());
    }

    @Test
    void testElementSetPosition() {
        Element a = new Pinky(new Position(0,0));
        Element b = new Coin(new Position(10,10));

        a.setPosition(new Position(20,50));
        b.setPosition(new Position(0,0));

        assertEquals(new Position(20, 50), a.getPosition());
        assertEquals(new Position(0, 0), b.getPosition());
    }

    @Test
    void testElementEquals() {
        Element a = new Blinky(new Position(0,0));
        Element b = new Coin(new Position(10,10));
        Element c = new Clyde(new Position(0,0));
        Element d = new Blinky(new Position(0,0));

        assertFalse(a.equals(b));
        assertFalse(a.equals(c));
        assertTrue(a.equals(d));
    }

    @Test
    void testElementNegativePosition() {
        assertThrows(IllegalArgumentException.class,() -> new Orange(new Position(-5,5)));
        assertThrows(IllegalArgumentException.class,() -> new Pacman(new Position(0,-100)));
        assertThrows(IllegalArgumentException.class,() -> new Clyde(new Position(-1,-1)));
        assertDoesNotThrow(() -> new Inky(new Position(0,0)));
        assertDoesNotThrow(() -> new Pacman(new Position(100,5)));
    }

    @Test
    void testElementSetNegativePosition() {
        Element a = new Pacman(new Position(0,0));
        Element b = new Coin(new Position(10,10));
        Element c = new Clyde(new Position(0,0));

        assertThrows(IllegalArgumentException.class,() -> a.setPosition(new Position(-5,5)));
        assertThrows(IllegalArgumentException.class,() -> b.setPosition(new Position(0,-100)));
        assertThrows(IllegalArgumentException.class,() -> c.setPosition(new Position(-1,-1)));
        assertDoesNotThrow(() -> new Inky(new Position(0,0)));
        assertDoesNotThrow(() -> new Pacman(new Position(100,5)));
    }
}
