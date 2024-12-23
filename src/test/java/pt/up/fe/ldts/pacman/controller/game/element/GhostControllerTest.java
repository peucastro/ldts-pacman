package pt.up.fe.ldts.pacman.controller.game.element;

import net.jqwik.api.*;
import net.jqwik.api.lifecycle.BeforeTry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.up.fe.ldts.pacman.Game;
import pt.up.fe.ldts.pacman.controller.game.element.behaviours.GhostMovementBehaviour;
import pt.up.fe.ldts.pacman.model.Position;
import pt.up.fe.ldts.pacman.model.game.Arena;
import pt.up.fe.ldts.pacman.model.game.element.Direction;
import pt.up.fe.ldts.pacman.model.game.element.GhostGate;
import pt.up.fe.ldts.pacman.model.game.element.Wall;
import pt.up.fe.ldts.pacman.model.game.element.ghost.*;
import pt.up.fe.ldts.pacman.model.game.element.pacman.Pacman;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
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
        when(arena.getWidth()).thenReturn(20);
        when(arena.getHeight()).thenReturn(20);

        when(ghost.getPosition()).thenReturn(new Position(5, 5));
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
    void testInvalidDirections(){
        ghost = new Blinky(new Position(3,1));
        ghost.setDirection(Direction.DOWN);
        ghost.setSpeed(1);
        ghost.setOutsideGate();
        Pacman pacman = new Pacman(new Position(3,0));
        when(arena.getGhosts()).thenReturn(Set.of(ghost));
        when(arena.getPacmans()).thenReturn(List.of(pacman));
        ghostController = new GhostController(arena);

        ghostController.step(game, null, 0);

        //even though moving up is closer, ghost cannot invert direction
        //even though right and left are at the same distance from pacman, right takes priority
        assertEquals(Direction.RIGHT, ghost.getDirection());
    }

    @Test
    void testCannotMoveToGateWhileAlive(){
        ghost = new Blinky(new Position(3,2));
        ghost.setDirection(Direction.DOWN);
        ghost.setSpeed(1);
        ghost.setOutsideGate();
        Pacman pacman = new Pacman(new Position(3,0));
        when(arena.getGhosts()).thenReturn(Set.of(ghost));
        when(arena.getPacmans()).thenReturn(List.of(pacman));
        when(arena.getGhostGate()).thenReturn(new GhostGate(new Position(3,1)));
        ghostController = new GhostController(arena);

        ghostController.step(game, null, 0);

        //even though moving up is closer, ghost cannot move to the ghost gate if outside and alive
        assertNotEquals(Direction.UP, ghost.getDirection());
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
    void testTargetPacmanSwitchInMultiplayer() throws IllegalAccessException, NoSuchFieldException {
        Pacman pacman1 = new Pacman(new Position(0,0));
        Pacman pacman2 = new Pacman(new Position(2,0));
        when(arena.getPacmans()).thenReturn(Arrays.asList(pacman1, pacman2));
        ghost = new Blinky(new Position(1,0));
        ghost.setOutsideGate();
        ghost.setDirection(Direction.DOWN);
        ghost.setSpeed(1);
        ghost.setCounter(0);
        when(arena.getGhosts()).thenReturn(Set.of(ghost));
        ghostController = new GhostController(arena);

        Field privateField = GhostController.class.getDeclaredField("frameCount");
        privateField.setAccessible(true);

        privateField.set(ghostController,3999);

        ghostController.step(game, null, 0);

        //blinky will follow the first pacman
        assertEquals(Direction.LEFT, ghost.getDirection());

        ghost.setCounter(0);
        ghost.setDirection(Direction.DOWN);
        ghostController.step(game, null, 0);

        //blinky will follow the second pacman
        assertEquals(Direction.RIGHT, ghost.getDirection());


        Field privateField2 = GhostController.class.getDeclaredField("targetPacman");
        privateField2.setAccessible(true);
        privateField2.set(ghostController,0);
        pacman2.setDying(true);
        ghost.setCounter(0);
        ghost.setDirection(Direction.DOWN);
        privateField.set(ghostController,3999);

        ghostController.step(game, null, 0);

        //blinky will follow the first pacman
        assertEquals(Direction.LEFT, ghost.getDirection());

        ghost.setCounter(0);
        ghost.setDirection(Direction.DOWN);
        ghostController.step(game, null, 0);

        //blinky will follow the first pacman again since the second is dead
        assertEquals(Direction.LEFT, ghost.getDirection());
    }

    @Test
    void testGhostSpeed(){
        when(ghost.getSpeed()).thenReturn(Arena.GHOST_NORMAL_SPEED);
        when(arena.getGhosts()).thenReturn(Set.of(ghost));
        ghostController = new GhostController(arena);

        ghostController.step(game, null, 0); //will move
        ghostController.step(game, null, 1); //will not move

        verify(ghost, times(1)).incrementCounter();
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
    void testChaseAndScatterModeSwitch() throws IllegalAccessException, NoSuchFieldException {
        when(arena.isEmpty(any(Position.class))).thenReturn(true);

        GhostMovementBehaviour ghostMovementBehaviour = mock(GhostMovementBehaviour.class);
        when(ghostMovementBehaviour.getTargetPosition(any(), any(), any(), anyBoolean())).thenReturn(new Position(7, 7));


        Field privateField = GhostController.class.getDeclaredField("movementBehaviours");
        privateField.setAccessible(true);
        privateField.set(ghostController, Map.of(
                Blinky.class, ghostMovementBehaviour,
                Pinky.class, ghostMovementBehaviour,
                Inky.class, ghostMovementBehaviour,
                Clyde.class, ghostMovementBehaviour
        ));

        for (int frame = 0; frame < 451; frame++) {
            ghostController.step(game, List.of(), frame);
            if (frame == 450) {
                verify(ghost).invertDirection();
            }
        }
    }


    @Test
    void ghostLeavesGhostGate(){
        Blinky blinky = new Blinky(new Position(10,11));
        blinky.setCounter(10); //blinky is one intermediate position away from the ghost gate
        blinky.setDirection(Direction.UP);
        blinky.setInsideGate(); //blinky is inside the ghost box
        when(arena.getGhosts()).thenReturn(Set.of(blinky));

        ghostController.step(game, List.of(), 0); //blinky arrives at the ghost gate
        ghostController.step(game, List.of(), 0); //as counter = 0, blinky leave the ghost box and decides on the next direction

        assertFalse(blinky.isInsideGate());
    }

    @Test
    void testChaseModeCondition() throws NoSuchFieldException, IllegalAccessException {
        ghost = new Pinky(new Position(1,0));
        ghost.setDirection(Direction.DOWN);
        ghost.setSpeed(1);
        ghost.setOutsideGate();
        Pacman pacman = new Pacman(new Position(3,0));
        pacman.setDirection(Direction.RIGHT);
        when(arena.getGhosts()).thenReturn(Set.of(ghost));
        when(arena.getPacmans()).thenReturn(List.of(pacman));
        ghostController = new GhostController(arena);

        Field privateField = GhostController.class.getDeclaredField("frameCount");
        privateField.setAccessible(true);


        privateField.set(ghostController, 449); //scatter mode
        ghostController.step(game, null, 0);

        assertEquals(Direction.LEFT, ghost.getDirection());//pinky scatters to the left

        ghost.setCounter(0);
        ghostController.step(game, null, 0);//goes into chase mode

        assertEquals(Direction.RIGHT, ghost.getDirection());//pinky chases pacman to the right


        ghost.setCounter(0);
        ghost.setDirection(Direction.DOWN);
        privateField.set(ghostController, 2699); //chase mode
        ghostController.step(game, null, 0);

        assertEquals(Direction.RIGHT, ghost.getDirection());//pinky chases pacman to the right

        ghost.setCounter(0);
        ghost.setDirection(Direction.DOWN);
        ghostController.step(game, null, 0);//goes into chase mode

        assertEquals(Direction.LEFT, ghost.getDirection());//pinky scatters to the left

        privateField.set(ghostController, 3199); //scatter mode
        ghostController.step(game, null, 0);

        assertEquals(Direction.LEFT, ghost.getDirection());//pinky scatters to the left

        ghost.setCounter(0);
        ghostController.step(game, null, 0);//goes into chase mode

        assertEquals(Direction.RIGHT, ghost.getDirection());//pinky chases pacman to the right
    }

    @Test
    void testGhostChangesDirectionWhenBlockedByWall() {
        when(arena.isEmpty(any(Position.class))).thenReturn(false);

        when(ghost.getPosition()).thenReturn(new Position(5, 5));
        when(ghost.getCounter()).thenReturn(0);

        ghostController.step(game, List.of(), 0);

        verify(ghost, never()).setPosition(any(Position.class));

        assertNotEquals(Direction.UP, ghost.getDirection());
    }

    @Test
    void testGhostSpeedWhenRevived() {
        when(ghost.isDead()).thenReturn(true);
        when(ghost.getPosition()).thenReturn(new Position(10,10));
        ghostController.step(game, List.of(), 0);

        verify(ghost).setSpeed(Arena.GHOST_NORMAL_SPEED);
    }

    @BeforeTry
    void setUpPBT() {
        arena = new Arena(20, 20);
        ghostController = new GhostController(arena);

        arena.addWall(new Wall(new Position(5, 5)));
        arena.addWall(new Wall(new Position(6, 5)));
        arena.addWall(new Wall(new Position(7, 5)));
        arena.setGhostGatePosition(new Position(10, 10));
        arena.addPacman(new Pacman(new Position(15, 15)));
    }

    @Property
    void testNotMovingThroughWalls(@ForAll("positions") Position position, @ForAll("ghosts") Ghost ghost) {
        arena.addGhost(ghost);

        for (Direction direction : Direction.values()) {
            ghost.setPosition(position);
            ghost.setDirection(direction);
            Position nextPosition = switch (direction) {
                case UP -> position.getUp();
                case DOWN -> position.getDown();
                case LEFT -> position.getLeft();
                case RIGHT -> position.getRight();
            };

            for(int i = 0; i < 11; ++i) ghostController.step(game, List.of(), 0);


            if (!arena.isEmpty(nextPosition)) { //if the next position in the direction is not empty, ghost will change direction
                assertNotEquals(ghost.getDirection(), direction);
            }
        }
    }

    @Provide
    Arbitrary<Position> positions() {
        return Combinators.combine(
                Arbitraries.integers().between(1, 19),
                Arbitraries.integers().between(1, 19)
        ).as(Position::new);
    }

    @Provide
    Arbitrary<Ghost> ghosts() {
        return Arbitraries.of(
                new Blinky(new Position(10,10)),
                new Pinky(new Position(10,10)),
                new Inky(new Position(10,10)),
                new Clyde(new Position(10,10))
        );
    }
}