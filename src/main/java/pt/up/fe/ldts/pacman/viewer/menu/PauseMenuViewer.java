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
    private boolean initialClear;

    public PauseMenuViewer() throws IOException {
        this.viewers = ViewerFactory.createPauseMenuViewers();
        this.initialClear = false;
    }

    public void drawElement(GUI gui, Element element, long frameCount) {
        Viewer<Element> viewer = viewers.get(element.getClass());
        if (viewer != null) {
            viewer.drawElement(gui, element, frameCount);
        }
    }

    public void drawElements(GUI gui, PauseMenu menu, long frameCount) {
        menu.getOptions().forEach(textBox -> drawElement(gui, textBox, frameCount));
        drawElement(gui, menu.getPauseSign(), frameCount);
        drawElement(gui, menu.getTitle(), frameCount);
    }

    @Override
    public void drawElement(GUI gui, PauseMenu menu, long frameCount) {
        if(!initialClear) {gui.clear(); initialClear = true;}
        drawElements(gui, menu, frameCount);
        try {
            gui.refresh();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
