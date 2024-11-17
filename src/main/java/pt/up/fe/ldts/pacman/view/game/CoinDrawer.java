package pt.up.fe.ldts.pacman.view.game;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class CoinDrawer extends Drawer{
    public CoinDrawer() throws IOException {
        this.image = ImageIO.read(new File("src/main/resources/PNGs/point.png"));
    }
}
