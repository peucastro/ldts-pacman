package pt.up.fe.ldts.pacman.controller.game.element.behaviours;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.up.fe.ldts.pacman.model.Position;
import pt.up.fe.ldts.pacman.model.game.Arena;
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
        when(pacman.getPosition()).thenReturn(new Position(10, 10));

        Position target = behaviour.getAlivePosition(ghost, arena, pacman, true);
        assertEquals(new Position(10, 10), target); // Clyde chases Pacman
    }

    @Test
    void testClydeAliveChaseModeNearPacman() {
        GhostMovementBehaviour behaviour = new ClydeMovementBehaviour();

        when(ghost.isInsideGate()).thenReturn(false);
        when(arena.getCollectedCollectibles()).thenReturn(70);
        when(ghost.getPosition()).thenReturn(new Position(0, 0));
        when(pacman.getPosition()).thenReturn(new Position(2, 2));

        Position target = behaviour.getAlivePosition(ghost, arena, pacman, true);
        assertEquals(new Position(0, arena.getHeight()), target); // Clyde retreats to bottom-left corner
    }

    @Test
    void testClydeLowCollectibles() {
        GhostMovementBehaviour behaviour = new ClydeMovementBehaviour();

        when(arena.getCollectedCollectibles()).thenReturn(50);

        Position target = behaviour.getAlivePosition(ghost, arena, pacman, false);
        assertEquals(new Position(10, 11), target); // Clyde moves to a fixed position
    }

}
