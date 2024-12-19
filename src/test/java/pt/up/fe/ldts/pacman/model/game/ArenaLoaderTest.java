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

        arenaLoader.loadMap("src/main/resources/Maps/testmap.txt");

        Assertions.assertEquals(new Position(14, 13), arena.getPacmans().getFirst().getPosition());
        Assertions.assertTrue(arena.getCollectibles().contains(new Orange(new Position(15, 10))));
        Assertions.assertTrue(arena.getCollectibles().contains(new Apple(new Position(12, 10))));
        Assertions.assertTrue(arena.getCollectibles().contains(new Cherry(new Position(13, 10))));
        Assertions.assertTrue(arena.getCollectibles().contains(new Key(new Position(14, 10))));
        Assertions.assertTrue(arena.getCollectibles().contains(new Strawberry(new Position(16, 10))));
        Assertions.assertTrue(arena.getCollectibles().contains(new Coin(new Position(5, 13))));
        Assertions.assertTrue(arena.getCollectibles().contains(new PowerUp(new Position(1,1))));
        Assertions.assertTrue(arena.getGhosts().contains(new Pinky(new Position(13, 8))));
        Assertions.assertTrue(arena.getGhosts().contains(new Inky(new Position(14, 8))));
        Assertions.assertTrue(arena.getGhosts().contains(new Clyde(new Position(15, 8))));
        Assertions.assertTrue(arena.getGhosts().contains(new Blinky(new Position(14, 7))));
        Assertions.assertTrue(arena.getWalls().contains(new Wall(new Position(0, 0))));
        String output = outputStream.toString(StandardCharsets.UTF_8);
        Assertions.assertEquals("Unknown element '*' at (2, 1)" + System.lineSeparator(), output);

        System.setOut(originalOut);
    }
}
