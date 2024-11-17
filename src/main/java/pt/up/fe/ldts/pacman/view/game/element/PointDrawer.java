package pt.up.fe.ldts.pacman.view.game.element;

import pt.up.fe.ldts.pacman.view.game.Drawer;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class PointDrawer extends Drawer {
    public PointDrawer() throws IOException {
        this.image = ImageIO.read(new File("src/main/resources/PNGs/items/point.png"));
    }
}
