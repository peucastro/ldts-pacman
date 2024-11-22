package pt.up.fe.ldts.pacman.viewer;

import com.googlecode.lanterna.graphics.TextGraphics;
import pt.up.fe.ldts.pacman.model.game.Position;
import pt.up.fe.ldts.pacman.model.game.element.Element;

import java.awt.image.BufferedImage;

public interface Viewable {
    void drawElement(TextGraphics graphics, Element element);
    void draw(TextGraphics graphics, Position position, BufferedImage image);
}
