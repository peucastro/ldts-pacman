package pt.up.fe.ldts.pacman.view.game.element;

import pt.up.fe.ldts.pacman.view.game.Drawer;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class BlinkyDrawer extends Drawer {
    public BlinkyDrawer() throws IOException {
        this.image = ImageIO.read(new File("src/main/resources/PNGs/ghosts/blinkyleft.png"));
    }
}
