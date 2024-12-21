package pt.up.fe.ldts.pacman.viewer.menu;

import pt.up.fe.ldts.pacman.gui.GUI;
import pt.up.fe.ldts.pacman.model.Position;
import pt.up.fe.ldts.pacman.model.menu.MainMenu;
import pt.up.fe.ldts.pacman.viewer.ModelViewer;
import pt.up.fe.ldts.pacman.viewer.ViewerFactory;

import java.io.IOException;

public class MainMenuViewer extends ModelViewer<MainMenu> {

    public MainMenuViewer() throws IOException {
        super(ViewerFactory.createMainMenuViewers());
    }

    @Override
    public void drawElements(GUI gui, MainMenu menu, long frameCount) {
        menu.getBlankPositions().forEach(position -> gui.erase(new Position(position.getX() * 11, position.getY() * 11)));
        menu.getOptions().forEach(textBox -> drawElement(gui, textBox, frameCount));
        drawElement(gui, menu.getPacman(), frameCount);

        drawElement(gui, menu.getBlinky(), frameCount);
        drawElement(gui, menu.getPinky(), frameCount);
        drawElement(gui, menu.getInky(), frameCount);
        drawElement(gui, menu.getClyde(), frameCount);

        drawElement(gui, menu.getTitle(), frameCount);
    }

}
