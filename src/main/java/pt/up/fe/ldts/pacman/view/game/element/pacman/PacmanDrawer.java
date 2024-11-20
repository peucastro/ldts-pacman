package pt.up.fe.ldts.pacman.view.game.element.pacman;

import pt.up.fe.ldts.pacman.view.game.Drawer;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class PacmanDrawer extends Drawer {
    public PacmanDrawer() throws IOException {
        this.image = ImageIO.read(new File("src/main/resources/PNGs/pacman/pacmanleft.png"));
    }
}