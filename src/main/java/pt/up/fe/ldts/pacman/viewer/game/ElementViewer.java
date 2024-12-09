package pt.up.fe.ldts.pacman.viewer.game;

import com.googlecode.lanterna.graphics.BasicTextImage;
import pt.up.fe.ldts.pacman.gui.GUI;
import pt.up.fe.ldts.pacman.model.Element;
import pt.up.fe.ldts.pacman.model.Position;
import pt.up.fe.ldts.pacman.viewer.Viewer;

public class ElementViewer extends Viewer<Element> {
    private final BasicTextImage image;

    public ElementViewer(BasicTextImage image) {
        this.image = image;
    }

    @Override
    public void drawElement(GUI gui, Element element) {
        Position drawPos = new Position(element.getPosition().getX() * 11, element.getPosition().getY() * 11);
        gui.drawImage(drawPos, image);
    }
}
