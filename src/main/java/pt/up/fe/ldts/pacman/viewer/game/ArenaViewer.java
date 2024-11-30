package pt.up.fe.ldts.pacman.viewer.game;

import com.googlecode.lanterna.graphics.TextGraphics;
import pt.up.fe.ldts.pacman.model.game.Arena;
import pt.up.fe.ldts.pacman.model.game.element.*;
import pt.up.fe.ldts.pacman.model.game.element.collectibles.*;
import pt.up.fe.ldts.pacman.model.game.element.ghost.*;
import pt.up.fe.ldts.pacman.model.game.element.pacman.Pacman;
import pt.up.fe.ldts.pacman.viewer.Renderer;
import pt.up.fe.ldts.pacman.viewer.Viewer;
import pt.up.fe.ldts.pacman.viewer.game.ghost.GhostViewer;
import pt.up.fe.ldts.pacman.viewer.game.pacman.PacmanViewer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
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

        // Loading static elements
        this.viewers.put(Wall.class, new ElementViewer(renderer, "src/main/resources/PNGs/wall.png"));
        this.viewers.put(Coin.class, new ElementViewer(renderer, "src/main/resources/PNGs/items/coin.png"));
        this.viewers.put(Apple.class, new ElementViewer(renderer, "src/main/resources/PNGs/items/apple.png"));
        this.viewers.put(Cherry.class, new ElementViewer(renderer, "src/main/resources/PNGs/items/cherry.png"));
        this.viewers.put(Key.class, new ElementViewer(renderer, "src/main/resources/PNGs/items/key.png"));
        this.viewers.put(Orange.class, new ElementViewer(renderer, "src/main/resources/PNGs/items/orange.png"));
        this.viewers.put(Strawberry.class, new ElementViewer(renderer, "src/main/resources/PNGs/items/strawberry.png"));

        // Loading Pacman images
        this.viewers.put(Pacman.class, new PacmanViewer(renderer, loadPacmanImages()));

        // Loading Ghosts with GhostState logic
        this.viewers.put(Blinky.class, new GhostViewer(renderer, loadGhostImages("blinky")));
        this.viewers.put(Pinky.class, new GhostViewer(renderer, loadGhostImages("pinky")));
        this.viewers.put(Inky.class, new GhostViewer(renderer, loadGhostImages("inky")));
        this.viewers.put(Clyde.class, new GhostViewer(renderer, loadGhostImages("clyde")));
    }

    private Map<GhostState, BufferedImage> loadGhostImages(String ghostName) throws IOException {
        return Map.of(
                GhostState.LEFT, ImageIO.read(new File("src/main/resources/PNGs/ghosts/" + ghostName + "/" + ghostName + "left.png")),
                GhostState.UP, ImageIO.read(new File("src/main/resources/PNGs/ghosts/" + ghostName + "/" + ghostName + "up.png")),
                GhostState.DOWN, ImageIO.read(new File("src/main/resources/PNGs/ghosts/" + ghostName + "/" + ghostName + "down.png")),
                GhostState.RIGHT, ImageIO.read(new File("src/main/resources/PNGs/ghosts/" + ghostName + "/" + ghostName + "right.png")),
                GhostState.SCARED, ImageIO.read(new File("src/main/resources/PNGs/ghosts/common/scaredghost.png")),
                GhostState.DEAD, ImageIO.read(new File("src/main/resources/PNGs/ghosts/common/deadghostright.png"))
        );
    }

    private Map<Direction, BufferedImage> loadPacmanImages() throws IOException {
        return Map.of(
                Direction.LEFT, ImageIO.read(new File("src/main/resources/PNGs/pacman/pacmanleft.png")),
                Direction.UP, ImageIO.read(new File("src/main/resources/PNGs/pacman/pacmanup.png")),
                Direction.DOWN, ImageIO.read(new File("src/main/resources/PNGs/pacman/pacmandown.png")),
                Direction.RIGHT, ImageIO.read(new File("src/main/resources/PNGs/pacman/pacmanright.png"))
        );
    }

    public void drawElement(TextGraphics graphics, Element element) {
        Viewer drawer = viewers.get(element.getClass());
        if (drawer != null) {
            drawer.drawElement(graphics, element);
        }
    }

    public void drawElements(TextGraphics graphics) {
        arena.getWalls().forEach(wall -> drawElement(graphics, wall));
        arena.getCollectibles().forEach(collectible -> drawElement(graphics, collectible));
        arena.getGhosts().forEach(ghost -> drawElement(graphics, ghost));
        drawElement(graphics, arena.getPacman());
    }
}
