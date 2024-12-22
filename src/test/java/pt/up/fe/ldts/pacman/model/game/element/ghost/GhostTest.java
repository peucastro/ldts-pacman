package pt.up.fe.ldts.pacman.model.game.element.ghost;

import org.junit.jupiter.api.Test;
import pt.up.fe.ldts.pacman.model.Position;
import pt.up.fe.ldts.pacman.model.game.Arena;
import pt.up.fe.ldts.pacman.model.game.element.Direction;

import static org.junit.jupiter.api.Assertions.*;

public class GhostTest {
    @Test
    void testGhostInitialization(){
        Ghost ghost = new Blinky(new Position(0,0));

        assertEquals(GhostState.ALIVE, ghost.getState());
        assertEquals(new Position(0,0) ,ghost.getPosition());
        assertNull(ghost.getRespawnPosition());
        assertEquals(Arena.GHOST_NORMAL_SPEED, ghost.getSpeed());
        assertTrue(ghost.isInsideGate());
    }

    @Test
    void testGhostSetDirection() {
        Ghost a = new Pinky(new Position(5, 14));
        Ghost b = new Blinky(new Position(92, 0));
        Ghost c = new Inky(new Position(0, 2));
        Ghost d = new Clyde(new Position(100, 0));

        b.setDirection(Direction.UP);
        c.setDirection(Direction.DOWN);
        d.setDirection(Direction.RIGHT);

        assertEquals(Direction.LEFT, a.getDirection());
        assertEquals(Direction.UP, b.getDirection());
        assertEquals(Direction.DOWN, c.getDirection());
        assertEquals(Direction.RIGHT, d.getDirection());
    }

    @Test
    void testGhostGetDirection() {
        Ghost a = new Pinky(new Position(5, 14));
        Ghost b = new Blinky(new Position(92, 0));
        Ghost c = new Inky(new Position(0, 2));
        Ghost d = new Clyde(new Position(100, 0));

        assertEquals(Direction.LEFT, a.getDirection());
        assertEquals(Direction.LEFT, b.getDirection());
        assertEquals(Direction.LEFT, c.getDirection());
        assertEquals(Direction.LEFT, d.getDirection());
    }

    @Test
    void testGhostSetState() {
        Ghost a = new Pinky(new Position(5, 14));
        Ghost b = new Blinky(new Position(92, 0));
        Ghost c = new Inky(new Position(0, 2));
        Ghost d = new Clyde(new Position(100, 0));

        b.setState(GhostState.DEAD);
        c.setState(GhostState.SCARED);
        d.setState(GhostState.DEAD);

        assertEquals(GhostState.ALIVE, a.getState());
        assertEquals(GhostState.DEAD, b.getState());
        assertEquals(GhostState.SCARED, c.getState());
        assertEquals(GhostState.DEAD, d.getState());
    }

    @Test
    void testGhostGetState() {
        Ghost a = new Pinky(new Position(5, 14));
        Ghost b = new Blinky(new Position(92, 0));
        Ghost c = new Inky(new Position(0, 2));
        Ghost d = new Clyde(new Position(100, 0));

        assertEquals(GhostState.ALIVE, a.getState());
        assertEquals(GhostState.ALIVE, b.getState());
        assertEquals(GhostState.ALIVE, c.getState());
        assertEquals(GhostState.ALIVE, d.getState());
    }

    @Test
    void testGhostIsDead() {
        Ghost a = new Pinky(new Position(5, 14));
        Ghost b = new Blinky(new Position(92, 0));
        Ghost c = new Inky(new Position(0, 2));
        Ghost d = new Clyde(new Position(100, 0));

        assertFalse(a.isDead());
        assertFalse(b.isDead());
        assertFalse(c.isDead());
        assertFalse(d.isDead());

        b.setState(GhostState.DEAD);
        d.setState(GhostState.DEAD);

        assertTrue(b.isDead());
        assertTrue(d.isDead());
    }

    @Test
    void testGhostIsScared() {
        Ghost a = new Pinky(new Position(5, 14));
        Ghost b = new Blinky(new Position(92, 0));
        Ghost c = new Inky(new Position(0, 2));
        Ghost d = new Clyde(new Position(100, 0));

        assertFalse(a.isScared());
        assertFalse(b.isScared());
        assertFalse(c.isScared());
        assertFalse(d.isScared());

        c.setState(GhostState.SCARED);

        assertTrue(c.isScared());
    }

    @Test
    void testGhostGateState() {
        Ghost a = new Pinky(new Position(5, 14));

        assertTrue(a.isInsideGate());

        a.setOutsideGate();

        assertFalse(a.isInsideGate());

        a.setInsideGate();

        assertTrue(a.isInsideGate());
    }

    @Test
    void testGhostRespawnPosition() {
        Ghost a = new Pinky(new Position(5, 14));
        Position respawnPos = new Position(10, 10);

        a.setRespawnPosition(respawnPos);

        assertEquals(respawnPos, a.getRespawnPosition());
    }

}
