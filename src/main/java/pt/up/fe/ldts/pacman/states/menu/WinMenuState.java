package pt.up.fe.ldts.pacman.states.menu;

import pt.up.fe.ldts.pacman.audio.AudioManager;
import pt.up.fe.ldts.pacman.controller.Controller;
import pt.up.fe.ldts.pacman.controller.menu.WinMenuController;
import pt.up.fe.ldts.pacman.model.menu.WinMenu;
import pt.up.fe.ldts.pacman.states.State;
import pt.up.fe.ldts.pacman.viewer.Viewer;
import pt.up.fe.ldts.pacman.viewer.menu.WinMenuViewer;

import java.io.IOException;
import java.net.URISyntaxException;

public class WinMenuState extends State<WinMenu> {
    public WinMenuState(WinMenu model, AudioManager audioManager) throws IOException, URISyntaxException {
        super(model, audioManager);
    }

    @Override
    public Viewer<WinMenu> createViewer() throws IOException, URISyntaxException {
        return new WinMenuViewer();
    }

    @Override
    public Controller<WinMenu> createController(AudioManager audioManager) {
        return new WinMenuController(getModel(),audioManager);
    }
}
