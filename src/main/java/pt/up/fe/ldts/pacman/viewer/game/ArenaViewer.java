package pt.up.fe.ldts.pacman.viewer.game;

import com.googlecode.lanterna.graphics.TextGraphics;
import pt.up.fe.ldts.pacman.model.game.Arena;
import pt.up.fe.ldts.pacman.model.game.element.Element;
import pt.up.fe.ldts.pacman.viewer.Renderer;
import pt.up.fe.ldts.pacman.viewer.Viewer;
import pt.up.fe.ldts.pacman.viewer.ViewerFactory;

import java.io.IOException;
import java.util.Map;

public class ArenaViewer extends Viewer {
    private final Arena arena;
    private final Map<Class<?>, Viewer> viewers;

    public ArenaViewer(Renderer renderer, Arena arena) throws IOException {
        super(renderer);
        this.arena = arena;
        this.viewers = ViewerFactory.createViewers(renderer);
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
