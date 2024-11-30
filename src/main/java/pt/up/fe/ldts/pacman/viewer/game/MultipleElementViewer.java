package pt.up.fe.ldts.pacman.viewer.game;

import com.googlecode.lanterna.graphics.TextGraphics;
import pt.up.fe.ldts.pacman.model.game.element.Element;
import pt.up.fe.ldts.pacman.viewer.Renderer;
import pt.up.fe.ldts.pacman.viewer.Viewer;

import java.awt.image.BufferedImage;
import java.util.Map;

public abstract class MultipleElementViewer<T extends Element, E extends Enum<E>> extends Viewer {
    protected final Map<E, BufferedImage> images;

    public MultipleElementViewer(Renderer renderer, Map<E, BufferedImage> images) {
        super(renderer);
        this.images = images;
    }

    @Override
    public void drawElement(TextGraphics graphics, Element element) {
        @SuppressWarnings("unchecked")
        BufferedImage image = getCurrentImage((T) element);
        if (image != null) {
            renderer.drawImage(element.getPosition(), image);
        }
    }

    protected abstract BufferedImage getCurrentImage(T element);
}
