package pt.up.fe.ldts.pacman.controller.game.element.behaviours;

import pt.up.fe.ldts.pacman.model.Position;
import pt.up.fe.ldts.pacman.model.game.Arena;
import pt.up.fe.ldts.pacman.model.game.element.ghost.Ghost;

public class BlinkyMovementBehaviour extends GhostMovementBehaviour{
    @Override
    protected Position getAlivePosition(Ghost ghost, Arena arena) {
        if(ghost.isInsideGate()) return arena.getGhostGate().getPosition();
        else return arena.getPacman().getPosition();
    }
}
