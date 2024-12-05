package pt.up.fe.ldts.pacman.viewer.game;


import pt.up.fe.ldts.pacman.gui.GUI;
import pt.up.fe.ldts.pacman.model.game.Arena;
import pt.up.fe.ldts.pacman.model.game.element.Element;
import pt.up.fe.ldts.pacman.viewer.Viewer;
import pt.up.fe.ldts.pacman.viewer.ViewerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

public class ArenaViewer extends Viewer<Arena> {
    private final Map<Class<?>, Viewer> viewers;

    public ArenaViewer() throws IOException, URISyntaxException {
        this.viewers = ViewerFactory.createViewers();
    }


    public void drawElement(GUI gui, Element element) {
        Viewer viewer = viewers.get(element.getClass());
        if (viewer != null) {
            viewer.drawElement(gui,element);
        }
    }

    @Override
    public void drawElement(GUI gui, Arena arena) {
        arena.getWalls().forEach(wall -> drawElement(gui,wall));
        arena.getCollectibles().forEach(collectible -> drawElement(gui,collectible));
        arena.getGhosts().forEach(ghost -> drawElement(gui,ghost));
        drawElement(gui,arena.getPacman());
    }

    public void draw(GUI gui, Arena arena) throws IOException {
        gui.clear();
        drawElement(gui,arena);
        gui.refresh();
    }
}
