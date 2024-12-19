package pt.up.fe.ldts.pacman.controller.game;

import pt.up.fe.ldts.pacman.Game;
import pt.up.fe.ldts.pacman.audio.AudioManager;
import pt.up.fe.ldts.pacman.audio.AudioPlayer;
import pt.up.fe.ldts.pacman.gui.GUI;
import pt.up.fe.ldts.pacman.model.game.Arena;
import pt.up.fe.ldts.pacman.model.game.element.ghost.GhostState;
import pt.up.fe.ldts.pacman.model.game.element.pacman.Pacman;
import pt.up.fe.ldts.pacman.model.menu.AlertMenu;
import pt.up.fe.ldts.pacman.states.game.GameState;
import pt.up.fe.ldts.pacman.states.menu.AlertMenuState;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class DyingStateController extends GameController {
    private int stateTimeCounter;

    public DyingStateController(Arena arena, AudioManager audioManager) {
        super(arena);
        this.stateTimeCounter = 110;

        if (!audioManager.audioExists("deathAudio"))
            audioManager.addAudio("deathAudio", new AudioPlayer("Audio/pacmanDeath.wav"));
        AudioPlayer deathAudio = audioManager.getAudio("deathAudio");
        deathAudio.setVolume(1.0f);
        deathAudio.playOnce();
    }

    @Override
    public void step(Game game, List<GUI.ACTION> actions, long time) throws IOException, URISyntaxException {
        if (--stateTimeCounter == 0) {
            int alivePacmans = 0; //number of still alive pacmans
            for (Pacman pacman : getModel().getPacmans()) {
                if (pacman.getLife() > 0) {
                    pacman.setPosition(pacman.getRespawnPosition());
                    pacman.setCounter(0);
                    pacman.setDying(false);
                    ++alivePacmans;
                }
            }
            if (alivePacmans == 0) { //no pacman alive: game over
                game.getAudioManager().stopAllAudios();
                game.setState(new AlertMenuState(new AlertMenu(getModel(), "PNGs/gameover.png"), game.getAudioManager()));
            } else { //there is still at least one pacman with more than one life: keep playing
                getModel().getGhosts().forEach(ghost -> {
                    ghost.setState(GhostState.ALIVE);
                    ghost.setSpeed(Arena.GHOST_NORMAL_SPEED);
                    ghost.setPosition(ghost.getRespawnPosition());
                    ghost.setCounter(0);
                    ghost.setInsideGate();
                });
                game.setState(new GameState(getModel(), game.getAudioManager()));
            }
        }
    }
}
