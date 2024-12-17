package pt.up.fe.ldts.pacman.controller.game.element.behaviours;

import pt.up.fe.ldts.pacman.model.Position;
import pt.up.fe.ldts.pacman.model.game.Arena;
import pt.up.fe.ldts.pacman.model.game.element.ghost.Ghost;

public class BlinkyMovementBehaviour extends GhostMovementBehaviour{
    @Override
    protected Position getAlivePosition(Ghost ghost, Arena arena, boolean chaseMode) {
        if(ghost.isInsideGate()) return arena.getGhostGate().getPosition();
        if(!chaseMode) return new Position(arena.getWidth(),0);
        return arena.getPacman().getPosition();
    }
}
