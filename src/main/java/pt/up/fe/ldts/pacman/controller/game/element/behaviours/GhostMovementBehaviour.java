package pt.up.fe.ldts.pacman.controller.game.element.behaviours;

import pt.up.fe.ldts.pacman.model.Position;
import pt.up.fe.ldts.pacman.model.game.Arena;
import pt.up.fe.ldts.pacman.model.game.element.ghost.Ghost;


public abstract class GhostMovementBehaviour {
    public Position getTargetPosition(Ghost ghost, Arena arena, boolean chaseMode){
        return switch (ghost.getState()){
            case ALIVE -> getAlivePosition(ghost,arena,chaseMode);
            case DEAD -> arena.getGhostGate().getPosition();
            case SCARED -> (ghost.isInsideGate() ? getAlivePosition(ghost,arena,chaseMode) : new Position((int)(Math.random()*20),(int)(Math.random()*20)));
        };
    }

    protected abstract Position getAlivePosition(Ghost ghost, Arena arena, boolean chaseMode);
}
