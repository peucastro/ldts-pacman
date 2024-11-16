package pt.up.fe.ldts.pacman.view.game;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PinkyDrawer extends Drawer{
    public PinkyDrawer() throws IOException {
        this.image = ImageIO.read(new File("src/main/resources/PNGs/pinkyleft.png"));
    }
}
