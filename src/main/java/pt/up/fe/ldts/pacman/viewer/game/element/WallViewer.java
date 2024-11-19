package pt.up.fe.ldts.pacman.viewer.game.element;

import pt.up.fe.ldts.pacman.viewer.Viewer;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class WallViewer extends Viewer {
    public WallViewer() throws IOException {
        this.image = ImageIO.read(new File("src/main/resources/PNGs/wall.png"));
    }
}
