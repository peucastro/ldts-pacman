package pt.up.fe.ldts.pacman;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import pt.up.fe.ldts.pacman.model.game.Arena;
import pt.up.fe.ldts.pacman.model.game.ArenaLoader;
import pt.up.fe.ldts.pacman.view.game.*;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;


public class TempDrawFrame {
    public static void main(String[] args) throws IOException, URISyntaxException, FontFormatException, InterruptedException {

        Display display = new Display(new TerminalSize(280, 280));
        TextGraphics graphics = display.getScreen().newTextGraphics();

        Arena arena = new Arena(20, 20);
        ArenaLoader arenaLoader = new ArenaLoader(arena);
        arenaLoader.loadMap("src/main/resources/Maps/map.txt");
        ArenaDrawer arenaDrawer = new ArenaDrawer(arena);
        arenaDrawer.drawEntities(graphics);

        display.getScreen().refresh();
        Thread.sleep(15000);
        display.getScreen().close();

    }
}
