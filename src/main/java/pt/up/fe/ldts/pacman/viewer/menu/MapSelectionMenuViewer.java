package pt.up.fe.ldts.pacman.viewer.menu;

import pt.up.fe.ldts.pacman.gui.GUI;
import pt.up.fe.ldts.pacman.model.Element;
import pt.up.fe.ldts.pacman.model.menu.MapSelectionMenu;
import pt.up.fe.ldts.pacman.viewer.Viewer;
import pt.up.fe.ldts.pacman.viewer.ViewerFactory;

import java.io.IOException;
import java.util.Map;

public class MapSelectionMenuViewer extends Viewer<MapSelectionMenu> {
    private final Map<Class<?>, Viewer<Element>> viewers;
    private boolean initialClear;

    public MapSelectionMenuViewer() throws IOException {
        this.viewers = ViewerFactory.createMapSelectionMenuViewers();
        this.initialClear = false;
    }

    public void drawElement(GUI gui, Element element) {
        Viewer<Element> viewer = viewers.get(element.getClass());
        if (viewer != null) {
            viewer.drawElement(gui, element);
        }
    }

    public void drawElements(GUI gui, MapSelectionMenu menu) {
        menu.getOptions().forEach(textBox -> drawElement(gui, textBox));
        drawElement(gui, menu.getTitle());
    }

    @Override
    public void drawElement(GUI gui, MapSelectionMenu menu) {
        if(!initialClear) {gui.clear(); initialClear = true;}
        drawElements(gui, menu);
        try {
            gui.refresh();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
