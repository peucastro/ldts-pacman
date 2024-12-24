package pt.up.fe.ldts.pacman.states.menu;

import pt.up.fe.ldts.pacman.audio.AudioManager;
import pt.up.fe.ldts.pacman.controller.Controller;
import pt.up.fe.ldts.pacman.controller.menu.AlertMenuController;
import pt.up.fe.ldts.pacman.model.menu.AlertMenu;
import pt.up.fe.ldts.pacman.states.State;
import pt.up.fe.ldts.pacman.viewer.Viewer;
import pt.up.fe.ldts.pacman.viewer.menu.AlertMenuViewer;

import java.io.IOException;
import java.net.URISyntaxException;

public class AlertMenuState extends State<AlertMenu> {
    public AlertMenuState(AlertMenu model, AudioManager audioManager) throws IOException, URISyntaxException {
        super(model, audioManager);
    }

    @Override
    public Viewer<AlertMenu> createViewer() throws IOException {
        return new AlertMenuViewer(getModel().getAlertFilePath());
    }

    @Override
    public Controller<AlertMenu> createController(AudioManager audioManager) {
        return new AlertMenuController(getModel(), audioManager);
    }
}
