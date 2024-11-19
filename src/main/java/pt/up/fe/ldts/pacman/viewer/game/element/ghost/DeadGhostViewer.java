package pt.up.fe.ldts.pacman.viewer.game.element.ghost;

import pt.up.fe.ldts.pacman.viewer.Viewer;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class DeadGhostViewer extends Viewer {
    public DeadGhostViewer() throws IOException {
        this.image = ImageIO.read(new File("src/main/resources/PNGs/ghosts/deadghost.png"));
    }
}
