package pt.up.fe.ldts.pacman.viewer.game;

import pt.up.fe.ldts.pacman.gui.GUI;
import pt.up.fe.ldts.pacman.model.game.element.Element;
import pt.up.fe.ldts.pacman.viewer.Viewer;

import java.awt.image.BufferedImage;

public class ElementViewer extends Viewer<Element> {
    private final BufferedImage image;

    public ElementViewer(BufferedImage image){
        this.image = image;
    }

    @Override
    public void drawElement(GUI gui, Element element) {
        gui.drawImage(element.getPosition(), image);
    }
}
