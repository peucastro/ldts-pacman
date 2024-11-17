package pt.up.fe.ldts.pacman.view.game;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class InkyDrawer extends Drawer{
    public InkyDrawer() throws IOException {
        this.image = ImageIO.read(new File("src/main/resources/PNGs/inkyleft.png"));
    }
}
