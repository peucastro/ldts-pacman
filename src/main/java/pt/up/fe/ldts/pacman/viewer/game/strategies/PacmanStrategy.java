package pt.up.fe.ldts.pacman.viewer.game.strategies;

import com.googlecode.lanterna.graphics.BasicTextImage;
import pt.up.fe.ldts.pacman.model.Element;
import pt.up.fe.ldts.pacman.model.game.element.pacman.Pacman;

import java.awt.image.BufferedImage;
import java.util.Map;


public class PacmanStrategy extends MultipleElementStrategy{
    @Override
    public BasicTextImage getCurrentImage(Element element, Map<Character,BasicTextImage> images) {
        Pacman pacman = (Pacman)element;
        return switch (pacman.getDirection()) {
            case UP -> images.get('U');
            case DOWN -> images.get('D');
            case RIGHT -> images.get('R');
            case LEFT -> images.get('L');
        };
    }
}
