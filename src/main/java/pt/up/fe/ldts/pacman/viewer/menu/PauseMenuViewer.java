package pt.up.fe.ldts.pacman.viewer.menu;

import pt.up.fe.ldts.pacman.gui.GUI;
import pt.up.fe.ldts.pacman.model.Element;
import pt.up.fe.ldts.pacman.model.menu.MainMenu;
import pt.up.fe.ldts.pacman.model.menu.PauseMenu;
import pt.up.fe.ldts.pacman.viewer.Viewer;
import pt.up.fe.ldts.pacman.viewer.ViewerFactory;

import java.io.IOException;
import java.util.Map;

public class PauseMenuViewer extends Viewer<PauseMenu> {
    private final Map<Class<?>, Viewer<Element>> viewers;

    public PauseMenuViewer() throws IOException {
        this.viewers = ViewerFactory.createMainMenuViewers();
    }

    public void drawElement(GUI gui, Element element) {
        Viewer<Element> viewer = viewers.get(element.getClass());
        if (viewer != null) {
            viewer.drawElement(gui, element);
        }
    }

    public void drawElements(GUI gui, PauseMenu menu) {
        menu.getOptions().forEach(textBox -> drawElement(gui, textBox));
        drawElement(gui, menu.getPauseSign());
        drawElement(gui, menu.getTitle());
    }

    @Override
    public void drawElement(GUI gui, PauseMenu menu) {
        gui.clear();
        drawElements(gui, menu);
        try {
            gui.refresh();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}