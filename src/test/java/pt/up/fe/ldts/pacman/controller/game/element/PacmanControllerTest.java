package pt.up.fe.ldts.pacman.controller.game.element;

import net.jqwik.api.*;
import net.jqwik.api.lifecycle.BeforeTry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.up.fe.ldts.pacman.Game;
import pt.up.fe.ldts.pacman.gui.GUI;
import pt.up.fe.ldts.pacman.model.Position;
import pt.up.fe.ldts.pacman.model.game.Arena;
import pt.up.fe.ldts.pacman.model.game.element.Direction;
import pt.up.fe.ldts.pacman.model.game.element.GhostGate;
import pt.up.fe.ldts.pacman.model.game.element.Wall;
import pt.up.fe.ldts.pacman.model.game.element.pacman.Pacman;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PacmanControllerTest {

    private PacmanController pacmanController;
    private Arena arena;
    private Game game;
    private Pacman pacman;

    @BeforeEach
    @BeforeTry
    void setUp() {
        arena = mock(Arena.class);
        game = mock(Game.class);

        pacman = mock(Pacman.class);
        when(arena.getPacmans()).thenReturn(Collections.singletonList(pacman));

        GhostGate ghostGate = mock(GhostGate.class);
        when(ghostGate.getPosition()).thenReturn(new Position(0, 0));
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
    void testPacmanMovesWhenItCan() {
        when(arena.getWalls()).thenReturn(Set.of()); //no walls
        when(arena.isEmpty(any())).thenReturn(true);
        Pacman realpacman = new Pacman(new Position(10, 10));
        realpacman.setSpeed(1); //max speed possible
        realpacman.setCounter(0);
        when(arena.getPacmans()).thenReturn(List.of(realpacman));

        pacmanController.step(game, List.of(), 0);

        assertEquals(1, realpacman.getCounter()); //assert pacman moves one intermediate position
    }

    @Test
    void testSpeed() {
        when(pacman.getSpeed()).thenReturn(Arena.PACMAN_NORMAL_SPEED);

        pacmanController.step(game, List.of(), 0); //will move
        pacmanController.step(game, List.of(), 1); // will not move

        verify(pacman, times(1)).getCounter();
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

    @Property
    void testKeepLastDesiredDirection(@ForAll("listOfActions") List<GUI.ACTION> actions) throws NoSuchFieldException, IllegalAccessException {
        Pacman pacman1 = new Pacman(new Position(10, 10));
        Pacman pacman2 = new Pacman(new Position(10, 10));
        when(arena.getPacmans()).thenReturn(List.of(pacman1, pacman2));

        Field privateField = PacmanController.class.getDeclaredField("desiredDirections");
        privateField.setAccessible(true);

        pacmanController.step(game, actions, 0);

        List<Direction> desiredDirections = (List<Direction>) privateField.get(pacmanController);
        if (actions.getLast() == GUI.ACTION.DOWN) assertEquals(Direction.DOWN, desiredDirections.getFirst());
        else if (actions.getLast() == GUI.ACTION.RIGHT) assertEquals(Direction.RIGHT, desiredDirections.getFirst());
        else if (actions.getLast() == GUI.ACTION.UP) assertEquals(Direction.UP, desiredDirections.getFirst());
        else assertEquals(Direction.LEFT, desiredDirections.getFirst());
    }

    @Test
    void testPacmanDoesNotMoveWhenCollidingWithAnotherPacman() throws NoSuchFieldException, IllegalAccessException {
        Pacman otherPacman = mock(Pacman.class); // Mock the other Pacman
        Position pacmanPosition = new Position(5, 5);
        Position otherPacmanPosition = new Position(6, 5);
        Position nextPosition = new Position(6, 5);

        when(arena.getPacmans()).thenReturn(List.of(pacman, otherPacman));
        when(pacman.getPosition()).thenReturn(pacmanPosition);
        when(pacman.getCounter()).thenReturn(0);
        when(pacman.getDirection()).thenReturn(Direction.RIGHT);
        when(pacman.getSpeed()).thenReturn(1);
        when(pacman.getNextPosition()).thenReturn(nextPosition);

        when(otherPacman.getPosition()).thenReturn(otherPacmanPosition);
        when(otherPacman.getSpeed()).thenReturn(1);
        when(otherPacman.getNextPosition()).thenReturn(otherPacmanPosition);
        when(otherPacman.collidingWith(any())).thenAnswer(invocation -> {
            Pacman other = invocation.getArgument(0);
            return otherPacmanPosition.equals(other.getPosition());
        });
        when(arena.isEmpty(any())).thenReturn(true);


        pacmanController.step(game, List.of(), 0); //without desired direction
        pacmanController.step(game, List.of(GUI.ACTION.RIGHT), 0); //with desired direction

        verify(pacman, never()).setDirection(Direction.RIGHT);
        verify(pacman, never()).incrementCounter();


        when(otherPacman.isDying()).thenReturn(true);

        pacmanController.step(game, List.of(GUI.ACTION.RIGHT), 0);

        //since the other pacman is now dead, moving right is possible
        verify(pacman).setDirection(Direction.RIGHT);
        verify(pacman).incrementCounter();

        Field privateField = PacmanController.class.getDeclaredField("desiredDirections");
        privateField.setAccessible(true);
        privateField.set(pacmanController, Arrays.asList(null, null));
        pacmanController.step(game, List.of(), 4);

        //since the other pacman is now dead, moving right is possible, now without desired direction,
        verify(pacman, times(2)).incrementCounter();
    }


    @Property
    void testNotMovingThroughWalls(@ForAll("arenas") Arena arena2, @ForAll("positions") Position startPosition, @ForAll("actions") GUI.ACTION action) {
        Pacman realpacman = new Pacman(new Position(startPosition));
        realpacman.setCounter(0);
        realpacman.setDirection(switch (action) {
            case UP -> Direction.UP;
            case RIGHT -> Direction.RIGHT;
            case DOWN -> Direction.DOWN;
            case LEFT -> Direction.LEFT;
            case null, default -> null;
        });
        arena2.getPacmans().clear(); //remove all pacmans that existed before this test
        arena2.addPacman(realpacman);
        Position expectedPosition = realpacman.getNextPosition();
        pacmanController = new PacmanController(arena2);

        for (int i = 0; i < 11; ++i) {
            assert action != null;
            pacmanController.step(game, List.of(action), 0);
        }

        //if the next position is not empty then pacman should not move
        if (!arena2.isEmpty(expectedPosition) || arena2.getGhostGate().getPosition().equals(expectedPosition)) {
            assertEquals(startPosition, realpacman.getPosition());
        }
    }

    @Test
    void testPacmanDoesNotMoveIntoGhostGate() {
        when(pacman.getPosition()).thenReturn(new Position(5, 5));
        when(pacman.getDirection()).thenReturn(Direction.UP);
        when(pacman.getSpeed()).thenReturn(1);
        when(pacman.getCounter()).thenReturn(0);
        when(pacman.getNextPosition()).thenReturn(new Position(6, 5));
        when(arena.isEmpty(any())).thenReturn(true);
        when(arena.getGhostGate()).thenReturn(new GhostGate(new Position(6, 5)));

        pacmanController.step(game, List.of(GUI.ACTION.RIGHT), 0);

        verify(pacman, never()).setDirection(Direction.RIGHT);
    }

    @Test
    void testTimeAndSpeedBoundaryConditions() {
        when(pacman.getSpeed()).thenReturn(2);
        when(pacman.getPosition()).thenReturn(new Position(5, 5));
        when(pacman.getCounter()).thenReturn(1);

        pacmanController.step(game, List.of(GUI.ACTION.UP), 1);
        verify(pacman, never()).incrementCounter();

        pacmanController.step(game, List.of(GUI.ACTION.UP), 4);
        verify(pacman).incrementCounter();
    }


    @Provide
    Arbitrary<Position> positions() {
        return Combinators.combine(
                Arbitraries.integers().between(1, 19), // Assuming arena size is 20x20
                Arbitraries.integers().between(1, 19)
        ).as(Position::new);
    }

    @Provide
    Arbitrary<GUI.ACTION> actions() {
        return Arbitraries.of(
                GUI.ACTION.RIGHT, GUI.ACTION.DOWN,
                GUI.ACTION.UP, GUI.ACTION.LEFT
        );
    }

    @Provide
    Arbitrary<Arena> arenas() {
        return Arbitraries.integers().between(10, 20).flatMap(size -> {
            Arena arena2 = new Arena(size, size);

            Arbitraries.integers().between(0, size - 1).tuple2()
                    .list().ofMinSize(size).ofMaxSize(size * 2)
                    .sample()
                    .forEach(coords -> arena2.addWall(new Wall(new Position(coords.get1(), coords.get2()))));

            arena2.setGhostGatePosition(new Position(size / 2, size / 2));

            return Arbitraries.just(arena2);
        });
    }

    @Provide
    Arbitrary<List<GUI.ACTION>> listOfActions() {
        Arbitrary<GUI.ACTION> actions = Arbitraries.of(
                GUI.ACTION.RIGHT, GUI.ACTION.DOWN,
                GUI.ACTION.UP, GUI.ACTION.LEFT
        );
        return actions.list().ofMinSize(1);
    }
}