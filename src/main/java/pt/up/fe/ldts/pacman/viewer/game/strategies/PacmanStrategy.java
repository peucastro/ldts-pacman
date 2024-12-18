package pt.up.fe.ldts.pacman.viewer.game.strategies;

import pt.up.fe.ldts.pacman.model.Element;
import pt.up.fe.ldts.pacman.model.game.element.pacman.Pacman;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;


public class PacmanStrategy extends MultipleElementStrategy {
    @Override
    public BufferedImage getCurrentImage(Element element, Map<Character, List<BufferedImage>> images, long frameCount) {
        Pacman pacman = (Pacman) element;
        if (pacman.isDying()) {
            return images.get('X').getFirst();
        } else if (pacman.isMouthOpen()) {
            return images.get('C').getFirst();
        }
        return switch (pacman.getDirection()) {
            case UP -> images.get('U').getFirst();
            case DOWN -> images.get('D').getFirst();
            case RIGHT -> images.get('R').getFirst();
            case LEFT -> images.get('L').getFirst();
        };
    }
}
