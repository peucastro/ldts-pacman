package pt.up.fe.ldts.pacman.controller.menu;

import pt.up.fe.ldts.pacman.States.GameState;
import pt.up.fe.ldts.pacman.TempDrawFrame;
import pt.up.fe.ldts.pacman.gui.GUI;
import pt.up.fe.ldts.pacman.model.game.Arena;
import pt.up.fe.ldts.pacman.model.game.ArenaLoader;
import pt.up.fe.ldts.pacman.model.menu.MainMenu;

import java.io.IOException;
import java.net.URISyntaxException;

public class MainMenuController extends MenuController<MainMenu> {
    public MainMenuController(MainMenu model) {
        super(model);
    }

    @Override
    public void step(TempDrawFrame game, GUI.ACTION action, long time) throws IOException, URISyntaxException {
        if (action == GUI.ACTION.UP) getModel().selectPreviousOption();
        if (action == GUI.ACTION.DOWN) getModel().selectNextOption();
        if (action == GUI.ACTION.SELECT) {
            if (getModel().StartSelected()) {
                Arena arena = new Arena(20, 20);
                ArenaLoader arenaLoader = new ArenaLoader(arena);
                arenaLoader.loadMap("src/main/resources/Maps/map.txt");
                game.state = new GameState(arena);
            } else if (getModel().ExitSelected()) game.state = null;
        }
    }
}
