package pt.up.fe.ldts.pacman.controller.game.element.behaviours;

import pt.up.fe.ldts.pacman.model.Position;
import pt.up.fe.ldts.pacman.model.game.Arena;
import pt.up.fe.ldts.pacman.model.game.element.ghost.Ghost;


public abstract class GhostMovementBehaviour {
    public Position getTargetPosition(Ghost ghost, Arena arena){
        return switch (ghost.getState()){
            case ALIVE -> getAlivePosition(ghost,arena);
            case DEAD -> getDeadPosition(ghost,arena);
            case SCARED -> new Position((int)(Math.random()*20),(int)(Math.random()*20));
        };
    }

    protected abstract Position getAlivePosition(Ghost ghost, Arena arena);


    protected abstract Position getDeadPosition(Ghost ghost, Arena arena);
}
