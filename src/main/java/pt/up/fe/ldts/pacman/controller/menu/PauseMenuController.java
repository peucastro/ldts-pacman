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
    public void step(Game game, GUI.ACTION action, long time) throws IOException, URISyntaxException {
        super.step(game, action, time);
        if (action == GUI.ACTION.SELECT) {
            menuConfirmSelection.playOnce();
            if (getModel().ResumeSelected()) {
                game.setState(getModel().getPausedState());
            } else if (getModel().ExitSelected())
                game.setState(new MainMenuState(new MainMenu(game.getResolution(), game.getAudioManager().getMasterVolume()), game.getAudioManager()));
            else if (getModel().ResolutionSelected()) {
                GUI.SCREEN_RESOLUTION newResolution = switch (game.getResolution()) {
                    case _360p -> GUI.SCREEN_RESOLUTION._540p;
                    case _540p -> GUI.SCREEN_RESOLUTION._720p;
                    case _720p -> GUI.SCREEN_RESOLUTION._900p;
                    case _900p -> GUI.SCREEN_RESOLUTION._1080p;
                    case _1080p -> GUI.SCREEN_RESOLUTION._1440p;
                    case _1440p -> GUI.SCREEN_RESOLUTION._2160p;
                    case _2160p -> GUI.SCREEN_RESOLUTION._360p;
                };
                try {
                    game.setResolution(newResolution);
                    getModel().setResolution(newResolution);
                } catch (FontFormatException e) {
                    throw new RuntimeException(e);
                }
            } else if (getModel().MasterVolumeSelected()) {
                game.getGui().clear();
                float newVolume;
                if (game.getAudioManager().getMasterVolume() == 1f) newVolume = 0.1f;
                else newVolume = Math.round((game.getAudioManager().getMasterVolume() + 0.1f) * 10) / (float) 10;

                getModel().setMasterVolume(newVolume);
                game.getAudioManager().setMasterVolume(newVolume);
            }
        }
    }
}
