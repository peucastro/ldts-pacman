package pt.up.fe.ldts.pacman.model.game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pt.up.fe.ldts.pacman.model.Position;
import pt.up.fe.ldts.pacman.model.game.element.Wall;
import pt.up.fe.ldts.pacman.model.game.element.collectibles.*;
import pt.up.fe.ldts.pacman.model.game.element.ghost.Blinky;
import pt.up.fe.ldts.pacman.model.game.element.ghost.Clyde;
import pt.up.fe.ldts.pacman.model.game.element.ghost.Inky;
import pt.up.fe.ldts.pacman.model.game.element.ghost.Pinky;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class ArenaLoaderTest {

    @Test
    void testLoadMap() throws IOException {
        Arena arena = new Arena(20, 20);
        ArenaLoader arenaLoader = new ArenaLoader(arena);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        arenaLoader.loadMap("src/test/resources/Maps/testmap.txt");

        Assertions.assertEquals(new Position(14, 13), arena.getPacmans().getFirst().getPosition());
        Assertions.assertNotNull(arena.getPacmans().getFirst().getRespawnPosition());
        Assertions.assertTrue(arena.getCollectibles().contains(new Orange(new Position(15, 10))));
        Assertions.assertTrue(arena.getCollectibles().contains(new Apple(new Position(12, 10))));
        Assertions.assertTrue(arena.getCollectibles().contains(new Cherry(new Position(13, 10))));
        Assertions.assertTrue(arena.getCollectibles().contains(new Key(new Position(14, 10))));
        Assertions.assertTrue(arena.getCollectibles().contains(new Strawberry(new Position(16, 10))));
        Assertions.assertTrue(arena.getCollectibles().contains(new Coin(new Position(5, 13))));
        Assertions.assertTrue(arena.getCollectibles().contains(new PowerUp(new Position(1, 1))));
        Assertions.assertEquals(new Position(14, 6), arena.getGhostGate().getPosition());

        Assertions.assertTrue(arena.getGhosts().contains(new Pinky(new Position(13, 8))));
        Pinky pinky = (Pinky) arena.getGhosts().stream().filter(ghost -> ghost.getClass() == Pinky.class).toArray()[0];
        Assertions.assertNotNull(pinky.getRespawnPosition());

        Assertions.assertTrue(arena.getGhosts().contains(new Inky(new Position(14, 8))));
        Inky inky = (Inky) arena.getGhosts().stream().filter(ghost -> ghost.getClass() == Inky.class).toArray()[0];
        Assertions.assertNotNull(inky.getRespawnPosition());

        Assertions.assertTrue(arena.getGhosts().contains(new Clyde(new Position(15, 8))));
        Clyde clyde = (Clyde) arena.getGhosts().stream().filter(ghost -> ghost.getClass() == Clyde.class).toArray()[0];
        Assertions.assertNotNull(clyde.getRespawnPosition());

        Assertions.assertTrue(arena.getGhosts().contains(new Blinky(new Position(14, 7))));
        Blinky blinky = (Blinky) arena.getGhosts().stream().filter(ghost -> ghost.getClass() == Blinky.class).toArray()[0];
        Assertions.assertNotNull(blinky.getRespawnPosition());

        Assertions.assertTrue(arena.getWalls().contains(new Wall(new Position(0, 0))));

        Assertions.assertTrue(arena.getBlankPositions().contains(new Position(14, 13))); //pacman's position
        Assertions.assertTrue(arena.getBlankPositions().contains(new Position(13, 8))); //pinky's position
        Assertions.assertTrue(arena.getBlankPositions().contains(new Position(14, 8))); //inky's position
        Assertions.assertTrue(arena.getBlankPositions().contains(new Position(15, 8))); //clyde's position
        Assertions.assertTrue(arena.getBlankPositions().contains(new Position(14, 7))); //blinky's position
        Assertions.assertTrue(arena.getBlankPositions().contains(new Position(2, 1))); //unknown element's position

        String output = outputStream.toString(StandardCharsets.UTF_8);
        Assertions.assertEquals("Unknown element '*' at (2, 1)" + System.lineSeparator(), output);

        System.setOut(originalOut);
    }
}
