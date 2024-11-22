package pt.up.fe.ldts.pacman.viewer;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class ElementViewer extends Viewer {
    public ElementViewer(String filepath) throws IOException {
        this.image = ImageIO.read(new File(filepath));
    }
}
