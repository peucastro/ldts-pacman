package pt.up.fe.ldts.pacman.model.game.ghost;

import org.junit.jupiter.api.Test;
import pt.up.fe.ldts.pacman.model.game.Position;
import pt.up.fe.ldts.pacman.model.game.element.Direction;
import pt.up.fe.ldts.pacman.model.game.element.State;
import pt.up.fe.ldts.pacman.model.game.element.ghost.Clyde;
import pt.up.fe.ldts.pacman.model.game.element.ghost.*;
import pt.up.fe.ldts.pacman.model.game.element.ghost.Inky;
import pt.up.fe.ldts.pacman.model.game.element.ghost.Pinky;

import static org.junit.jupiter.api.Assertions.*;

public class GhostTest {
    @Test
    void testGhostSetDirection() {
        Ghost a = new Pinky(new Position(5,14));
        Ghost b = new Blinky(new Position(92,0));
        Ghost c = new Inky(new Position(0,2));
        Ghost d = new Clyde(new Position(100,0));

        b.setDirection(Direction.UP);
        c.setDirection(Direction.DOWN);
        d.setDirection(Direction.RIGHT);

        assertEquals(Direction.LEFT,a.getDirection());
        assertEquals(Direction.UP,b.getDirection());
        assertEquals(Direction.DOWN,c.getDirection());
        assertEquals(Direction.RIGHT,d.getDirection());
    }

    @Test
    void testGhostGetDirection() {
        Ghost a = new Pinky(new Position(5,14));
        Ghost b = new Blinky(new Position(92,0));
        Ghost c = new Inky(new Position(0,2));
        Ghost d = new Clyde(new Position(100,0));

        assertEquals(Direction.LEFT,a.getDirection());
        assertEquals(Direction.LEFT,b.getDirection());
        assertEquals(Direction.LEFT,c.getDirection());
        assertEquals(Direction.LEFT,d.getDirection());
    }

    @Test
    void testGhostSetState() {
        Ghost a = new Pinky(new Position(5,14));
        Ghost b = new Blinky(new Position(92,0));
        Ghost c = new Inky(new Position(0,2));
        Ghost d = new Clyde(new Position(100,0));

        b.setState(State.DEAD);
        c.setState(State.SCARED);
        d.setState(State.DEAD);

        assertEquals(State.ALIVE,a.getState());
        assertEquals(State.DEAD,b.getState());
        assertEquals(State.SCARED,c.getState());
        assertEquals(State.DEAD,d.getState());
    }

    @Test
    void testGhostGetState() {
        Ghost a = new Pinky(new Position(5,14));
        Ghost b = new Blinky(new Position(92,0));
        Ghost c = new Inky(new Position(0,2));
        Ghost d = new Clyde(new Position(100,0));

        assertEquals(State.ALIVE,a.getState());
        assertEquals(State.ALIVE,b.getState());
        assertEquals(State.ALIVE,c.getState());
        assertEquals(State.ALIVE,d.getState());
    }
}
