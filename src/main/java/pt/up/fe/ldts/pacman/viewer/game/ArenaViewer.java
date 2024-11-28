package pt.up.fe.ldts.pacman.viewer.game;

import com.googlecode.lanterna.graphics.TextGraphics;
import pt.up.fe.ldts.pacman.model.game.Arena;
import pt.up.fe.ldts.pacman.model.game.element.*;
import pt.up.fe.ldts.pacman.model.game.element.ghost.Blinky;
import pt.up.fe.ldts.pacman.model.game.element.ghost.Clyde;
import pt.up.fe.ldts.pacman.model.game.element.ghost.Inky;
import pt.up.fe.ldts.pacman.model.game.element.ghost.Pinky;
import pt.up.fe.ldts.pacman.model.game.element.collectibles.*;
import pt.up.fe.ldts.pacman.model.game.element.pacman.Pacman;
import pt.up.fe.ldts.pacman.viewer.*;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

public class ArenaViewer extends Viewer {
    private final Arena arena;
    private final Map<Class<?>, Viewer> viewers;

    public ArenaViewer(Renderer renderer, Arena arena) throws IOException {
        super(renderer);
        this.arena = arena;
        this.viewers = new HashMap<>();

        this.viewers.put(Wall.class, new ElementViewer(renderer, "src/main/resources/PNGs/wall.png"));
        this.viewers.put(Coin.class, new ElementViewer(renderer, "src/main/resources/PNGs/items/coin.png"));
        this.viewers.put(Apple.class, new ElementViewer(renderer, "src/main/resources/PNGs/items/apple.png"));
        this.viewers.put(Cherry.class, new ElementViewer(renderer, "src/main/resources/PNGs/items/cherry.png"));
        this.viewers.put(Key.class, new ElementViewer(renderer, "src/main/resources/PNGs/items/key.png"));
        this.viewers.put(Orange.class, new ElementViewer(renderer, "src/main/resources/PNGs/items/orange.png"));
        this.viewers.put(Strawberry.class, new ElementViewer(renderer, "src/main/resources/PNGs/items/strawberry.png"));
        this.viewers.put(Pacman.class, new MultipleElementViewer<>(renderer, new HashMap<>(Map.ofEntries(
                Map.entry(Direction.LEFT, ImageIO.read(new File("src/main/resources/PNGs/pacman/pacmanleft.png"))),
                Map.entry(Direction.UP, ImageIO.read(new File("src/main/resources/PNGs/pacman/pacmanup.png"))),
                Map.entry(Direction.DOWN, ImageIO.read(new File("src/main/resources/PNGs/pacman/pacmandown.png"))),
                Map.entry(Direction.RIGHT, ImageIO.read(new File("src/main/resources/PNGs/pacman/pacmanright.png")))))));
        this.viewers.put(Blinky.class, new MultipleElementViewer<>(renderer, new HashMap<>(Map.ofEntries(
                Map.entry(Direction.LEFT, ImageIO.read(new File("src/main/resources/PNGs/ghosts/blinky/blinkyleft.png"))),
                Map.entry(Direction.UP, ImageIO.read(new File("src/main/resources/PNGs/ghosts/blinky/blinkyup.png"))),
                Map.entry(Direction.DOWN, ImageIO.read(new File("src/main/resources/PNGs/ghosts/blinky/blinkydown.png"))),
                Map.entry(Direction.RIGHT, ImageIO.read(new File("src/main/resources/PNGs/ghosts/blinky/blinkyright.png")))))));
        this.viewers.put(Pinky.class, new MultipleElementViewer<>(renderer, new HashMap<>(Map.ofEntries(
                Map.entry(Direction.LEFT, ImageIO.read(new File("src/main/resources/PNGs/ghosts/pinky/pinkyleft.png"))),
                Map.entry(Direction.UP, ImageIO.read(new File("src/main/resources/PNGs/ghosts/pinky/pinkyup.png"))),
                Map.entry(Direction.DOWN, ImageIO.read(new File("src/main/resources/PNGs/ghosts/pinky/pinkydown.png"))),
                Map.entry(Direction.RIGHT, ImageIO.read(new File("src/main/resources/PNGs/ghosts/pinky/pinkyright.png")))))));
        this.viewers.put(Inky.class, new MultipleElementViewer<>(renderer, new HashMap<>(Map.ofEntries(
                Map.entry(Direction.LEFT, ImageIO.read(new File("src/main/resources/PNGs/ghosts/inky/inkyleft.png"))),
                Map.entry(Direction.UP, ImageIO.read(new File("src/main/resources/PNGs/ghosts/inky/inkyup.png"))),
                Map.entry(Direction.DOWN, ImageIO.read(new File("src/main/resources/PNGs/ghosts/inky/inkydown.png"))),
                Map.entry(Direction.RIGHT, ImageIO.read(new File("src/main/resources/PNGs/ghosts/inky/inkyright.png")))))));
        this.viewers.put(Clyde.class, new MultipleElementViewer<>(renderer, new HashMap<>(Map.ofEntries(
                Map.entry(Direction.LEFT, ImageIO.read(new File("src/main/resources/PNGs/ghosts/clyde/clydeleft.png"))),
                Map.entry(Direction.UP, ImageIO.read(new File("src/main/resources/PNGs/ghosts/clyde/clydeup.png"))),
                Map.entry(Direction.DOWN, ImageIO.read(new File("src/main/resources/PNGs/ghosts/clyde/clydedown.png"))),
                Map.entry(Direction.RIGHT, ImageIO.read(new File("src/main/resources/PNGs/ghosts/clyde/clyderight.png")))))));
    }

    public void drawElement(TextGraphics graphics, Element element) {
        Viewer drawer = viewers.get(element.getClass());
        if (drawer != null) {
            drawer.drawElement(graphics, element);
        }
    }

    public void drawElements(TextGraphics graphics) {
        arena.getWalls().forEach(wall -> drawElement(graphics, wall));
        arena.getCollectibles().forEach(c -> drawElement(graphics, c));
        arena.getGhosts().forEach(ghost -> drawElement(graphics, ghost));
        drawElement(graphics, arena.getPacman());
    }
}
