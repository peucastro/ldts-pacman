package pt.up.fe.ldts.pacman.viewer.menu;

import pt.up.fe.ldts.pacman.gui.GUI;
import pt.up.fe.ldts.pacman.model.menu.MapSelectionMenu;
import pt.up.fe.ldts.pacman.viewer.ModelViewer;
import pt.up.fe.ldts.pacman.viewer.ViewerFactory;

import java.io.IOException;

public class MapSelectionMenuViewer extends ModelViewer<MapSelectionMenu> {

    public MapSelectionMenuViewer() throws IOException {
        super(ViewerFactory.createMapSelectionMenuViewers());
    }

    @Override
    public void drawElements(GUI gui, MapSelectionMenu menu, long frameCount) {
        menu.getOptions().forEach(textBox -> drawElement(gui, textBox, frameCount));
        drawElement(gui, menu.getTitle(), frameCount);
    }

}
