package pt.up.fe.ldts.pacman.states.menu;

import pt.up.fe.ldts.pacman.audio.AudioManager;
import pt.up.fe.ldts.pacman.controller.Controller;
import pt.up.fe.ldts.pacman.controller.menu.MainMenuController;
import pt.up.fe.ldts.pacman.model.menu.MainMenu;
import pt.up.fe.ldts.pacman.states.State;
import pt.up.fe.ldts.pacman.viewer.Viewer;
import pt.up.fe.ldts.pacman.viewer.menu.MainMenuViewer;

import java.io.IOException;
import java.net.URISyntaxException;

public class MainMenuState extends State<MainMenu> {

    public MainMenuState(MainMenu model, AudioManager audioManager) throws IOException, URISyntaxException {
        super(model, audioManager);
    }

    @Override
    public Viewer<MainMenu> createViewer() throws IOException {
        return new MainMenuViewer();
    }

    @Override
    public Controller<MainMenu> createController(AudioManager audioManager) {
        return new MainMenuController(getModel(), audioManager);
    }
}
