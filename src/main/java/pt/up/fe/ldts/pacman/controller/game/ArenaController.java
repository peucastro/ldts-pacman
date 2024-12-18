package pt.up.fe.ldts.pacman.controller.game;

import pt.up.fe.ldts.pacman.Game;
import pt.up.fe.ldts.pacman.model.game.element.collectibles.Collectible;
import pt.up.fe.ldts.pacman.model.game.element.collectibles.PowerUp;
import pt.up.fe.ldts.pacman.model.menu.WinMenu;
import pt.up.fe.ldts.pacman.states.menu.PauseMenuState;
import pt.up.fe.ldts.pacman.audio.AudioManager;
import pt.up.fe.ldts.pacman.controller.game.element.CollectibleController;
import pt.up.fe.ldts.pacman.controller.game.element.GhostController;
import pt.up.fe.ldts.pacman.controller.game.element.PacmanController;
import pt.up.fe.ldts.pacman.gui.GUI;
import pt.up.fe.ldts.pacman.model.game.Arena;
import pt.up.fe.ldts.pacman.model.menu.PauseMenu;
import pt.up.fe.ldts.pacman.states.menu.WinMenuState;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class ArenaController extends GameController {
    private final PacmanController pacmanController;
    private final CollectibleController collectibleController;
    private final GhostController ghostController;
    private long maxScore;

    public ArenaController(Arena arena, AudioManager audioManager) {
        super(arena);
        this.pacmanController = new PacmanController(arena);
        this.collectibleController = new CollectibleController(arena, audioManager);
        this.ghostController = new GhostController(arena, audioManager);
        GhostController.setScaredTimeLeft(0);
        this.maxScore = calculateMaxScore();
    }

    private long calculateMaxScore(){
        long score = 0;
        for(Collectible collectible : getModel ().getCollectibles()){
            score += collectible.getValue();
            //every ghost is eaten every time a power up is consumed
            if(collectible.getClass() == PowerUp.class) score += (long) (200*((1-Math.pow(2,getModel().getGhosts().size()))/-1));
        }
        return score;
    }

    @Override
    public void step(Game game, List<GUI.ACTION> actions, long time) throws IOException, URISyntaxException {
        for(GUI.ACTION action : actions) {
            if (action == GUI.ACTION.QUIT) {
                game.getAudioManager().stopAllAudios();
                game.setState(new PauseMenuState(new PauseMenu(game.getState(), game.getResolution(), game.getAudioManager().getMasterVolume()), game.getAudioManager()));
                return;
            }
        }
        if(getModel().getCollectibles().isEmpty()){
            game.getAudioManager().stopAllAudios();
            game.setState(new WinMenuState(new WinMenu(getModel(), getModel().getScore() == maxScore), game.getAudioManager()));
            return;
        }
        //all the controllers here me thinks
        pacmanController.step(game, actions, time);
        ghostController.step(game, actions, time);
        collectibleController.step(game, actions, time);
    }
}
