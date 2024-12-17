package pt.up.fe.ldts.pacman.controller.game;

import pt.up.fe.ldts.pacman.Game;
import pt.up.fe.ldts.pacman.States.GameState;
import pt.up.fe.ldts.pacman.States.MainMenuState;
import pt.up.fe.ldts.pacman.audio.AudioManager;
import pt.up.fe.ldts.pacman.audio.AudioPlayer;
import pt.up.fe.ldts.pacman.gui.GUI;
import pt.up.fe.ldts.pacman.model.Position;
import pt.up.fe.ldts.pacman.model.game.Arena;
import pt.up.fe.ldts.pacman.model.menu.MainMenu;

import java.io.IOException;
import java.net.URISyntaxException;

public class DyingStateController extends GameController {
    private int stateTimeCounter;
    private final AudioPlayer deathAudio;

    public DyingStateController(Arena arena, AudioManager audioManager) {
        super(arena);
        this.stateTimeCounter = -1;
        audioManager.addAudio("deathAudio", new AudioPlayer("Audio/pacmanDeath.wav"));
        this.deathAudio = audioManager.getAudio("deathAudio");
        this.deathAudio.setVolume(1.0f);
        deathAudio.playOnce();
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException, URISyntaxException {
        if (stateTimeCounter == -1) stateTimeCounter = 110;

        if (stateTimeCounter == 0) {
            if (getModel().getPacman().getLife() <= 0) {
                game.getAudioManager().stopAllAudios();
                game.setState(new MainMenuState(new MainMenu(game.getResolution()), game.getAudioManager()));
            } else {
                getModel().getPacman().setPosition(getModel().getRespawnPosition());
                getModel().getGhosts().forEach(ghost -> {
                    ghost.setPosition(new Position(13, 7));
                    ghost.setInsideGate();
                });
                game.setState(new GameState(getModel(), game.getAudioManager()));
            }
        }
        stateTimeCounter--;
    }
}
