package pt.up.fe.ldts.pacman.view.game;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class ClydeDrawer extends Drawer{
    public ClydeDrawer() throws IOException {
        this.image = ImageIO.read(new File("src/main/resources/PNGs/clydeleft.png"));
    }
}
