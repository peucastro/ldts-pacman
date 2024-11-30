package pt.up.fe.ldts.pacman.viewer;

import pt.up.fe.ldts.pacman.model.game.element.collectibles.*;
import pt.up.fe.ldts.pacman.model.game.element.ghost.Blinky;
import pt.up.fe.ldts.pacman.model.game.element.ghost.Clyde;
import pt.up.fe.ldts.pacman.model.game.element.ghost.Inky;
import pt.up.fe.ldts.pacman.model.game.element.ghost.Pinky;
import pt.up.fe.ldts.pacman.model.game.element.pacman.Pacman;
import pt.up.fe.ldts.pacman.viewer.game.ElementViewer;
import pt.up.fe.ldts.pacman.viewer.game.ImageLoader;
import pt.up.fe.ldts.pacman.viewer.game.ghost.GhostViewer;
import pt.up.fe.ldts.pacman.viewer.game.pacman.PacmanViewer;
import pt.up.fe.ldts.pacman.model.game.element.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ViewerFactory {
    public static Map<Class<?>, Viewer> createViewers(Renderer renderer) throws IOException {
        Map<Class<?>, Viewer> viewers = new HashMap<>();

        viewers.put(Wall.class, new ElementViewer(renderer, "src/main/resources/PNGs/wall.png"));
        viewers.put(Coin.class, new ElementViewer(renderer, "src/main/resources/PNGs/items/coin.png"));
        viewers.put(Apple.class, new ElementViewer(renderer, "src/main/resources/PNGs/items/apple.png"));
        viewers.put(Cherry.class, new ElementViewer(renderer, "src/main/resources/PNGs/items/cherry.png"));
        viewers.put(Key.class, new ElementViewer(renderer, "src/main/resources/PNGs/items/key.png"));
        viewers.put(Orange.class, new ElementViewer(renderer, "src/main/resources/PNGs/items/orange.png"));
        viewers.put(Strawberry.class, new ElementViewer(renderer, "src/main/resources/PNGs/items/strawberry.png"));

        viewers.put(Pacman.class, new PacmanViewer(renderer, ImageLoader.loadPacmanImages()));

        viewers.put(Blinky.class, new GhostViewer(renderer, ImageLoader.loadGhostImages("blinky")));
        viewers.put(Pinky.class, new GhostViewer(renderer, ImageLoader.loadGhostImages("pinky")));
        viewers.put(Inky.class, new GhostViewer(renderer, ImageLoader.loadGhostImages("inky")));
        viewers.put(Clyde.class, new GhostViewer(renderer, ImageLoader.loadGhostImages("clyde")));

        return viewers;
    }
}
