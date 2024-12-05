package pt.up.fe.ldts.pacman;

import pt.up.fe.ldts.pacman.States.GameState;
import pt.up.fe.ldts.pacman.States.State;
import pt.up.fe.ldts.pacman.gui.LanternaGUI;
import pt.up.fe.ldts.pacman.model.game.Arena;
import pt.up.fe.ldts.pacman.model.game.ArenaLoader;
import pt.up.fe.ldts.pacman.model.game.Position;
import pt.up.fe.ldts.pacman.model.game.element.ghost.GhostState;
import pt.up.fe.ldts.pacman.viewer.game.ArenaViewer;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;


public class TempDrawFrame {
    public static void main(String[] args) throws IOException, URISyntaxException, FontFormatException {
        LanternaGUI gui = new LanternaGUI(280,280);

        Arena arena = new Arena(20, 20);
        ArenaLoader arenaLoader = new ArenaLoader(arena);
        arenaLoader.loadMap("src/main/resources/Maps/map.txt");

        State<Arena> state = new GameState(arena);


        int i = 0;
        while (i < 100) {
            state.step(new TempDrawFrame(),gui,i);
            if(i == 50){
                arena.getGhosts().forEach(ghost -> ghost.setState(GhostState.SCARED));
            }
            i++;
        }
        gui.close();
    }
}
