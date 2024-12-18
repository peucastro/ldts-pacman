package pt.up.fe.ldts.pacman.viewer.game.strategies;

import pt.up.fe.ldts.pacman.model.Element;
import pt.up.fe.ldts.pacman.model.game.element.pacman.Pacman;

import java.awt.image.BufferedImage;
import java.util.Map;


public class PacmanStrategy extends MultipleElementStrategy {
    @Override
    public BufferedImage getCurrentImage(Element element, Map<Character, BufferedImage> images) {
        Pacman pacman = (Pacman) element;
        if (pacman.isDying()) {
            return images.get('X');
        } else if (pacman.isMouthOpen()) {
            return images.get('C');
        }
        return switch (pacman.getDirection()) {
            case UP -> images.get('U');
            case DOWN -> images.get('D');
            case RIGHT -> images.get('R');
            case LEFT -> images.get('L');
        };
    }
}
