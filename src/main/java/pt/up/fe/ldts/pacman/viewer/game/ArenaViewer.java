package pt.up.fe.ldts.pacman.viewer.game;


import pt.up.fe.ldts.pacman.model.game.Arena;
import pt.up.fe.ldts.pacman.model.game.element.Element;
import pt.up.fe.ldts.pacman.viewer.Renderer;
import pt.up.fe.ldts.pacman.viewer.Viewer;
import pt.up.fe.ldts.pacman.viewer.ViewerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

public class ArenaViewer extends Viewer {
    private final Arena arena;
    private final Map<Class<?>, Viewer> viewers;

    public ArenaViewer(Renderer renderer, Arena arena) throws IOException, URISyntaxException {
        super(renderer);
        this.arena = arena;
        this.viewers = ViewerFactory.createViewers(renderer);
    }

    @Override
    public void drawElement(Element element) {
        Viewer drawer = viewers.get(element.getClass());
        if (drawer != null) {
            drawer.drawElement(element);
        }
    }

    public void drawElements() {
        arena.getWalls().forEach(this::drawElement);
        arena.getCollectibles().forEach(this::drawElement);
        arena.getGhosts().forEach(this::drawElement);
        drawElement(arena.getPacman());
    }
}
