package pt.up.fe.ldts.pacman.viewer.game.strategies;

import pt.up.fe.ldts.pacman.model.Element;
import pt.up.fe.ldts.pacman.model.game.element.pacman.Pacman;

import java.awt.image.BufferedImage;
import java.util.Map;


public class PacmanStrategy extends MultipleElementStrategy {
    @Override
    public BufferedImage getCurrentImage(Element element, Map<Character, BufferedImage> images) {
        Pacman pacman = (Pacman) element;
        return switch (pacman.getDirection()) {
            case UP -> pacman.isMouthOpen() ? images.get('U') : images.get('C');
            case DOWN -> pacman.isMouthOpen() ? images.get('D') : images.get('C');
            case RIGHT -> pacman.isMouthOpen() ? images.get('R') : images.get('C');
            case LEFT -> pacman.isMouthOpen() ? images.get('L') : images.get('C');
        };
    }
}
