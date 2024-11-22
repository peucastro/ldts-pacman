package pt.up.fe.ldts.pacman.viewer.game;

import com.googlecode.lanterna.graphics.TextGraphics;
import pt.up.fe.ldts.pacman.model.game.Arena;
import pt.up.fe.ldts.pacman.model.game.element.*;
import pt.up.fe.ldts.pacman.model.game.element.ghost.Blinky;
import pt.up.fe.ldts.pacman.model.game.element.ghost.Clyde;
import pt.up.fe.ldts.pacman.model.game.element.ghost.Inky;
import pt.up.fe.ldts.pacman.model.game.element.ghost.Pinky;
import pt.up.fe.ldts.pacman.model.game.element.item.Apple;
import pt.up.fe.ldts.pacman.model.game.element.item.Cherry;
import pt.up.fe.ldts.pacman.model.game.element.item.*;
import pt.up.fe.ldts.pacman.model.game.element.pacman.Pacman;
import pt.up.fe.ldts.pacman.viewer.ElementViewer;
import pt.up.fe.ldts.pacman.viewer.GhostViewer;
import pt.up.fe.ldts.pacman.viewer.PacmanViewer;
import pt.up.fe.ldts.pacman.viewer.Viewer;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

public class ArenaViewer extends Viewer {
    private final Arena arena;
    private final Map<Class<?>, Viewable> viewers;

    public ArenaViewer(Arena arena) throws IOException {
        this.arena = arena;
        this.viewers = new HashMap<>();

        this.viewers.put(Wall.class, new ElementViewer("src/main/resources/PNGs/wall.png"));
        this.viewers.put(Coin.class, new ElementViewer("src/main/resources/PNGs/items/coin.png"));
        this.viewers.put(Apple.class, new ElementViewer("src/main/resources/PNGs/items/apple.png"));
        this.viewers.put(Cherry.class, new ElementViewer("src/main/resources/PNGs/items/cherry.png"));
        this.viewers.put(Key.class, new ElementViewer("src/main/resources/PNGs/items/key.png"));
        this.viewers.put(Orange.class, new ElementViewer("src/main/resources/PNGs/items/orange.png"));
        this.viewers.put(Strawberry.class, new ElementViewer("src/main/resources/PNGs/items/strawberry.png"));
        this.viewers.put(Pacman.class, new PacmanViewer(Map.ofEntries(
                Map.entry((int)'l', ImageIO.read(new File("src/main/resources/PNGs/pacman/pacmanleft.png"))),
                Map.entry((int)'u', ImageIO.read(new File("src/main/resources/PNGs/pacman/pacmanup.png"))),
                Map.entry((int)'d', ImageIO.read(new File("src/main/resources/PNGs/pacman/pacmandown.png"))),
                Map.entry((int)'r', ImageIO.read(new File("src/main/resources/PNGs/pacman/pacmanright.png"))),
                Map.entry((int)'c', ImageIO.read(new File("src/main/resources/PNGs/pacman/pacmanclosed.png"))))));
        this.viewers.put(Blinky.class, new ElementViewer("src/main/resources/PNGs/ghosts/blinky/blinkyright.png"));
        this.viewers.put(Pinky.class, new ElementViewer("src/main/resources/PNGs/ghosts/pinky/pinkyright.png"));
        this.viewers.put(Inky.class, new ElementViewer("src/main/resources/PNGs/ghosts//inky/inkyright.png"));
        this.viewers.put(Clyde.class, new ElementViewer("src/main/resources/PNGs/ghosts/clyde/clyderight.png"));
    }

    public void drawElement(TextGraphics graphics, Element element) {
        Viewable drawer = viewers.get(element.getClass());
        if (drawer != null) {
            drawer.drawElement(graphics, element);
        }
    }

    public void drawElements(TextGraphics graphics) {
        arena.getWalls().forEach(wall -> drawElement(graphics, wall));
        arena.getCollectibles().forEach(c -> drawElement(graphics, c));
        arena.getGhosts().forEach(ghost -> drawElement(graphics,ghost));
        drawElement(graphics, arena.getPacman());
    }
}
