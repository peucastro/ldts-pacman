package pt.up.fe.ldts.pacman.viewer.game;

import com.googlecode.lanterna.graphics.BasicTextImage;
import com.googlecode.lanterna.graphics.TextImage;
import pt.up.fe.ldts.pacman.gui.GUI;
import pt.up.fe.ldts.pacman.model.Element;
import pt.up.fe.ldts.pacman.viewer.Viewer;

import java.awt.image.BufferedImage;

public class ElementViewer extends Viewer<Element> {
    private final BasicTextImage image;

    public ElementViewer(BasicTextImage image){
        this.image = image;
    }

    @Override
    public void drawElement(GUI gui, Element element) {
        gui.drawImage(element.getPosition(), image);
    }
}
