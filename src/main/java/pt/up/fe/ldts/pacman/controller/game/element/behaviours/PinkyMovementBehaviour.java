package pt.up.fe.ldts.pacman.controller.game.element.behaviours;

import pt.up.fe.ldts.pacman.model.Position;
import pt.up.fe.ldts.pacman.model.game.Arena;
import pt.up.fe.ldts.pacman.model.game.element.ghost.Ghost;
import pt.up.fe.ldts.pacman.model.game.element.pacman.Pacman;

public class PinkyMovementBehaviour extends GhostMovementBehaviour{
    @Override
    protected Position getAlivePosition(Ghost ghost, Arena arena, boolean chaseMode) {
        if(ghost.isInsideGate()) return arena.getGhostGate().getPosition();
        if(!chaseMode) return new Position(0,0);

        int newX = arena.getPacman().getPosition().getX(), newY = arena.getPacman().getPosition().getY();
        switch(arena.getPacman().getDirection()){
            case UP -> newY = Math.max(0, newY - 3);
            case DOWN -> newY = Math.max(arena.getHeight(), newY + 3);
            case RIGHT -> newX = Math.max(arena.getWidth(), newX + 3);
            case LEFT -> newX = Math.max(0, newX - 3);
        };
        return new Position(newX,newY);
    }
}
