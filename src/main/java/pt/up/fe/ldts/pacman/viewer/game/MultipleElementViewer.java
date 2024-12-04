package pt.up.fe.ldts.pacman.viewer.game;

import pt.up.fe.ldts.pacman.model.game.element.Element;
import pt.up.fe.ldts.pacman.viewer.Renderer;
import pt.up.fe.ldts.pacman.viewer.Viewer;
import pt.up.fe.ldts.pacman.viewer.game.strategies.MultipleElementStrategy;

import java.awt.image.BufferedImage;
import java.util.Map;

public class MultipleElementViewer extends Viewer<Element> {
    private final Map<Character, BufferedImage> images;
    private MultipleElementStrategy strategy;

    public MultipleElementViewer(Renderer renderer, MultipleElementStrategy strategy, Map<Character, BufferedImage> images) {
        super(renderer);
        this.images = images;
        this.strategy = strategy;
    }

    @Override
    public void drawElement(Element element) {
        BufferedImage image = strategy.getCurrentImage(element,images);
        if (image != null) {
            renderer.drawImage(element.getPosition(), image);
        }
    }
}
