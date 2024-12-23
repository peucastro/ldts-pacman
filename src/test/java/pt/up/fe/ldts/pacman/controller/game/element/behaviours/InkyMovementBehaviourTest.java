package pt.up.fe.ldts.pacman.controller.game.element.behaviours;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.up.fe.ldts.pacman.model.Position;
import pt.up.fe.ldts.pacman.model.game.Arena;
import pt.up.fe.ldts.pacman.model.game.element.GhostGate;
import pt.up.fe.ldts.pacman.model.game.element.ghost.Blinky;
import pt.up.fe.ldts.pacman.model.game.element.ghost.Ghost;
import pt.up.fe.ldts.pacman.model.game.element.pacman.Pacman;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class InkyMovementBehaviourTest {

    private Ghost inky;
    private Arena arena;
    private Pacman pacman;

    @BeforeEach
    void setUp() {
        inky = mock(Ghost.class);
        arena = mock(Arena.class);
        pacman = mock(Pacman.class);
    }

    @Test
    void testInkyLowCollectibles() {
        InkyMovementBehaviour behaviour = new InkyMovementBehaviour();

        when(arena.getCollectedCollectibles()).thenReturn(24);

        Position target = behaviour.getAlivePosition(inky, arena, pacman, true);
        assertEquals(new Position(8, 11), target); // Fixed position for low collectibles
    }

    @Test
    void testInkyInsideGate() {
        InkyMovementBehaviour behaviour = new InkyMovementBehaviour();

        when(inky.isInsideGate()).thenReturn(true);
        when(arena.getGhostGate()).thenReturn(new GhostGate(new Position(15, 6)));
        when(arena.getCollectedCollectibles()).thenReturn(25);

        Position target = behaviour.getAlivePosition(inky, arena, pacman, true);
        assertEquals(new Position(15, 6), target); // Targets ghost gate when inside
    }

    @Test
    void testInkyScatterMode() {
        InkyMovementBehaviour behaviour = new InkyMovementBehaviour();

        when(inky.isInsideGate()).thenReturn(false);
        when(arena.getWidth()).thenReturn(29);
        when(arena.getHeight()).thenReturn(16);
        when(arena.getCollectedCollectibles()).thenReturn(30);

        Position target = behaviour.getAlivePosition(inky, arena, pacman, false);
        assertEquals(new Position(29, 16), target); // Scatter mode to bottom-right corner
    }

    @Test
    void testInkyChaseMode() {
        InkyMovementBehaviour behaviour = new InkyMovementBehaviour();

        when(inky.isInsideGate()).thenReturn(false);
        when(arena.getCollectedCollectibles()).thenReturn(30);

        Ghost blinky = mock(Blinky.class);
        when(blinky.getPosition()).thenReturn(new Position(2, 2));

        Set<Ghost> ghosts = new HashSet<>();
        ghosts.add(blinky);
        ghosts.add(inky);
        when(arena.getGhosts()).thenReturn(ghosts);

        when(pacman.getNextPosition()).thenReturn(new Position(6, 6));
        when(arena.getWidth()).thenReturn(29);
        when(arena.getHeight()).thenReturn(16);

        Position target = behaviour.getAlivePosition(inky, arena, pacman, true);

        // Target calculated as: (2 * 6 - 2, 2 * 6 - 2) = (10, 10)
        assertEquals(new Position(10, 10), target);
    }

    @Test
    void testInkyChaseModeOutOfBounds() {
        InkyMovementBehaviour behaviour = new InkyMovementBehaviour();

        when(inky.isInsideGate()).thenReturn(false);
        when(arena.getCollectedCollectibles()).thenReturn(30);

        Ghost blinky = mock(Blinky.class);
        when(blinky.getPosition()).thenReturn(new Position(2, 2));

        Set<Ghost> ghosts = new HashSet<>();
        ghosts.add(blinky);
        ghosts.add(inky);
        when(arena.getGhosts()).thenReturn(ghosts);

        when(pacman.getNextPosition()).thenReturn(new Position(20, 20));
        when(arena.getWidth()).thenReturn(29);
        when(arena.getHeight()).thenReturn(16);

        Position target = behaviour.getAlivePosition(inky, arena, pacman, true);

        assertEquals(new Position(29, 16), target);
    }



    @Test
    void testInkyChaseModeBoundedPosition() {
        InkyMovementBehaviour behaviour = new InkyMovementBehaviour();

        when(inky.isInsideGate()).thenReturn(false);
        when(arena.getCollectedCollectibles()).thenReturn(30);

        Ghost blinky = mock(Blinky.class);
        when(blinky.getPosition()).thenReturn(new Position(5, 5));

        Set<Ghost> ghosts = new HashSet<>();
        ghosts.add(blinky);
        ghosts.add(inky);
        when(arena.getGhosts()).thenReturn(ghosts);

        when(pacman.getNextPosition()).thenReturn(new Position(0, 0));
        when(arena.getWidth()).thenReturn(29);
        when(arena.getHeight()).thenReturn(16);

        Position target = behaviour.getAlivePosition(inky, arena, pacman, true);

        // Target calculated as: (2 * 0 - 5, 2 * 0 - 5) = (-5, -5), bounded to (0, 0)
        assertEquals(new Position(0, 0), target);
    }
}
