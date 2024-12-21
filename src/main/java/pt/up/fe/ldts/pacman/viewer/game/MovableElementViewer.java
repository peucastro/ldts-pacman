package pt.up.fe.ldts.pacman.viewer.game;

import pt.up.fe.ldts.pacman.gui.GUI;
import pt.up.fe.ldts.pacman.model.Element;
import pt.up.fe.ldts.pacman.model.Position;
import pt.up.fe.ldts.pacman.model.game.element.MovableElement;
import pt.up.fe.ldts.pacman.viewer.Viewer;
import pt.up.fe.ldts.pacman.viewer.game.strategies.MultipleElementStrategy;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;

public class MovableElementViewer extends Viewer<Element> {
    private final Map<Character, List<BufferedImage>> images;
    private final MultipleElementStrategy strategy;

    public MovableElementViewer(MultipleElementStrategy strategy, Map<Character, List<BufferedImage>> images) {
        this.images = images;
        this.strategy = strategy;
    }

    @Override
    public void drawElement(GUI gui, Element element, long frameCount) {
        BufferedImage image = strategy.getCurrentImage(element, images, frameCount);
        assert image != null;
        MovableElement movableElement = (MovableElement) element;
        Position drawPos = movableElement.getRealPosition();
        gui.drawImage(drawPos, image);
    }
}
