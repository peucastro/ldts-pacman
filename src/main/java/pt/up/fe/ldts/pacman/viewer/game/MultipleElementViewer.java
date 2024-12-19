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

public class MultipleElementViewer extends Viewer<Element> {
    private final Map<Character, List<BufferedImage>> images;
    private final MultipleElementStrategy strategy;

    public MultipleElementViewer(MultipleElementStrategy strategy, Map<Character, List<BufferedImage>> images) {
        this.images = images;
        this.strategy = strategy;
    }

    @Override
    public void drawElement(GUI gui, Element element, long frameCount) {
        BufferedImage image = strategy.getCurrentImage(element, images, frameCount);
        assert image != null;
        if (element instanceof MovableElement movableElement) {
            Position drawPos = movableElement.getRealPosition();
            gui.drawImage(drawPos, image);
        }
        else {
            Position drawPos = new Position(element.getPosition().getX() * 11, element.getPosition().getY() * 11);
            gui.drawImage(drawPos, image);
        }
    }
}
