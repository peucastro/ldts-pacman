package pt.up.fe.ldts.pacman.view.game;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class DeadGhostDrawer extends Drawer{
    public DeadGhostDrawer() throws IOException {
        this.image = ImageIO.read(new File("src/main/resources/PNGs/ghosts/deadghost.png"));
    }
}
