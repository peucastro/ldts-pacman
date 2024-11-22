package pt.up.fe.ldts.pacman.viewer.game;

import com.googlecode.lanterna.graphics.TextGraphics;
import pt.up.fe.ldts.pacman.model.game.element.Element;
import pt.up.fe.ldts.pacman.viewer.Viewer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ElementViewer extends Viewer {
    private BufferedImage image;
    public ElementViewer(String filepath) throws IOException {
        this.image = ImageIO.read(new File(filepath));
    }

    @Override
    public void drawElement(TextGraphics graphics, Element element) {
        draw(graphics,element.getPosition(), image);
    }
}
