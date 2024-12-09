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

        Assertions.assertEquals(new Position(10*11,16*11), arena.getPacman().getPosition());
        Assertions.assertTrue(arena.getCollectibles().contains(new Orange(new Position(3*11,11*11))));
        Assertions.assertTrue(arena.getCollectibles().contains(new Apple(new Position(3*11,12*11))));
        Assertions.assertTrue(arena.getCollectibles().contains(new Cherry(new Position(3*11,13*11))));
        Assertions.assertTrue(arena.getCollectibles().contains(new Key(new Position(4*11,13*11))));
        Assertions.assertTrue(arena.getCollectibles().contains(new Strawberry(new Position(5*11,13*11))));
        Assertions.assertTrue(arena.getCollectibles().contains(new Coin(new Position(6*11, 13*11))));
        Assertions.assertTrue(arena.getGhosts().contains(new Pinky(new Position(8*11,11*11))));
        Assertions.assertTrue(arena.getGhosts().contains(new Inky(new Position(9*11,11*11))));
        Assertions.assertTrue(arena.getGhosts().contains(new Clyde(new Position(10*11,11*11))));
        Assertions.assertTrue(arena.getGhosts().contains(new Blinky(new Position(9*11,10*11))));
        Assertions.assertTrue(arena.getWalls().contains(new Wall(new Position(0,0))));

        String output = outputStream.toString(StandardCharsets.UTF_8);
        Assertions.assertEquals("Unknown element '*' at (11, 11)\n", output);

        System.setOut(originalOut);
    }
}
