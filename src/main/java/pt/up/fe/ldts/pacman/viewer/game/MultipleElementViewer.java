package pt.up.fe.ldts.pacman.viewer.game;

import com.googlecode.lanterna.graphics.TextGraphics;
import pt.up.fe.ldts.pacman.model.game.element.Element;
import pt.up.fe.ldts.pacman.viewer.Renderer;
import pt.up.fe.ldts.pacman.viewer.Viewer;

import java.awt.image.BufferedImage;
import java.util.Map;

import static pt.up.fe.ldts.pacman.model.game.element.Direction.LEFT;

public class MultipleElementViewer<T extends Element> extends Viewer {
    private final Map<Enum<?>, BufferedImage> images;

    public MultipleElementViewer(Renderer renderer, Map<Enum<?>, BufferedImage> images) {
        super(renderer);
        this.images = images;
    }

    @Override
    public void drawElement(TextGraphics graphics, Element element) {
        renderer.drawImage(element.getPosition(), images.get(LEFT));
    }
}
