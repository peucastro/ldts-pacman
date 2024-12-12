package pt.up.fe.ldts.pacman.controller.game.element.behaviours;

import pt.up.fe.ldts.pacman.model.Position;
import pt.up.fe.ldts.pacman.model.game.Arena;
import pt.up.fe.ldts.pacman.model.game.element.ghost.Ghost;
import pt.up.fe.ldts.pacman.model.game.element.pacman.Pacman;

public class PinkyMovementBehaviour extends GhostMovementBehaviour{
    @Override
    protected Position getAlivePosition(Ghost ghost, Arena arena) {
        int newX = arena.getPacman().getPosition().getX(), newY = arena.getPacman().getPosition().getY();
        switch(arena.getPacman().getDirection()){
            case UP -> newY = Math.max(0, newY - 2);
            case DOWN -> newY = Math.max(arena.getHeight(), newY + 2);
            case RIGHT -> newX = Math.max(arena.getWidth(), newX + 2);
            case LEFT -> newX = Math.max(0, newX - 2);
        };
        return new Position(newX,newY);
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
