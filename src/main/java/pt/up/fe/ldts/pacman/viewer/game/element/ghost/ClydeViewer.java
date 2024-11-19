package pt.up.fe.ldts.pacman.viewer.game.element.ghost;

import pt.up.fe.ldts.pacman.viewer.game.Viewer;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class ClydeViewer extends Viewer {
    public ClydeViewer() throws IOException {
        this.image = ImageIO.read(new File("src/main/resources/PNGs/ghosts/clydeleft.png"));
    }
}
