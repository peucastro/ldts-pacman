package pt.up.fe.ldts.pacman.controller.game.element;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.up.fe.ldts.pacman.Game;
import pt.up.fe.ldts.pacman.audio.AudioPlayer;
import pt.up.fe.ldts.pacman.gui.GUI;
import pt.up.fe.ldts.pacman.model.Position;
import pt.up.fe.ldts.pacman.model.game.Arena;
import pt.up.fe.ldts.pacman.model.game.element.Direction;
import pt.up.fe.ldts.pacman.model.game.element.pacman.Pacman;
import pt.up.fe.ldts.pacman.model.game.element.GhostGate;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PacmanControllerTest {

    private PacmanController pacmanController;
    private Arena arena;
    private Game game;
    private Pacman pacman;

    @BeforeEach
    void setUp() {
        arena = mock(Arena.class);
        game = mock(Game.class);

        pacman = mock(Pacman.class);
        when(arena.getPacmans()).thenReturn(Collections.singletonList(pacman));

        GhostGate ghostGate = mock(GhostGate.class);
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

    @Test
    void keepLastDesiredDirection() throws NoSuchFieldException, IllegalAccessException {
        Pacman pacman2 = mock(Pacman.class);
        when(pacman.getCounter()).thenReturn(1); //do not move pacmans
        when(pacman2.getCounter()).thenReturn(1);
        when(pacman.getDirection()).thenReturn(Direction.UP);
        when(pacman2.getDirection()).thenReturn(Direction.UP);
        when(pacman.getSpeed()).thenReturn(1);
        when(pacman2.getSpeed()).thenReturn(1);
        when(arena.getPacmans()).thenReturn(List.of(pacman, pacman2));
        Field privateField = PacmanController.class.getDeclaredField("desiredDirections");
        privateField.setAccessible(true);
        List<GUI.ACTION> actions = List.of(
                GUI.ACTION.SELECT, GUI.ACTION.RIGHT, GUI.ACTION.D,
                GUI.ACTION.LEFT, GUI.ACTION.W, GUI.ACTION.UP,
                GUI.ACTION.A, GUI.ACTION.DOWN, GUI.ACTION.S
        );

        pacmanController.step(game, actions, 0);

        List<Direction> desiredDirections = (List<Direction>) privateField.get(pacmanController);
        assertEquals(Direction.DOWN, desiredDirections.getFirst());
        assertEquals(Direction.DOWN, desiredDirections.get(1));
    }
}
