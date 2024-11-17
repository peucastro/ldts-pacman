package pt.up.fe.ldts.pacman.view.game;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import pt.up.fe.ldts.pacman.model.game.Position;

import java.awt.image.BufferedImage;

public abstract class Drawer {
    protected BufferedImage image;

    public void draw(TextGraphics graphics, Position position){
        for(int y = 0; y < 14;y++){
            for(int x = 0; x < 14;x++){
                if(image.getRGB(x,y) == 0) continue;
                graphics.setBackgroundColor(TextColor.Factory.fromString("#" + String.format("%x", image.getRGB(x,y)).substring(2)));
                graphics.setCharacter(new TerminalPosition(position.getX() + x, position.getY() + y), ' ');
            }
        }
    }
}
