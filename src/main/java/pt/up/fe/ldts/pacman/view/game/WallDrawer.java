package pt.up.fe.ldts.pacman.view.game;

import pt.up.fe.ldts.pacman.model.game.element.Wall;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class WallDrawer extends Drawer{
    public WallDrawer() throws IOException {
        this.image = ImageIO.read(new File("src/main/resources/PNGs/wall.png"));
    }
}
