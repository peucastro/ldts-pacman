package pt.up.fe.ldts.pacman.model.game;

import org.junit.jupiter.api.Test;
import pt.up.fe.ldts.pacman.model.Position;
import pt.up.fe.ldts.pacman.model.game.element.Wall;
import pt.up.fe.ldts.pacman.model.game.element.collectibles.*;
import pt.up.fe.ldts.pacman.model.game.element.ghost.*;
import pt.up.fe.ldts.pacman.model.game.element.pacman.Pacman;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

public class ArenaTest {

    @Test
    void testArenaInitialization() {
        int width = 20;
        int height = 15;

        Arena arena = new Arena(width, height);

        assertNotNull(arena.getPacman());
        assertEquals(width, arena.getWidth());
        assertEquals(height, arena.getHeight());
        assertTrue(arena.getWalls().isEmpty());
        assertTrue(arena.getCollectibles().isEmpty());
    }

    @Test
    void testSetAndGetWalls() {
        Arena arena = new Arena(20, 15);
        HashSet<Wall> walls = new HashSet<>();
        walls.add(new Wall(new Position(1, 1)));
        walls.add(new Wall(new Position(2, 2)));

        arena.setWalls(walls);

        assertEquals(2, arena.getWalls().size());
        assertTrue(arena.getWalls().contains(new Wall(new Position(1, 1))));
        assertTrue(arena.getWalls().contains(new Wall(new Position(2, 2))));
    }

    @Test
    void testPacmanInitialPosition() {
        Arena arena = new Arena(20, 15);

        Pacman pacman = arena.getPacman();

        assertEquals(new Position(10, 10), pacman.getPosition());
    }

    @Test
    void testAddWall() {
        Arena arena = new Arena(20, 15);
        Wall wall = new Wall(new Position(10, 10));
        arena.addWall(wall);
        assertTrue(arena.getWalls().contains(new Wall(new Position(10, 10))));
    }

    @Test
    void testSetPacmanPosition() {
        Arena arena = new Arena(20, 15);
        Position position = new Position(10, 10);
        arena.setPacmanPosition(position);
        assertEquals(position, arena.getPacman().getPosition());
    }

    @Test
    void testSetAndGetCollectibles() {
        Arena arena = new Arena(20, 15);
        HashSet<Collectible> testCollectibles = new HashSet<>();

        Collectible c1 = new Apple(new Position(10, 10));
        Collectible c2 = new Cherry(new Position(5, 5));
        Collectible c3 = new Strawberry(new Position(1, 1));
        Collectible c4 = new Coin(new Position(15, 15));
        Collectible c5 = new Key(new Position(2, 9));
        Collectible c6 = new Orange(new Position(2, 3));
        testCollectibles.add(c1);
        testCollectibles.add(c2);
        testCollectibles.add(c3);
        testCollectibles.add(c4);
        testCollectibles.add(c5);
        testCollectibles.add(c6);
        arena.setCollectibles(testCollectibles);

        assertEquals(testCollectibles, arena.getCollectibles());
        assertTrue(arena.getCollectibles().contains(c1));
        assertTrue(arena.getCollectibles().contains(c2));
        assertTrue(arena.getCollectibles().contains(c3));
        assertTrue(arena.getCollectibles().contains(c4));
    }

    @Test
    void testSetAndGetGhosts() {
        Arena arena = new Arena(20, 15);
        HashSet<Ghost> testGhost = new HashSet<>();

        Ghost g1 = new Clyde(new Position(10, 10));
        Ghost g2 = new Pinky(new Position(5, 5));
        Ghost g3 = new Inky(new Position(1, 1));
        Ghost g4 = new Blinky(new Position(15, 15));
        testGhost.add(g1);
        testGhost.add(g2);
        testGhost.add(g3);
        testGhost.add(g4);
        arena.setGhosts(testGhost);

        assertEquals(testGhost, arena.getGhosts());
        assertTrue(arena.getGhosts().contains(g1));
        assertTrue(arena.getGhosts().contains(g2));
        assertTrue(arena.getGhosts().contains(g3));
        assertTrue(arena.getGhosts().contains(g4));
    }

    @Test
    void testAddCollectible() {
        Arena arena = new Arena(20, 20);
        Collectible collectible = new Apple(new Position(10, 10));
        arena.addCollectible(collectible);
        assertTrue(arena.getCollectibles().contains(collectible));
    }

    @Test
    void testAddGhost() {
        Arena arena = new Arena(20, 20);
        Ghost ghost = new Blinky(new Position(10, 10));
        arena.addGhost(ghost);
        assertTrue(arena.getGhosts().contains(ghost));
    }

}
