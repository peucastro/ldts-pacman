package pt.up.fe.ldts.pacman.viewer;

import com.googlecode.lanterna.graphics.TextGraphics;
import pt.up.fe.ldts.pacman.model.game.element.Element;
import pt.up.fe.ldts.pacman.model.game.element.ghost.Ghost;

import java.awt.image.BufferedImage;
import java.util.Map;

public class GhostViewer extends MultipleElementViewer{
    public GhostViewer(Map<Integer, BufferedImage> images){
        super(images);
    }

    @Override
    public void drawElement(TextGraphics graphics, Element element) {
        if(((Ghost)element).getState() == 's') draw(graphics,element.getPosition(),images.get('s'));
        else draw(graphics,element.getPosition(),images.get(((Ghost)element).getDirection()));
    }
}
