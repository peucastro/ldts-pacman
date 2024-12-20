package pt.up.fe.ldts.pacman.controller.game.element;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.up.fe.ldts.pacman.Game;
import pt.up.fe.ldts.pacman.gui.GUI;
import pt.up.fe.ldts.pacman.model.Position;
import pt.up.fe.ldts.pacman.model.game.Arena;
import pt.up.fe.ldts.pacman.model.game.element.Direction;
import pt.up.fe.ldts.pacman.model.game.element.pacman.Pacman;
import pt.up.fe.ldts.pacman.model.game.element.GhostGate;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class PacmanControllerTest {

    private PacmanController pacmanController;
    private Arena arena;
    private Game game;
    private Pacman pacman;
    private GhostGate ghostGate;

    @BeforeEach
    void setUp() {
        arena = mock(Arena.class);
        game = mock(Game.class);

        pacman = mock(Pacman.class);
        when(arena.getPacmans()).thenReturn(Arrays.asList(pacman));

        ghostGate = mock(GhostGate.class);
        when(arena.getGhostGate()).thenReturn(ghostGate);
        when(ghostGate.getPosition()).thenReturn(new Position(10, 10));

        pacmanController = new PacmanController(arena);
        doNothing().when(pacman).incrementCounter();
    }

    @Test
    void testPacmanMovesUp() {
        Position initialPosition = new Position(5, 5);
        when(pacman.getPosition()).thenReturn(initialPosition);
        when(pacman.getDirection()).thenReturn(Direction.RIGHT);
        when(pacman.getSpeed()).thenReturn(1);
        when(pacman.getCounter()).thenReturn(0);
        when(arena.isEmpty(any())).thenReturn(true);

        pacmanController.step(game, List.of(GUI.ACTION.UP), 0);

        verify(pacman).setDirection(Direction.UP);
        verify(pacman).incrementCounter();
    }

    @Test
    void testPacmanDoesNotMoveWhenSpeedCounterIsGreaterThanZero() {
        Position initialPosition = new Position(5, 5);
        when(pacman.getPosition()).thenReturn(initialPosition);
        when(pacman.getDirection()).thenReturn(Direction.UP);
        when(pacman.getSpeed()).thenReturn(1);
        when(pacman.getCounter()).thenReturn(1);

        pacmanController.step(game, List.of(GUI.ACTION.UP), 0);

        verify(pacman).incrementCounter();
    }

    @Test
    void testPacmanInvertsDirectionWhenOpposite() {
        Position initialPosition = new Position(5, 5);
        when(pacman.getPosition()).thenReturn(initialPosition);
        when(pacman.getDirection()).thenReturn(Direction.RIGHT);
        when(pacman.getSpeed()).thenReturn(1);

        pacmanController.step(game, List.of(GUI.ACTION.LEFT), 0);

        verify(pacman).invertDirection();
    }

    @Test
    void testPacmanDoesNotMoveWhenDying() {
        // Setup mock behavior for position and direction
        Position initialPosition = new Position(5, 5);
        when(pacman.getPosition()).thenReturn(initialPosition);
        when(pacman.getDirection()).thenReturn(Direction.UP);
        when(pacman.getSpeed()).thenReturn(1);
        when(pacman.getCounter()).thenReturn(0);

        when(pacman.isDying()).thenReturn(true);

        pacmanController.step(game, List.of(GUI.ACTION.UP), 0);

        verify(pacman, never()).incrementCounter();
    }

}
