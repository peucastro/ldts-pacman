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
        //first use the module operator to limit the value to only 20 different values, then assign the first ten to the first image and the rest to the second image
        int index = (frameCount%20 < 10 ? 0 : 1);
        if (pacman.isDying())
            return images.get('X').getFirst();

        return switch (pacman.getDirection()) {
            case UP -> images.get('U').get(index);
            case DOWN -> images.get('D').get(index);
            case RIGHT -> images.get('R').get(index);
            case LEFT -> images.get('L').get(index);
        };

    }
}
