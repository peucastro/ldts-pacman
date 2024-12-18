package pt.up.fe.ldts.pacman.states.menu;

import pt.up.fe.ldts.pacman.audio.AudioManager;
import pt.up.fe.ldts.pacman.controller.Controller;
import pt.up.fe.ldts.pacman.controller.menu.GameOverMenuController;
import pt.up.fe.ldts.pacman.model.menu.GameOverMenu;
import pt.up.fe.ldts.pacman.states.State;
import pt.up.fe.ldts.pacman.viewer.Viewer;
import pt.up.fe.ldts.pacman.viewer.menu.GameOverMenuViewer;

import java.io.IOException;
import java.net.URISyntaxException;


public class GameOverMenuState extends State<GameOverMenu> {
    public GameOverMenuState(GameOverMenu model, AudioManager audioManager) throws IOException, URISyntaxException {
        super(model, audioManager);
    }

    @Override
    public Viewer<GameOverMenu> createViewer() throws IOException, URISyntaxException {
        return new GameOverMenuViewer();
    }

    @Override
    public Controller<GameOverMenu> createController(AudioManager audioManager) {
        return new GameOverMenuController(getModel(), audioManager);
    }
}
