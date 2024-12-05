package pt.up.fe.ldts.pacman.viewer;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import pt.up.fe.ldts.pacman.model.game.Position;

import java.awt.image.BufferedImage;

public class Renderer {
    private final TextGraphics graphics;

    public Renderer(TextGraphics graphics) {
        this.graphics = graphics;
    }

    public void drawImage(Position position, BufferedImage image) {
        int posX = position.getX();
        int posY = position.getY();

        for (int y = 0; y < 14; y++) {
            for (int x = 0; x < 14; x++) {
                if (image.getRGB(x, y) == 0) continue;

                int RGB = image.getRGB(x,y);
                int red = RGB >> 16 & 0xFF;
                int green = RGB >> 8 & 0xFF;
                int blue = RGB & 0xFF;

                graphics.setBackgroundColor(new TextColor.RGB(red,green,blue));
                graphics.setCharacter(posX + x, posY + y, ' ');
            }
        }
    }
}
