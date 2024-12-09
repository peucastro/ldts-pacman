package pt.up.fe.ldts.pacman.model.game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pt.up.fe.ldts.pacman.model.Position;
import pt.up.fe.ldts.pacman.model.game.element.Wall;
import pt.up.fe.ldts.pacman.model.game.element.ghost.Blinky;
import pt.up.fe.ldts.pacman.model.game.element.ghost.Clyde;
import pt.up.fe.ldts.pacman.model.game.element.ghost.Inky;
import pt.up.fe.ldts.pacman.model.game.element.ghost.Pinky;
import pt.up.fe.ldts.pacman.model.game.element.collectibles.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class ArenaLoaderTest {

    @Test
    void testLoadMap() throws IOException {
        Arena arena = new Arena(20,20);
        ArenaLoader arenaLoader = new ArenaLoader(arena);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        arenaLoader.loadMap("src/main/resources/Maps/testmap.txt");

        Assertions.assertEquals(new Position(10*14,16*14), arena.getPacman().getPosition());
        Assertions.assertTrue(arena.getCollectibles().contains(new Orange(new Position(3*14,11*14))));
        Assertions.assertTrue(arena.getCollectibles().contains(new Apple(new Position(3*14,12*14))));
        Assertions.assertTrue(arena.getCollectibles().contains(new Cherry(new Position(3*14,13*14))));
        Assertions.assertTrue(arena.getCollectibles().contains(new Key(new Position(4*14,13*14))));
        Assertions.assertTrue(arena.getCollectibles().contains(new Strawberry(new Position(5*14,13*14))));
        Assertions.assertTrue(arena.getCollectibles().contains(new Coin(new Position(6*14, 13*14))));
        Assertions.assertTrue(arena.getGhosts().contains(new Pinky(new Position(8*14,11*14))));
        Assertions.assertTrue(arena.getGhosts().contains(new Inky(new Position(9*14,11*14))));
        Assertions.assertTrue(arena.getGhosts().contains(new Clyde(new Position(10*14,11*14))));
        Assertions.assertTrue(arena.getGhosts().contains(new Blinky(new Position(9*14,10*14))));
        Assertions.assertTrue(arena.getWalls().contains(new Wall(new Position(0,0))));

        String output = outputStream.toString(StandardCharsets.UTF_8);
        Assertions.assertEquals("Unknown element '*' at (14, 14)\n", output);

        System.setOut(originalOut);
    }
}
