package pt.up.fe.ldts.pacman.controller.game.element.behaviours;

import pt.up.fe.ldts.pacman.model.Position;
import pt.up.fe.ldts.pacman.model.game.Arena;
import pt.up.fe.ldts.pacman.model.game.element.ghost.Ghost;

public class ClydeMovementBehaviour extends GhostMovementBehaviour{
    @Override
    protected Position getAlivePosition(Ghost ghost, Arena arena) {
        if(ghost.getPosition().squaredDistance(arena.getPacman().getPosition()) >= 64){//8 or more tiles away
            return arena.getPacman().getPosition();
        }
        else{
            return new Position(0, arena.getHeight());
        }
    }

    @Override
    protected Position getScaredPosition(Ghost ghost, Arena arena) {
        return null;
    }

    @Override
    protected Position getDeadPosition(Ghost ghost, Arena arena) {
        return null;
    }
}
