package pt.up.fe.ldts.pacman.view.game;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Stack;

public class StrawberryDrawer extends Drawer {
    public StrawberryDrawer() throws IOException {
        this.image = ImageIO.read(new File("src/main/resources/PNGs/strawberry.png"));
    }
}
