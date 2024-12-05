package pt.up.fe.ldts.pacman.viewer;

import pt.up.fe.ldts.pacman.model.game.element.collectibles.*;
import pt.up.fe.ldts.pacman.model.game.element.ghost.Blinky;
import pt.up.fe.ldts.pacman.model.game.element.ghost.Clyde;
import pt.up.fe.ldts.pacman.model.game.element.ghost.Inky;
import pt.up.fe.ldts.pacman.model.game.element.ghost.Pinky;
import pt.up.fe.ldts.pacman.model.game.element.pacman.Pacman;
import pt.up.fe.ldts.pacman.viewer.game.ElementViewer;
import pt.up.fe.ldts.pacman.viewer.game.ImageLoader;
import pt.up.fe.ldts.pacman.viewer.game.MultipleElementViewer;
import pt.up.fe.ldts.pacman.model.game.element.*;
import pt.up.fe.ldts.pacman.viewer.game.strategies.GhostStrategy;
import pt.up.fe.ldts.pacman.viewer.game.strategies.PacmanStrategy;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class ViewerFactory {
    public static Map<Class<?>, Viewer<Element>> createViewers() throws IOException, URISyntaxException {
        Map<Class<?>, Viewer<Element>> viewers = new HashMap<>();

        viewers.put(Wall.class, new ElementViewer(ImageLoader.loadImage( "PNGs/wall.png")));
        viewers.put(Coin.class, new ElementViewer(ImageLoader.loadImage( "PNGs/items/coin.png")));
        viewers.put(Apple.class, new ElementViewer(ImageLoader.loadImage( "PNGs/items/apple.png")));
        viewers.put(Cherry.class, new ElementViewer(ImageLoader.loadImage( "PNGs/items/cherry.png")));
        viewers.put(Key.class, new ElementViewer(ImageLoader.loadImage( "PNGs/items/key.png")));
        viewers.put(Orange.class, new ElementViewer(ImageLoader.loadImage( "PNGs/items/orange.png")));
        viewers.put(Strawberry.class, new ElementViewer(ImageLoader.loadImage( "PNGs/items/strawberry.png")));

        viewers.put(Pacman.class, new MultipleElementViewer(new PacmanStrategy(), ImageLoader.loadPacmanImages()));

        viewers.put(Blinky.class, new MultipleElementViewer(new GhostStrategy(), ImageLoader.loadGhostImages("blinky")));
        viewers.put(Pinky.class, new MultipleElementViewer(new GhostStrategy(), ImageLoader.loadGhostImages("pinky")));
        viewers.put(Inky.class, new MultipleElementViewer(new GhostStrategy(), ImageLoader.loadGhostImages("inky")));
        viewers.put(Clyde.class, new MultipleElementViewer(new GhostStrategy(), ImageLoader.loadGhostImages("clyde")));

        return viewers;
    }
}
