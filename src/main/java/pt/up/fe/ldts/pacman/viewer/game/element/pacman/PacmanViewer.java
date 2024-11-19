package pt.up.fe.ldts.pacman.viewer.game.element.pacman;

import pt.up.fe.ldts.pacman.viewer.game.Viewer;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class PacmanViewer extends Viewer {
    public PacmanViewer() throws IOException {
        this.image = ImageIO.read(new File("src/main/resources/PNGs/pacman/pacmanleft.png"));
    }
}
