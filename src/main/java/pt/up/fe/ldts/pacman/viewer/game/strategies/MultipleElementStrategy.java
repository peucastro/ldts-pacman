package pt.up.fe.ldts.pacman.viewer.game.strategies;

import pt.up.fe.ldts.pacman.model.Element;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;

public abstract class MultipleElementStrategy {
    public abstract BufferedImage getCurrentImage(Element element, Map<Character, List<BufferedImage>> images, long frameCount);
}
