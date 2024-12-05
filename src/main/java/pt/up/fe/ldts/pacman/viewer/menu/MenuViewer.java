package pt.up.fe.ldts.pacman.viewer.menu;

import pt.up.fe.ldts.pacman.gui.GUI;
import pt.up.fe.ldts.pacman.model.Element;
import pt.up.fe.ldts.pacman.model.game.Arena;
import pt.up.fe.ldts.pacman.model.menu.Menu;
import pt.up.fe.ldts.pacman.model.menu.element.TextBox;
import pt.up.fe.ldts.pacman.viewer.Viewer;
import pt.up.fe.ldts.pacman.viewer.ViewerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

public class MenuViewer extends Viewer<Menu> {
    private final Map<Class<?>, Viewer<TextBox>> viewers;

    public MenuViewer() throws IOException, URISyntaxException {
        this.viewers = ViewerFactory.createMenuViewers();
    }

    public void drawElement(GUI gui, Element element) {
        Viewer<TextBox> viewer = viewers.get(element.getClass());
        if (viewer != null) {
            viewer.drawElement(gui,(TextBox) element);
        }
    }

    public void drawElements(GUI gui, Menu menu) {
        menu.getOptions().forEach(textBox -> drawElement(gui,textBox));
    }

    @Override
    public void drawElement(GUI gui, Menu menu) {
        gui.clear();
        drawElements(gui,menu);
        try {
            gui.refresh();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
