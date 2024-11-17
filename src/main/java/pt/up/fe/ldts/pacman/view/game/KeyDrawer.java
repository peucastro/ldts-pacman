package pt.up.fe.ldts.pacman.view.game;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class KeyDrawer extends Drawer{
    public KeyDrawer() throws IOException {
        this.image = ImageIO.read(new File("src/main/resources/PNGs/key.png"));
    }
}
