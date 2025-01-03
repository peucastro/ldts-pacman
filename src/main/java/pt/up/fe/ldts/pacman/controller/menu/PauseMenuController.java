package pt.up.fe.ldts.pacman.controller.menu;

import pt.up.fe.ldts.pacman.Game;
import pt.up.fe.ldts.pacman.audio.AudioManager;
import pt.up.fe.ldts.pacman.gui.GUI;
import pt.up.fe.ldts.pacman.model.menu.MainMenu;
import pt.up.fe.ldts.pacman.model.menu.PauseMenu;
import pt.up.fe.ldts.pacman.states.menu.MainMenuState;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class PauseMenuController extends MenuController<PauseMenu> {

    public PauseMenuController(PauseMenu pauseMenu, AudioManager audioManager) {
        super(pauseMenu, audioManager);
    }

    @Override
    public void step(Game game, List<GUI.ACTION> actions, long time) throws IOException, URISyntaxException, FontFormatException {
        super.step(game, actions, time);
        for (GUI.ACTION action : actions) {
            if (action == GUI.ACTION.SELECT) {
                menuConfirmSelection.playOnce();
                if (getModel().ResumeSelected()) { //return to the game
                    game.setState(getModel().getPausedState());
                } else if (getModel().ExitSelected()) { //go to main menu
                    game.setState(new MainMenuState(new MainMenu(game.getResolution(), game.getAudioManager().getMasterVolume()), game.getAudioManager()));
                } else if (getModel().ResolutionSelected()) { //change the resolution
                    GUI.SCREEN_RESOLUTION newResolution = incrementResolution(game.getResolution());
                    game.setResolution(newResolution);
                    getModel().setResolution(newResolution);
                } else if (getModel().MasterVolumeSelected()) { //change the master volume
                    float newVolume = super.handleVolumeChange(game);
                    getModel().setMasterVolume(newVolume);
                }
            }
        }
    }
}
