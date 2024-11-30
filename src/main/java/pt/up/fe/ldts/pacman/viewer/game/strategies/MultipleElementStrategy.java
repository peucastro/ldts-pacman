package pt.up.fe.ldts.pacman.viewer.game.strategies;

import pt.up.fe.ldts.pacman.model.game.element.Element;

import java.awt.image.BufferedImage;
import java.util.Map;

public abstract class MultipleElementStrategy {
    public abstract BufferedImage getCurrentImage(Element element, Map<Character, BufferedImage> images);
}
