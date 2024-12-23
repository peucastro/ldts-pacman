package pt.up.fe.ldts.pacman.model.game.element.pacman;

import org.junit.jupiter.api.Test;
import pt.up.fe.ldts.pacman.model.Position;
import pt.up.fe.ldts.pacman.model.game.Arena;

import static org.junit.jupiter.api.Assertions.*;

public class PacmanTest {

    @Test
    void testPacmanInitialization() {
        Pacman pacman = new Pacman(new Position(10, 10));

        assertEquals(3, pacman.getLife());

        assertFalse(pacman.isDying());

        assertEquals(new Position(10, 10), pacman.getPosition());

        assertEquals(Arena.PACMAN_NORMAL_SPEED ,pacman.getSpeed());
    }

    @Test
    void testDecreaseLife() {
        Pacman pacman = new Pacman(new Position(10, 10));

        pacman.decreaseLife();
        assertEquals(2, pacman.getLife());

        pacman.decreaseLife();
        assertEquals(1, pacman.getLife());

        pacman.decreaseLife();
        assertEquals(0, pacman.getLife());
    }

    @Test
    void testSetLife() {
        Pacman pacman = new Pacman(new Position(10, 10));

        pacman.setLife(5);
        assertEquals(5, pacman.getLife());

        pacman.setLife(1);
        assertEquals(1, pacman.getLife());
    }

    @Test
    void testSetRespawnPosition() {
        Pacman pacman = new Pacman(new Position(10, 10));

        Position respawnPosition = new Position(15, 15);
        pacman.setRespawnPosition(respawnPosition);

        assertEquals(respawnPosition, pacman.getRespawnPosition());
    }

    @Test
    void testIsDying() {
        Pacman pacman = new Pacman(new Position(10, 10));

        assertFalse(pacman.isDying());

        pacman.setDying(true);

        assertTrue(pacman.isDying());

        pacman.setDying(false);

        assertFalse(pacman.isDying());
    }
}
