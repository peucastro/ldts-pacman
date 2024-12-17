package pt.up.fe.ldts.pacman.controller.game;

import pt.up.fe.ldts.pacman.Game;
import pt.up.fe.ldts.pacman.States.DyingState;
import pt.up.fe.ldts.pacman.States.PauseMenuState;
import pt.up.fe.ldts.pacman.audio.AudioManager;
import pt.up.fe.ldts.pacman.controller.game.element.CollectibleController;
import pt.up.fe.ldts.pacman.controller.game.element.GhostController;
import pt.up.fe.ldts.pacman.controller.game.element.PacmanController;
import pt.up.fe.ldts.pacman.gui.GUI;
import pt.up.fe.ldts.pacman.model.game.Arena;
import pt.up.fe.ldts.pacman.model.menu.PauseMenu;

import java.io.IOException;
import java.net.URISyntaxException;

public class ArenaController extends GameController {
    private final PacmanController pacmanController;
    private final CollectibleController collectibleController;
    private final GhostController ghostController;

    public ArenaController(Arena arena, AudioManager audioManager) {
        super(arena);
        this.pacmanController = new PacmanController(arena);
        this.collectibleController = new CollectibleController(arena, audioManager);
        this.ghostController = new GhostController(arena, audioManager);
        GhostController.setScaredTimeLeft(0);
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException, URISyntaxException {
        if (action == GUI.ACTION.QUIT) {
            game.getAudioManager().stopAllAudios();
            game.setState(new PauseMenuState(new PauseMenu(game.getState(), game.getResolution(), game.getAudioManager().getMasterVolume()), game.getAudioManager()));
        } else {
            //all the controllers here me thinks
            pacmanController.step(game, action, time);
            ghostController.step(game, action, time);
            collectibleController.step(game, action, time);
        }
    }
}
