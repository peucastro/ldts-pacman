package pt.up.fe.ldts.pacman.controller.game.element.behaviours;

import pt.up.fe.ldts.pacman.model.Position;
import pt.up.fe.ldts.pacman.model.game.Arena;
import pt.up.fe.ldts.pacman.model.game.element.ghost.Blinky;
import pt.up.fe.ldts.pacman.model.game.element.ghost.Ghost;

public class InkyMovementBehaviour extends GhostMovementBehaviour{
    @Override
    protected Position getAlivePosition(Ghost ghost, Arena arena) {
        if(arena.getCollectedCollectibles() < 25) return new Position(8,11);
        else if(ghost.isInsideGate()) return arena.getGhostGate().getPosition();

        Ghost blinky = null;
        for(Ghost g : arena.getGhosts()) if(g.getClass() == Blinky.class){blinky = g; break;}
        assert blinky != null;
        Position pacmanNextPos = arena.getPacman().getNextPosition();

        int newX = 2 * pacmanNextPos.getX() - blinky.getPosition().getX();
        int newY = 2 * pacmanNextPos.getY() - blinky.getPosition().getY();

        //bound the new coords
        if(newX < 0) newX = 0;
        if(newX > arena.getWidth()) newX = arena.getWidth();
        if(newY < 0) newY = 0;
        if(newY > arena.getHeight()) newY = arena.getHeight();

        return new Position(newX,newY);
    }

}