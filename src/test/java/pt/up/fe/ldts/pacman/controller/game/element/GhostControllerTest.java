package pt.up.fe.ldts.pacman.controller.game.element;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.up.fe.ldts.pacman.Game;
import pt.up.fe.ldts.pacman.controller.game.element.behaviours.GhostMovementBehaviour;
import pt.up.fe.ldts.pacman.model.Position;
import pt.up.fe.ldts.pacman.model.game.Arena;
import pt.up.fe.ldts.pacman.model.game.element.Direction;
import pt.up.fe.ldts.pacman.model.game.element.GhostGate;
import pt.up.fe.ldts.pacman.model.game.element.ghost.*;
import pt.up.fe.ldts.pacman.model.game.element.pacman.Pacman;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.mockito.Mockito.*;

public class GhostControllerTest {
    private GhostController ghostController;
    private Arena arena;
    private Game game;
    private Ghost ghost;

    @BeforeEach
    void setUp() {
        arena = mock(Arena.class);
        game = mock(Game.class);
        ghost = mock(Blinky.class);
        Pacman pacman = mock(Pacman.class);

        GhostGate ghostGate = mock(GhostGate.class);
        when(arena.getGhostGate()).thenReturn(ghostGate);
        when(ghost.getState()).thenReturn(GhostState.ALIVE);

        when(arena.getPacmans()).thenReturn(List.of(pacman));
        when(arena.getGhosts()).thenReturn(Set.of(ghost));
        when(arena.isEmpty(any())).thenReturn(true);
        when(arena.getGhostGate().getPosition()).thenReturn(new Position(10, 10));

        when(ghost.getPosition()).thenReturn(new Position(5, 5));
        when(ghost.getDirection()).thenReturn(Direction.UP);
        when(ghost.getCounter()).thenReturn(0);
        when(ghost.getSpeed()).thenReturn(1);

        ghostController = new GhostController(arena);
    }

    @Test
    void testGhostMovesTowardsTarget() {
        ghostController.step(game, List.of(), 0);

        verify(ghost).setDirection(any(Direction.class));
        verify(ghost).incrementCounter();
    }

    @Test
    void testGhostResetsAtGate() {
        when(ghost.getPosition()).thenReturn(new Position(10, 10));
        when(ghost.isDead()).thenReturn(true);

        ghostController.step(game, List.of(), 0);

        verify(ghost).setState(GhostState.ALIVE);
        verify(ghost).setInsideGate();
        verify(ghost).setSpeed(Arena.GHOST_NORMAL_SPEED);
    }

    @Test
    void testTargetPacmanSwitchInMultiplayer() {
        Pacman pacman1 = mock(Pacman.class);
        Pacman pacman2 = mock(Pacman.class);
        when(arena.getPacmans()).thenReturn(Arrays.asList(pacman1, pacman2));
        when(pacman1.isDying()).thenReturn(false);
        when(pacman2.isDying()).thenReturn(false);

        GhostMovementBehaviour ghostMovementBehaviour = mock(GhostMovementBehaviour.class);
        Position targetPosition = new Position(7, 7);
        when(ghostMovementBehaviour.getTargetPosition(any(), any(), any(), anyBoolean())).thenReturn(targetPosition);

        GhostController ghostController = new GhostController(arena);
        ghostController.movementBehaviours = Map.of(
                Blinky.class, ghostMovementBehaviour,
                Pinky.class, ghostMovementBehaviour,
                Inky.class, ghostMovementBehaviour,
                Clyde.class, ghostMovementBehaviour
        );

        ghostController.step(game, List.of(), 0);

        verify(ghostMovementBehaviour).getTargetPosition(any(), any(), any(), anyBoolean());
    }

    @Test
    void testGhostDoesNotMoveThroughWall() {
        when(arena.isEmpty(any(Position.class))).thenReturn(false);

        when(ghost.getPosition()).thenReturn(new Position(5, 5));
        when(ghost.getDirection()).thenReturn(Direction.UP);
        when(ghost.getCounter()).thenReturn(0);

        ghostController.step(game, List.of(), 0);
        verify(ghost, never()).setPosition(any(Position.class));
    }

    @Test
    void testChaseAndScatterModeSwitch() {
        when(arena.isEmpty(any(Position.class))).thenReturn(true);

        GhostMovementBehaviour ghostMovementBehaviour = mock(GhostMovementBehaviour.class);
        when(ghostMovementBehaviour.getTargetPosition(any(), any(), any(), anyBoolean())).thenReturn(new Position(7, 7));

        ghostController.movementBehaviours = Map.of(
                Blinky.class, ghostMovementBehaviour,
                Pinky.class, ghostMovementBehaviour,
                Inky.class, ghostMovementBehaviour,
                Clyde.class, ghostMovementBehaviour
        );

        for (int frame = 0; frame < 451; frame++) {
            ghostController.step(game, List.of(), frame);
            if (frame == 450) {
                verify(ghost).invertDirection();
            }
        }
    }
}
