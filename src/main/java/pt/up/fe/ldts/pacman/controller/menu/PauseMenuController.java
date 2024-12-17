package pt.up.fe.ldts.pacman.controller.menu;

import pt.up.fe.ldts.pacman.Game;
import pt.up.fe.ldts.pacman.States.MainMenuState;
import pt.up.fe.ldts.pacman.audio.AudioManager;
import pt.up.fe.ldts.pacman.gui.GUI;
import pt.up.fe.ldts.pacman.model.menu.MainMenu;
import pt.up.fe.ldts.pacman.model.menu.PauseMenu;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class PauseMenuController extends MenuController<PauseMenu> {

    public PauseMenuController(PauseMenu pauseMenu, AudioManager audioManager) {
        super(pauseMenu, audioManager);
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException, URISyntaxException, FontFormatException {
        super.step(game, action, time);
        if (action == GUI.ACTION.SELECT) {
            menuConfirmSelection.playOnce();
            if (getModel().ResumeSelected()) {
                game.setState(getModel().getPausedState());
            } else if (getModel().ExitSelected())
                game.setState(new MainMenuState(new MainMenu(game.getResolution(), game.getAudioManager().getMasterVolume()), game.getAudioManager()));
            else if (getModel().ResolutionSelected()) {
                GUI.SCREEN_RESOLUTION newResolution = super.handleResolutionChange(game);
                getModel().setResolution(newResolution);
            } else if (getModel().MasterVolumeSelected()) {
                float newVolume = super.handleVolumeChange(game);
                getModel().setMasterVolume(newVolume);
            }
        }
    }
}
