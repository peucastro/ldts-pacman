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

public class BlinkyMovementBehaviourTest {
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
    void testBlinkyAliveChaseMode() {
        GhostMovementBehaviour behaviour = new BlinkyMovementBehaviour();
        when(ghost.isInsideGate()).thenReturn(false);
        when(pacman.getPosition()).thenReturn(new Position(7, 7));
        when(arena.getWidth()).thenReturn(29);
        when(arena.getHeight()).thenReturn(16);

        Position target = behaviour.getAlivePosition(ghost, arena, pacman, true);
        assertEquals(new Position(7, 7), target); // Chase mode, targets Pacman
    }

    @Test
    void testBlinkyAliveScatterMode() {
        GhostMovementBehaviour behaviour = new BlinkyMovementBehaviour();

        when(ghost.isInsideGate()).thenReturn(false);
        when(pacman.getPosition()).thenReturn(new Position(7, 7));
        when(arena.getWidth()).thenReturn(29);

        Position target = behaviour.getAlivePosition(ghost, arena, pacman, false);
        assertEquals(new Position(29, 0), target); // Scatter mode, top-right corner
    }
}
