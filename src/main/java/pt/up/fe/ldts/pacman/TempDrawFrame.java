package pt.up.fe.ldts.pacman;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import pt.up.fe.ldts.pacman.view.game.*;

public class TempDrawFrame {
    public static void main(String args[]){
        try {
            Display display = new Display(new TerminalSize(40, 20));
            TextGraphics graphics = display.getScreen().newTextGraphics();
            for(int x = 0; x < 10; x++){
                for(int y = 0; y < 10; y++){
                    graphics.setBackgroundColor(TextColor.Factory.fromString("#FFFFFF"));
                    graphics.putString(new TerminalPosition(10 + x, 5 + y), " ");
                }
            }
            display.getScreen().refresh();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
