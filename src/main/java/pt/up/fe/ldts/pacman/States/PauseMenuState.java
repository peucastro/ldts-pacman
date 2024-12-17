package pt.up.fe.ldts.pacman.States;

import pt.up.fe.ldts.pacman.audio.AudioManager;
import pt.up.fe.ldts.pacman.controller.Controller;
import pt.up.fe.ldts.pacman.controller.menu.PauseMenuController;
import pt.up.fe.ldts.pacman.model.menu.PauseMenu;
import pt.up.fe.ldts.pacman.viewer.Viewer;
import pt.up.fe.ldts.pacman.viewer.menu.PauseMenuViewer;

import java.io.IOException;
import java.net.URISyntaxException;

public class PauseMenuState extends State<PauseMenu> {
    public PauseMenuState(PauseMenu pauseMenu, AudioManager audioManager) throws IOException, URISyntaxException {
        super(pauseMenu, audioManager);
    }

    @Override
    public Viewer<PauseMenu> createViewer() throws IOException {
        return new PauseMenuViewer();
    }

    @Override
    public Controller<PauseMenu> createController(AudioManager audioManager) {
        return new PauseMenuController(getModel(), audioManager);
    }
}
