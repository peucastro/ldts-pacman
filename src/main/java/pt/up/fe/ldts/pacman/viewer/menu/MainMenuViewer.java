package pt.up.fe.ldts.pacman.viewer.menu;

import pt.up.fe.ldts.pacman.gui.GUI;
import pt.up.fe.ldts.pacman.model.Element;
import pt.up.fe.ldts.pacman.model.Position;
import pt.up.fe.ldts.pacman.model.menu.MainMenu;
import pt.up.fe.ldts.pacman.viewer.Viewer;
import pt.up.fe.ldts.pacman.viewer.ViewerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

public class MainMenuViewer extends Viewer<MainMenu> {
    private final Map<Class<?>, Viewer<Element>> viewers;
    private boolean initialClear;

    public MainMenuViewer() throws IOException, URISyntaxException {
        this.viewers = ViewerFactory.createMainMenuViewers();
        this.initialClear = false;
    }

    public void drawElement(GUI gui, Element element) {
        Viewer<Element> viewer = viewers.get(element.getClass());
        if (viewer != null) {
            viewer.drawElement(gui, element);
        }
    }

    public void drawElements(GUI gui, MainMenu menu) {
        menu.getBlankPositions().forEach(position -> gui.erase(new Position(position.getX()*11, position.getY()*11)));
        menu.getOptions().forEach(textBox -> drawElement(gui, textBox));
        drawElement(gui, menu.getPacman());

        drawElement(gui, menu.getBlinky());
        drawElement(gui, menu.getPinky());
        drawElement(gui, menu.getInky());
        drawElement(gui, menu.getClyde());

        drawElement(gui, menu.getTitle());
    }

    @Override
    public void drawElement(GUI gui, MainMenu menu) {
        if(!initialClear) {gui.clear(); initialClear = true;}
        drawElements(gui, menu);
        try {
            gui.refresh();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
