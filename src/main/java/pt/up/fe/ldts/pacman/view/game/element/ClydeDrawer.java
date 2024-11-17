package pt.up.fe.ldts.pacman.view.game.element;

import pt.up.fe.ldts.pacman.view.game.Drawer;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class ClydeDrawer extends Drawer {
    public ClydeDrawer() throws IOException {
        this.image = ImageIO.read(new File("src/main/resources/PNGs/ghosts/clydeleft.png"));
    }
}
