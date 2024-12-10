package pt.up.fe.ldts.pacman.controller.game.element;

import pt.up.fe.ldts.pacman.TempDrawFrame;
import pt.up.fe.ldts.pacman.controller.game.GameController;
import pt.up.fe.ldts.pacman.gui.GUI;
import pt.up.fe.ldts.pacman.model.game.Arena;
import pt.up.fe.ldts.pacman.model.game.element.collectibles.Collectible;
import pt.up.fe.ldts.pacman.model.game.element.pacman.Pacman;


public class CollectibleController extends GameController {
    public CollectibleController(Arena arena) {
        super(arena);
    }

    @Override
    public void step(TempDrawFrame game, GUI.ACTION action, long time) {
        Pacman pacman = getModel().getPacman();
        getModel().getCollectibles().removeIf(collectible -> {
            if(pacman.getPosition().equals(collectible.getPosition())){
                getModel().incrementScore(collectible.getValue());
                return true;
            }
            return false;
        });
    }
}
