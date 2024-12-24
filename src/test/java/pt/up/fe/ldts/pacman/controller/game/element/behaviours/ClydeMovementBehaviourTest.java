package pt.up.fe.ldts.pacman.controller.game.element.behaviours;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.up.fe.ldts.pacman.model.Position;
import pt.up.fe.ldts.pacman.model.game.Arena;
import pt.up.fe.ldts.pacman.model.game.element.GhostGate;
import pt.up.fe.ldts.pacman.model.game.element.ghost.Ghost;
import pt.up.fe.ldts.pacman.model.game.element.pacman.Pacman;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class ClydeMovementBehaviourTest {
    private Ghost ghost;
    private Arena arena;
    private Pacman pacman;

    @BeforeEach
    void setUp() {
        ghost = mock(Ghost.class);
        arena = mock(Arena.class);
        pacman = mock(Pacman.class);
    }

    @Test
    void testClydeAliveChaseModeFarFromPacman() {
        GhostMovementBehaviour behaviour = new ClydeMovementBehaviour();

        when(ghost.isInsideGate()).thenReturn(false);
        when(arena.getCollectedCollectibles()).thenReturn(70);
        when(ghost.getPosition()).thenReturn(new Position(0, 0));
        when(pacman.getPosition()).thenReturn(new Position(6, 0));

        Position target = behaviour.getAlivePosition(ghost, arena, pacman, true);
        assertEquals(new Position(6, 0), target); // Clyde chases Pacman
    }

    @Test
    void testClydeAliveChaseModeNearPacman() {
        GhostMovementBehaviour behaviour = new ClydeMovementBehaviour();

        when(ghost.isInsideGate()).thenReturn(false);
        when(arena.getCollectedCollectibles()).thenReturn(70);
        when(ghost.getPosition()).thenReturn(new Position(0, 0));
        when(pacman.getPosition()).thenReturn(new Position(5, 0));
        when(arena.getHeight()).thenReturn(20);

        Position target = behaviour.getAlivePosition(ghost, arena, pacman, true);
        assertEquals(new Position(0, 20), target); // Clyde retreats to bottom-left corner
    }

    @Test
    void testClydeNumberOfCollectibles() {
        GhostMovementBehaviour behaviour = new ClydeMovementBehaviour();
        when(ghost.isInsideGate()).thenReturn(true);

        when(arena.getCollectedCollectibles()).thenReturn(50);

        Position target = behaviour.getAlivePosition(ghost, arena, pacman, false);
        assertEquals(new Position(10, 11), target); // Clyde moves to a fixed position

        when(arena.getCollectedCollectibles()).thenReturn(60);
        when(arena.getGhostGate()).thenReturn(new GhostGate(new Position(5, 5)));

        target = behaviour.getAlivePosition(ghost, arena, pacman, false);
        assertEquals(new Position(5, 5), target); // Clyde moves to a fixed position
    }

    @Test
    void testClydeScatterMode() {
        GhostMovementBehaviour behaviour = new ClydeMovementBehaviour();
        when(ghost.isInsideGate()).thenReturn(false);

        when(arena.getCollectedCollectibles()).thenReturn(70);
        when(arena.getHeight()).thenReturn(20);

        Position target = behaviour.getAlivePosition(ghost, arena, pacman, false);
        assertEquals(new Position(0, 20), target); // Clyde moves to a fixed position
    }

}
