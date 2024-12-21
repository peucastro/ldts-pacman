package pt.up.fe.ldts.pacman.controller.game.element.behaviours;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.up.fe.ldts.pacman.model.Position;
import pt.up.fe.ldts.pacman.model.game.Arena;
import pt.up.fe.ldts.pacman.model.game.element.GhostGate;
import pt.up.fe.ldts.pacman.model.game.element.ghost.Ghost;
import pt.up.fe.ldts.pacman.model.game.element.ghost.GhostState;
import pt.up.fe.ldts.pacman.model.game.element.pacman.Pacman;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GhostMovementBehaviourTest {
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
    void testGetTargetPositionWhenAliveAndChaseMode() {
        GhostMovementBehaviour behaviour = new BlinkyMovementBehaviour();

        when(ghost.getState()).thenReturn(GhostState.ALIVE);
        when(ghost.isInsideGate()).thenReturn(false);
        when(pacman.getPosition()).thenReturn(new Position(5, 5));
        when(arena.getWidth()).thenReturn(29);
        when(arena.getHeight()).thenReturn(16);

        Position target = behaviour.getTargetPosition(ghost, arena, pacman, true);
        assertEquals(new Position(5, 5), target); // Chase mode, targets Pacman
    }

    @Test
    void testGetTargetPositionWhenDead() {
        GhostMovementBehaviour behaviour = new BlinkyMovementBehaviour();

        when(ghost.getState()).thenReturn(GhostState.DEAD);
        when(arena.getGhostGate()).thenReturn(new GhostGate(new Position(15, 6)));

        Position target = behaviour.getTargetPosition(ghost, arena, pacman, false);
        assertEquals(new Position(15, 6), target); // Targets ghost gate
    }

    @Test
    void testGetTargetPositionWhenScaredAndOutsideGate() {
        GhostMovementBehaviour behaviour = new BlinkyMovementBehaviour();

        when(ghost.getState()).thenReturn(GhostState.SCARED);
        when(ghost.isInsideGate()).thenReturn(false);

        Position target = behaviour.getTargetPosition(ghost, arena, pacman, false);
        assertTrue(target.getX() >= 0 && target.getX() < 20 && target.getY() >= 0 && target.getY() < 20);
    }

    @Test
    void testGetTargetPositionWhenScaredAndInsideGate() {
        GhostMovementBehaviour behaviour = new BlinkyMovementBehaviour();

        when(ghost.getState()).thenReturn(GhostState.SCARED);
        when(ghost.isInsideGate()).thenReturn(true);
        when(arena.getWidth()).thenReturn(29);
        when(arena.getHeight()).thenReturn(16);
        when(arena.getGhostGate()).thenReturn(new GhostGate(new Position(15, 6)));

        Position target = behaviour.getTargetPosition(ghost, arena, pacman, false);
        assertEquals(new Position(15, 6), target); // Scared, inside gate, defaults to Blinky's idle position
    }
}
