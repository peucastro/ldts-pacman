package pt.up.fe.ldts.pacman.view.game;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BlinkyDrawer extends Drawer{
    public BlinkyDrawer() throws IOException {
        this.image = ImageIO.read(new File("src/main/resources/PNGs/ghosts/blinkyleft.png"));
    }
}
