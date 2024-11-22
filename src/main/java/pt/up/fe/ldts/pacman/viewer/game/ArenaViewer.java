package pt.up.fe.ldts.pacman.viewer.game;

import com.googlecode.lanterna.graphics.TextGraphics;
import pt.up.fe.ldts.pacman.model.game.Arena;
import pt.up.fe.ldts.pacman.model.game.element.*;
import pt.up.fe.ldts.pacman.model.game.element.ghost.Blinky;
import pt.up.fe.ldts.pacman.model.game.element.ghost.Clyde;
import pt.up.fe.ldts.pacman.model.game.element.ghost.Inky;
import pt.up.fe.ldts.pacman.model.game.element.ghost.Pinky;
import pt.up.fe.ldts.pacman.model.game.element.item.Apple;
import pt.up.fe.ldts.pacman.model.game.element.item.Coin;
import pt.up.fe.ldts.pacman.model.game.element.pacman.Pacman;
import pt.up.fe.ldts.pacman.viewer.ElementViewer;
import pt.up.fe.ldts.pacman.viewer.Viewer;

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
        this.viewers.put(Pacman.class, new ElementViewer("src/main/resources/PNGs/pacman/pacman.png"));
        this.viewers.put(Blinky.class, new ElementViewer("src/main/resources/PNGs/ghosts/blinky/blinkyright.png"));
        this.viewers.put(Pinky.class, new ElementViewer("src/main/resources/PNGs/ghosts/pinky/pinkyright.png"));
        this.viewers.put(Inky.class, new ElementViewer("src/main/resources/PNGs/ghosts//inky/inkyright.png"));
        this.viewers.put(Clyde.class, new ElementViewer("src/main/resources/PNGs/ghosts/clyde/clyderight.png"));
    }

    private void drawElement(TextGraphics graphics, Element element) {
        Viewable drawer = viewers.get(element.getClass());
        if (drawer != null) {
            drawer.draw(graphics, element.getPosition());
        }
    }

    public void drawElements(TextGraphics graphics) {
        arena.getWalls().forEach(wall -> drawElement(graphics, wall));
        drawElement(graphics, arena.getPacman());
        drawElement(graphics, arena.getBlinky());
        drawElement(graphics, arena.getPinky());
        drawElement(graphics, arena.getInky());
        drawElement(graphics, arena.getClyde());
    }
}
