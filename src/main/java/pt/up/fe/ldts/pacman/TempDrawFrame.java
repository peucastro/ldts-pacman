package pt.up.fe.ldts.pacman;

import pt.up.fe.ldts.pacman.States.MainMenuState;
import pt.up.fe.ldts.pacman.States.State;
import pt.up.fe.ldts.pacman.gui.GUI;
import pt.up.fe.ldts.pacman.gui.LanternaGUI;
import pt.up.fe.ldts.pacman.model.menu.MainMenu;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;


public class TempDrawFrame {
    public GUI gui;
    public State state;

    public static void main(String[] args) throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        /*
        LanternaGUI gui = new LanternaGUI(280,280);

        Arena arena = new Arena(20, 20);
        ArenaLoader arenaLoader = new ArenaLoader(arena);
        arenaLoader.loadMap("src/main/resources/Maps/map.txt");

        State<Arena> state = new GameState(arena);


        int i = 0;
        long start = System.currentTimeMillis();
        while (i < 300) {
            state.step(new TempDrawFrame(),gui,i);
            if(i == 50){
                arena.getGhosts().forEach(ghost -> ghost.setState(GhostState.SCARED));
            }
            i++;
        }
        System.out.println((System.currentTimeMillis() - start)/(double)1000);
        gui.close();
         */

        TempDrawFrame tdf = new TempDrawFrame();
        tdf.gui = new LanternaGUI(220, 220);


        tdf.state = new MainMenuState(new MainMenu());


        int i = 0;
        long frameTime = 1000 / 30;
        while (i < 500 && tdf.state != null) {
            long startTime = System.currentTimeMillis();
            tdf.state.step(tdf, tdf.gui, i);
            i++;
            long ellapsedTime = System.currentTimeMillis() - startTime;
            if (ellapsedTime < frameTime) Thread.sleep(frameTime - ellapsedTime);
        }
        tdf.gui.close();
    }
}
