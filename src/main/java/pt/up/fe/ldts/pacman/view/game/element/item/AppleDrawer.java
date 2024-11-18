package pt.up.fe.ldts.pacman.view.game.element.item;

import pt.up.fe.ldts.pacman.view.game.Drawer;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class AppleDrawer extends Drawer {
    public AppleDrawer() throws IOException {
        this.image = ImageIO.read(new File("src/main/resources/PNGs/items/apple.png"));
    }
}
