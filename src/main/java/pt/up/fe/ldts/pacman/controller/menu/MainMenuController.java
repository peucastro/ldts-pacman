package pt.up.fe.ldts.pacman.controller.menu;

import pt.up.fe.ldts.pacman.Game;
import pt.up.fe.ldts.pacman.States.MapSelectionMenuState;
import pt.up.fe.ldts.pacman.audio.AudioManager;
import pt.up.fe.ldts.pacman.gui.GUI;
import pt.up.fe.ldts.pacman.model.menu.MainMenu;
import pt.up.fe.ldts.pacman.model.menu.MapSelectionMenu;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class MainMenuController extends MenuController<MainMenu> {
    public MainMenuController(MainMenu model, AudioManager audioManager) {
        super(model, audioManager);
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws URISyntaxException, IOException, FontFormatException {
        super.step(game, action, time);
        if (action == GUI.ACTION.SELECT) {
            menuConfirmSelection.playOnce();
            if (getModel().StartSelected()) {
                MapSelectionMenu mapSelectionMenu = new MapSelectionMenu(); // Create a new map selection menu model
                game.setState(new MapSelectionMenuState(mapSelectionMenu, game.getAudioManager())); // Switch to map selection menu
            } else if (getModel().ExitSelected()) {
                game.setState(null);
            } else if (getModel().ResolutionSelected()) {
                GUI.SCREEN_RESOLUTION newResolution = super.handleResolutionChange(game);
                getModel().setResolution(newResolution);
            } else if (getModel().MasterVolumeSelected()) {
                float newVolume = super.handleVolumeChange(game);
                getModel().setMasterVolume(newVolume);
            }
        }
    }
}