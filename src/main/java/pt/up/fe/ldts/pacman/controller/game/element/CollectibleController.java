package pt.up.fe.ldts.pacman.controller.game.element;

import pt.up.fe.ldts.pacman.Game;
import pt.up.fe.ldts.pacman.controller.game.GameController;
import pt.up.fe.ldts.pacman.gui.GUI;
import pt.up.fe.ldts.pacman.model.Position;
import pt.up.fe.ldts.pacman.model.game.Arena;
import pt.up.fe.ldts.pacman.model.game.element.collectibles.PowerUp;
import pt.up.fe.ldts.pacman.model.game.element.ghost.GhostState;
import pt.up.fe.ldts.pacman.model.game.element.pacman.Pacman;


public class CollectibleController extends GameController {
    public CollectibleController(Arena arena) {
        super(arena);
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) {
        Pacman pacman = getModel().getPacman();
        getModel().getCollectibles().removeIf(collectible -> {
            if (pacman.getPosition().equals(collectible.getPosition())) {
                if(collectible.getClass() == PowerUp.class) getModel().getGhosts().forEach(ghost -> {
                 GhostController.setScaredTimeLeft(3000);
                 if(!ghost.isDead()) {
                     ghost.setState(GhostState.SCARED);
                     ghost.setSpeed(Arena.GHOST_SCARED_SPEED);
                     ghost.invertDirection();
                 }
                });
                pacman.setSpeed(Arena.PACMAN_BOOSTED_SPEED);
                getModel().addBlankPosition(new Position(collectible.getPosition()));
                getModel().incrementScore(collectible.getValue());
                getModel().incrementCollectedCollectibles();
                return true;
            }
            return false;
        });
    }
}
