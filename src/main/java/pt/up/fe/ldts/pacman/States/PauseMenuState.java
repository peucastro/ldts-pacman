package pt.up.fe.ldts.pacman.States;

import pt.up.fe.ldts.pacman.controller.Controller;
import pt.up.fe.ldts.pacman.controller.menu.PauseMenuController;
import pt.up.fe.ldts.pacman.model.menu.PauseMenu;
import pt.up.fe.ldts.pacman.viewer.Viewer;
import pt.up.fe.ldts.pacman.viewer.menu.PauseMenuViewer;

import java.io.IOException;
import java.net.URISyntaxException;

public class PauseMenuState extends State<PauseMenu> {
    public PauseMenuState(PauseMenu pauseMenu) throws IOException, URISyntaxException {
        super(pauseMenu);
    }

    @Override
    public Viewer<PauseMenu> createViewer() throws IOException {
        return new PauseMenuViewer();
    }

    @Override
    public Controller<PauseMenu> createController() {
        return new PauseMenuController(getModel());
    }
}
