package pt.up.fe.ldts.pacman.controller.menu;

import pt.up.fe.ldts.pacman.Game;
import pt.up.fe.ldts.pacman.States.GameState;
import pt.up.fe.ldts.pacman.States.MainMenuState;
import pt.up.fe.ldts.pacman.gui.GUI;
import pt.up.fe.ldts.pacman.model.game.Arena;
import pt.up.fe.ldts.pacman.model.game.ArenaLoader;
import pt.up.fe.ldts.pacman.model.menu.MainMenu;
import pt.up.fe.ldts.pacman.model.menu.Menu;
import pt.up.fe.ldts.pacman.model.menu.PauseMenu;

import java.io.IOException;
import java.net.URISyntaxException;

public class PauseMenuController extends MenuController<PauseMenu> {

    public PauseMenuController(PauseMenu pauseMenu) {
        super(pauseMenu);
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException, URISyntaxException {
        if (action == GUI.ACTION.UP) getModel().selectPreviousOption();
        if (action == GUI.ACTION.DOWN) getModel().selectNextOption();
        if (action == GUI.ACTION.SELECT) {
            if (getModel().ResumeSelected()) {
                game.setState(getModel().getPausedState());
            } else if (getModel().ExitSelected()) game.setState(new MainMenuState(new MainMenu()));
        }
    }
}
