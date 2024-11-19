package pt.up.fe.ldts.pacman.viewer.game.element.item;

import pt.up.fe.ldts.pacman.viewer.Viewer;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class OrangeViewer extends Viewer {
    public OrangeViewer() throws IOException {
        this.image = ImageIO.read(new File("src/main/resources/PNGs/items/orange.png"));
    }
}
