package pt.up.fe.ldts.pacman.controller.menu;

import pt.up.fe.ldts.pacman.Game;
import pt.up.fe.ldts.pacman.audio.AudioManager;
import pt.up.fe.ldts.pacman.audio.AudioPlayer;
import pt.up.fe.ldts.pacman.controller.Controller;
import pt.up.fe.ldts.pacman.gui.GUI;
import pt.up.fe.ldts.pacman.model.menu.Menu;

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

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws URISyntaxException, IOException {
        if (action == GUI.ACTION.UP) {
            menuSelect.playOnce();
            getModel().selectPreviousOption();
        } else if (action == GUI.ACTION.DOWN) {
            menuSelect.playOnce();
            getModel().selectNextOption();
        }
    }
}
