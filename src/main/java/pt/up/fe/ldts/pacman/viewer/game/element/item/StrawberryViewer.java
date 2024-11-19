package pt.up.fe.ldts.pacman.viewer.game.element.item;

import pt.up.fe.ldts.pacman.viewer.game.Viewer;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class StrawberryViewer extends Viewer {
    public StrawberryViewer() throws IOException {
        this.image = ImageIO.read(new File("src/main/resources/PNGs/items/strawberry.png"));
    }
}
