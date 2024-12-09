package pt.up.fe.ldts.pacman.States;

import pt.up.fe.ldts.pacman.controller.Controller;
import pt.up.fe.ldts.pacman.controller.menu.MainMenuController;
import pt.up.fe.ldts.pacman.model.menu.MainMenu;
import pt.up.fe.ldts.pacman.model.menu.Menu;
import pt.up.fe.ldts.pacman.viewer.Viewer;
import pt.up.fe.ldts.pacman.viewer.menu.MainMenuViewer;

import java.io.IOException;
import java.net.URISyntaxException;

public class MainMenuState extends State<MainMenu>{

    public MainMenuState(MainMenu model) throws IOException, URISyntaxException {
        super(model);
    }

    @Override
    public Viewer<MainMenu> createViewer() throws IOException, URISyntaxException {
        return new MainMenuViewer();
    }

    @Override
    public Controller<MainMenu> createController() {
        return new MainMenuController(getModel());
    }
}
