package pt.up.fe.ldts.pacman.controller.game.element.behaviours;

import pt.up.fe.ldts.pacman.model.Position;
import pt.up.fe.ldts.pacman.model.game.Arena;
import pt.up.fe.ldts.pacman.model.game.element.ghost.Ghost;

public class BlinkyMovementBehaviour extends GhostMovementBehaviour{
    @Override
    protected Position getAlivePosition(Ghost ghost, Arena arena) {
        return arena.getPacman().getPosition();
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
