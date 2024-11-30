package pt.up.fe.ldts.pacman.viewer.game;

import pt.up.fe.ldts.pacman.model.game.element.Element;
import pt.up.fe.ldts.pacman.viewer.Renderer;
import pt.up.fe.ldts.pacman.viewer.Viewer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ElementViewer extends Viewer {
    private final BufferedImage image;

    public ElementViewer(Renderer renderer, String filePath) throws IOException {
        super(renderer);
        this.image = ImageIO.read(new File(filePath));
    }

    @Override
    public void drawElement(Element element) {
        renderer.drawImage(element.getPosition(), image);
    }
}
