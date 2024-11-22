package pt.up.fe.ldts.pacman.viewer.game;

import com.googlecode.lanterna.graphics.TextGraphics;
import pt.up.fe.ldts.pacman.model.game.element.Element;
import pt.up.fe.ldts.pacman.model.game.element.pacman.Pacman;
import pt.up.fe.ldts.pacman.viewer.MultipleElementViewer;

import java.awt.image.BufferedImage;
import java.util.Map;

public class PacmanViewer extends MultipleElementViewer {
    public PacmanViewer(Map<Integer, BufferedImage> images){
        super(images);
    }

    @Override
    public void drawElement(TextGraphics graphics, Element element) {
        draw(graphics,element.getPosition(),images.get(((Pacman)element).getDirection()));
    }
}
