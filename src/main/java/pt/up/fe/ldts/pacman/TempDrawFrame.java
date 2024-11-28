package pt.up.fe.ldts.pacman;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import pt.up.fe.ldts.pacman.model.game.Arena;
import pt.up.fe.ldts.pacman.model.game.ArenaLoader;
import pt.up.fe.ldts.pacman.model.game.Position;
import pt.up.fe.ldts.pacman.viewer.Renderer;
import pt.up.fe.ldts.pacman.viewer.game.ArenaViewer;
import pt.up.fe.ldts.pacman.viewer.game.Display;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;


public class TempDrawFrame {
    public static void main(String[] args) throws IOException, URISyntaxException, FontFormatException {

        Display display = new Display(new TerminalSize(280, 280));
        TextGraphics graphics = display.getScreen().newTextGraphics();

        Arena arena = new Arena(20, 20);
        ArenaLoader arenaLoader = new ArenaLoader(arena);
        arenaLoader.loadMap("src/main/resources/Maps/map.txt");
        Renderer renderer = new Renderer(graphics);
        ArenaViewer arenaViewer = new ArenaViewer(renderer, arena);
        arenaViewer.drawElements(graphics);

        int i = 0;
        while (i < 100) {
            display.getScreen().clear();
            arena.setPacmanPosition(new Position(arena.getPacman().getPosition().getX() - 1, arena.getPacman().getPosition().getY()));
            arenaViewer.drawElements(graphics);
            display.getScreen().refresh();
            i++;
        }
        display.getScreen().close();
    }
}
