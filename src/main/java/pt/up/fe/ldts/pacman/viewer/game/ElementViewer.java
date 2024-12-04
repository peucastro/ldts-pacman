package pt.up.fe.ldts.pacman.viewer.game;

import pt.up.fe.ldts.pacman.model.game.element.Element;
import pt.up.fe.ldts.pacman.viewer.Renderer;
import pt.up.fe.ldts.pacman.viewer.Viewer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class ElementViewer extends Viewer<Element> {
    private final BufferedImage image;

    public ElementViewer(Renderer renderer, String filePath) throws IOException, URISyntaxException {
        super(renderer);
        URL resource = getClass().getClassLoader().getResource(filePath);
        assert resource != null;
        File imageFile = new File(resource.toURI());
        this.image = ImageIO.read(imageFile);
    }

    @Override
    public void drawElement(Element element) {
        renderer.drawImage(element.getPosition(), image);
    }
}
