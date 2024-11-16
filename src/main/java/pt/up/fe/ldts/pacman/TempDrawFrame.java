package pt.up.fe.ldts.pacman;


import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import pt.up.fe.ldts.pacman.model.game.Position;
import pt.up.fe.ldts.pacman.view.game.*;


public class TempDrawFrame {
    public static void main(String args[]){
        try {
            Display display = new Display(new TerminalSize(280,280));
            View view = new View();
            TextGraphics graphics = display.getScreen().newTextGraphics();

            view.pacmanDrawer.draw(graphics,new Position(16,0));
            view.pinkyDrawer.draw(graphics,new Position(0,0));
            view.blinkyDrawer.draw(graphics,new Position(32,0));
            view.inkyDrawer.draw(graphics,new Position(48,0));
            view.clydeDrawer.draw(graphics,new Position(64,0));
            view.appleDrawer.draw(graphics,new Position(80,0));
            view.cherryDrawer.draw(graphics,new Position(0,16));
            view.deadGhostDrawer.draw(graphics,new Position(16,16));
            view.keyDrawer.draw(graphics,new Position(32,16));
            view.orangeDrawer.draw(graphics,new Position(48,16));
            view.pointDrawer.draw(graphics,new Position(64,16));
            view.strawberryDrawer.draw(graphics,new Position(80,16));

            display.getScreen().refresh();
            Thread.sleep(15000);
            display.getScreen().close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
