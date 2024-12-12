package pt.up.fe.ldts.pacman.controller.game.element.behaviours;

import pt.up.fe.ldts.pacman.model.Position;
import pt.up.fe.ldts.pacman.model.game.Arena;
import pt.up.fe.ldts.pacman.model.game.element.ghost.Ghost;

public class ClydeMovementBehaviour extends GhostMovementBehaviour{
    @Override
    protected Position getAlivePosition(Ghost ghost, Arena arena) {
        if(arena.getCollectedCollectibles() < 60) return new Position(10,11);
        else if(ghost.isInsideGate()) return arena.getGhostGate().getPosition();

        if(ghost.getPosition().squaredDistance(arena.getPacman().getPosition()) >= 36){//6 or more tiles away
            return arena.getPacman().getPosition();
        }
        else{
            return new Position(0, arena.getHeight());
        }
    }


    @Override
    protected Position getDeadPosition(Ghost ghost, Arena arena) {
        return null;
    }
}
