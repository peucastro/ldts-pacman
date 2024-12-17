package pt.up.fe.ldts.pacman.controller.menu;

import pt.up.fe.ldts.pacman.audio.AudioManager;
import pt.up.fe.ldts.pacman.audio.AudioPlayer;
import pt.up.fe.ldts.pacman.controller.Controller;
import pt.up.fe.ldts.pacman.model.menu.Menu;

public abstract class MenuController<T extends Menu> extends Controller<T> {
    protected final AudioPlayer menuSelect;
    protected final AudioPlayer menuConfirmSelection;

    public MenuController(T model, AudioManager audioManager) {
        super(model);

        if(!audioManager.audioExists("menuSelect")) audioManager.addAudio("menuSelect", new AudioPlayer("Audio/menuSelect.wav"));
        this.menuSelect = audioManager.getAudio("menuSelect");
        if(!audioManager.audioExists("menuConfirmSelection")) audioManager.addAudio("menuConfirmSelection", new AudioPlayer("Audio/menuConfirmSelection.wav"));
        this.menuConfirmSelection = audioManager.getAudio("menuConfirmSelection");
    }
}
