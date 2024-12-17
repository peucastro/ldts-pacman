package pt.up.fe.ldts.pacman.controller.menu;

import pt.up.fe.ldts.pacman.Game;
import pt.up.fe.ldts.pacman.audio.AudioManager;
import pt.up.fe.ldts.pacman.audio.AudioPlayer;
import pt.up.fe.ldts.pacman.controller.Controller;
import pt.up.fe.ldts.pacman.gui.GUI;
import pt.up.fe.ldts.pacman.model.menu.Menu;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public abstract class MenuController<T extends Menu> extends Controller<T> {
    protected final AudioPlayer menuSelect;
    protected final AudioPlayer menuConfirmSelection;

    public MenuController(T model, AudioManager audioManager) {
        super(model);

        if (!audioManager.audioExists("menuSelect"))
            audioManager.addAudio("menuSelect", new AudioPlayer("Audio/menuSelect.wav"));
        this.menuSelect = audioManager.getAudio("menuSelect");
        this.menuSelect.setVolume(0.25f);
        if (!audioManager.audioExists("menuConfirmSelection"))
            audioManager.addAudio("menuConfirmSelection", new AudioPlayer("Audio/menuConfirmSelection.wav"));
        this.menuConfirmSelection = audioManager.getAudio("menuConfirmSelection");
        this.menuConfirmSelection.setVolume(0.2f);
    }

    protected GUI.SCREEN_RESOLUTION handleResolutionChange(Game game) throws FontFormatException, URISyntaxException, IOException {
        GUI.SCREEN_RESOLUTION newResolution = switch (game.getResolution()) {
            case _360p -> GUI.SCREEN_RESOLUTION._540p;
            case _540p -> GUI.SCREEN_RESOLUTION._720p;
            case _720p -> GUI.SCREEN_RESOLUTION._900p;
            case _900p -> GUI.SCREEN_RESOLUTION._1080p;
            case _1080p -> GUI.SCREEN_RESOLUTION._1440p;
            case _1440p -> GUI.SCREEN_RESOLUTION._2160p;
            case _2160p -> GUI.SCREEN_RESOLUTION._360p;
        };
        game.setResolution(newResolution);
        return newResolution;
    }

    protected float handleVolumeChange(Game game) {
        game.getGui().clear();
        float newVolume = game.getAudioManager().getMasterVolume() == 1f ? 0.1f
                : Math.round((game.getAudioManager().getMasterVolume() + 0.1f) * 10) / 10f;

        game.getAudioManager().setMasterVolume(newVolume);
        return newVolume;
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws URISyntaxException, IOException, FontFormatException {
        if (action == GUI.ACTION.UP) {
            menuSelect.playOnce();
            getModel().selectPreviousOption();
        } else if (action == GUI.ACTION.DOWN) {
            menuSelect.playOnce();
            getModel().selectNextOption();
        }
    }
}
