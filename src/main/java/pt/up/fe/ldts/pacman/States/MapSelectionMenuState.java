package pt.up.fe.ldts.pacman.States;

import pt.up.fe.ldts.pacman.audio.AudioManager;
import pt.up.fe.ldts.pacman.controller.Controller;
import pt.up.fe.ldts.pacman.controller.menu.MapSelectionMenuController;
import pt.up.fe.ldts.pacman.model.menu.MapSelectionMenu;
import pt.up.fe.ldts.pacman.viewer.Viewer;
import pt.up.fe.ldts.pacman.viewer.menu.MapSelectionMenuViewer;

import java.io.IOException;
import java.net.URISyntaxException;

public class MapSelectionMenuState extends State<MapSelectionMenu> {
    public MapSelectionMenuState(MapSelectionMenu model, AudioManager audioManager) throws IOException, URISyntaxException {
        super(model, audioManager);
    }

    @Override
    public Viewer<MapSelectionMenu> createViewer() throws IOException, URISyntaxException {
        return new MapSelectionMenuViewer();
    }

    @Override
    public Controller<MapSelectionMenu> createController(AudioManager audioManager) {
        return new MapSelectionMenuController(getModel(), audioManager);
    }
}
