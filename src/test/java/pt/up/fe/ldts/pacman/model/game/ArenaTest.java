package pt.up.fe.ldts.pacman.model.game;

import org.junit.jupiter.api.Test;
import pt.up.fe.ldts.pacman.model.game.element.pacman.Pacman;
import pt.up.fe.ldts.pacman.model.game.element.item.Coin;
import pt.up.fe.ldts.pacman.model.game.element.item.PowerUp;
import pt.up.fe.ldts.pacman.model.game.element.Wall;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

public class ArenaTest {

    @Test
    void testArenaInitialization() {
        int width = 20;
        int height = 15;

        Arena arena = new Arena(width, height);

        assertNotNull(arena.getPacman());
        assertNotNull(arena.getBlinky());
        assertNotNull(arena.getPinky());
        assertNotNull(arena.getInky());
        assertNotNull(arena.getClyde());
        assertEquals(width, arena.getWidth());
        assertEquals(height, arena.getHeight());
        assertTrue(arena.getWalls().isEmpty());
        assertTrue(arena.getCoins().isEmpty());
        assertTrue(arena.getPowerUps().isEmpty());
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
    void testSetAndGetCoins() {
        Arena arena = new Arena(20, 15);
        HashSet<Coin> coins = new HashSet<>();
        coins.add(new Coin(new Position(1, 1)));
        coins.add(new Coin(new Position(3, 4)));

        arena.setCoins(coins);

        assertEquals(2, arena.getCoins().size());
        assertTrue(arena.getCoins().contains(new Coin(new Position(1, 1))));
        assertTrue(arena.getCoins().contains(new Coin(new Position(3, 4))));
    }

    @Test
    void testSetAndGetPowerUps() {
        Arena arena = new Arena(20, 15);
        HashSet<PowerUp> powerUps = new HashSet<>();
        powerUps.add(new PowerUp(new Position(5, 5)));
        powerUps.add(new PowerUp(new Position(6, 7)));

        arena.setPowerUps(powerUps);

        assertEquals(2, arena.getPowerUps().size());
        assertTrue(arena.getPowerUps().contains(new PowerUp(new Position(5, 5))));
        assertTrue(arena.getPowerUps().contains(new PowerUp(new Position(6, 7))));
    }

    @Test
    void testPacmanInitialPosition() {
        Arena arena = new Arena(20, 15);

        Pacman pacman = arena.getPacman();

        assertEquals(new Position(0, 0), pacman.getPosition());
    }
}
