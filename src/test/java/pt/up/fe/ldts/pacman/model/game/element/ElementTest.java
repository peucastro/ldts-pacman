package pt.up.fe.ldts.pacman.model.game.element;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import org.junit.jupiter.api.Test;
import pt.up.fe.ldts.pacman.model.Element;
import pt.up.fe.ldts.pacman.model.Position;
import pt.up.fe.ldts.pacman.model.game.element.collectibles.Cherry;
import pt.up.fe.ldts.pacman.model.game.element.collectibles.Coin;
import pt.up.fe.ldts.pacman.model.game.element.collectibles.Orange;
import pt.up.fe.ldts.pacman.model.game.element.ghost.Blinky;
import pt.up.fe.ldts.pacman.model.game.element.ghost.Clyde;
import pt.up.fe.ldts.pacman.model.game.element.ghost.Inky;
import pt.up.fe.ldts.pacman.model.game.element.ghost.Pinky;
import pt.up.fe.ldts.pacman.model.game.element.pacman.Pacman;

import static org.junit.jupiter.api.Assertions.*;

public class ElementTest {
    @Test
    void testElementGetPosition() {
        Element a = new Pacman(new Position(0, 0));
        Element b = new Cherry(new Position(10, 10));

        assertEquals(new Position(0, 0), a.getPosition());
        assertEquals(new Position(10, 10), b.getPosition());
    }

    @Test
    void testElementSetPosition() {
        Element a = new Pinky(new Position(0, 0));
        Element b = new Coin(new Position(10, 10));

        a.setPosition(new Position(20, 50));
        b.setPosition(new Position(0, 0));

        assertEquals(new Position(20, 50), a.getPosition());
        assertEquals(new Position(0, 0), b.getPosition());
    }

    @Test
    void testElementEquals() {
        Element a = new Blinky(new Position(0, 0));
        Element b = new Coin(new Position(10, 10));
        Element c = new Clyde(new Position(0, 0));
        Element d = new Blinky(new Position(0, 0));

        assertNotEquals(a, b);
        assertNotEquals(a, c);
        assertEquals(a, d);
    }

    @Test
    void testElementNegativePosition() {
        assertThrows(IllegalArgumentException.class, () -> new Orange(new Position(-5, 5)));
        assertThrows(IllegalArgumentException.class, () -> new Pacman(new Position(0, -100)));
        assertThrows(IllegalArgumentException.class, () -> new Clyde(new Position(-1, -1)));
        assertDoesNotThrow(() -> new Inky(new Position(0, 0)));
        assertDoesNotThrow(() -> new Pacman(new Position(100, 5)));
    }

    @Test
    void testEqualsWithSameObject(){
        Element element = new Coin(new Position(0,0));
        assertEquals(element, element);
    }

    @Test
    void testEqualsWithDifferentClass(){
        Element element = new Coin(new Position(0,0));
        assertFalse(element.equals("not an element Ob"));
    }

    @Test
    void testHashCodeConsistency(){
        Element element1 = new Pacman(new Position(0,0));
        Element element2 = new Pacman(new Position(0,0));
        Element element3 = new Pacman(new Position(1,0));
        assertEquals(element2.hashCode(), element1.hashCode());
        assertNotEquals(element2.hashCode(), element3.hashCode());
    }

    @Test
    void testElementSetNegativePosition() {
        Element a = new Pacman(new Position(0, 0));
        Element b = new Coin(new Position(10, 10));
        Element c = new Clyde(new Position(0, 0));

        assertThrows(IllegalArgumentException.class, () -> a.setPosition(new Position(-5, 5)));
        assertThrows(IllegalArgumentException.class, () -> b.setPosition(new Position(0, -100)));
        assertThrows(IllegalArgumentException.class, () -> c.setPosition(new Position(-1, -1)));
        assertDoesNotThrow(() -> new Inky(new Position(0, 0)));
        assertDoesNotThrow(() -> new Pacman(new Position(100, 5)));
    }

    @Property
    void testElementCreation(@ForAll int x, @ForAll int y){
        Position position = new Position(x,y);
        if(x < 0 || y < 0){
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,() -> new Orange(position));
            assertEquals("Element position cannot have negatives values: " + position, exception.getMessage());
        }
        else assertDoesNotThrow(() -> new Orange(position));
    }
}
