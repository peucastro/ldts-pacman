package pt.up.fe.ldts.pacman.controller.game.element.behaviours;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.up.fe.ldts.pacman.model.Position;
import pt.up.fe.ldts.pacman.model.game.Arena;
import pt.up.fe.ldts.pacman.model.game.element.Direction;
import pt.up.fe.ldts.pacman.model.game.element.GhostGate;
import pt.up.fe.ldts.pacman.model.game.element.ghost.Ghost;
import pt.up.fe.ldts.pacman.model.game.element.pacman.Pacman;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PinkyMovementBehaviourTest {

    private Ghost pinky;
    private Arena arena;
    private Pacman pacman;

    @BeforeEach
    void setUp() {
        pinky = mock(Ghost.class);
        arena = mock(Arena.class);
        pacman = mock(Pacman.class);
    }

    @Test
    void testPinkyInsideGate() {
        PinkyMovementBehaviour behaviour = new PinkyMovementBehaviour();

        when(pinky.isInsideGate()).thenReturn(true);
        when(arena.getGhostGate()).thenReturn(new GhostGate(new Position(5, 5)));

        Position target = behaviour.getAlivePosition(pinky, arena, pacman, true);
        assertEquals(new Position(5, 5), target); // Targets ghost gate when inside
    }

    @Test
    void testPinkyScatterMode() {
        PinkyMovementBehaviour behaviour = new PinkyMovementBehaviour();

        when(pinky.isInsideGate()).thenReturn(false);

        Position target = behaviour.getAlivePosition(pinky, arena, pacman, false);
        assertEquals(new Position(0, 0), target); // Scatter mode to top-left corner
    }

    @Test
    void testPinkyChaseModeUpDirection() {
        PinkyMovementBehaviour behaviour = new PinkyMovementBehaviour();

        when(pinky.isInsideGate()).thenReturn(false);
        when(pacman.getPosition()).thenReturn(new Position(5, 5));
        when(pacman.getDirection()).thenReturn(Direction.UP);
        when(arena.getHeight()).thenReturn(20);

        Position target = behaviour.getAlivePosition(pinky, arena, pacman, true);
        assertEquals(new Position(5, 2), target); // 3 tiles up, bounded at 0
    }

    @Test
    void testPinkyChaseModeDownDirection() {
        PinkyMovementBehaviour behaviour = new PinkyMovementBehaviour();

        when(pinky.isInsideGate()).thenReturn(false);
        when(pacman.getPosition()).thenReturn(new Position(5, 5));
        when(pacman.getDirection()).thenReturn(Direction.DOWN);
        when(arena.getHeight()).thenReturn(10);

        Position target = behaviour.getAlivePosition(pinky, arena, pacman, true);
        assertEquals(new Position(5, 10), target); // 3 tiles down, bounded at arena height
    }

    @Test
    void testPinkyChaseModeRightDirection() {
        PinkyMovementBehaviour behaviour = new PinkyMovementBehaviour();

        when(pinky.isInsideGate()).thenReturn(false);
        when(pacman.getPosition()).thenReturn(new Position(5, 5));
        when(pacman.getDirection()).thenReturn(Direction.RIGHT);
        when(arena.getWidth()).thenReturn(10);

        Position target = behaviour.getAlivePosition(pinky, arena, pacman, true);
        assertEquals(new Position(10, 5), target); // 3 tiles right, bounded at arena width
    }

    @Test
    void testPinkyChaseModeLeftDirection() {
        PinkyMovementBehaviour behaviour = new PinkyMovementBehaviour();

        when(pinky.isInsideGate()).thenReturn(false);
        when(pacman.getPosition()).thenReturn(new Position(5, 5));
        when(pacman.getDirection()).thenReturn(Direction.LEFT);

        Position target = behaviour.getAlivePosition(pinky, arena, pacman, true);
        assertEquals(new Position(2, 5), target); // 3 tiles left, bounded at 0
    }
}
