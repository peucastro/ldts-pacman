package pt.up.fe.ldts.pacman.controller.game.element;

import pt.up.fe.ldts.pacman.Game;
import pt.up.fe.ldts.pacman.controller.game.GameController;
import pt.up.fe.ldts.pacman.controller.game.element.behaviours.*;
import pt.up.fe.ldts.pacman.gui.GUI;
import pt.up.fe.ldts.pacman.model.Position;
import pt.up.fe.ldts.pacman.model.game.Arena;
import pt.up.fe.ldts.pacman.model.game.element.Direction;
import pt.up.fe.ldts.pacman.model.game.element.ghost.*;

import java.util.Map;

public class GhostController extends GameController {
    private final Map<Class<?>, GhostMovementBehaviour> movementBehaviours;
    private int ghostsEaten; //ghosts eaten in current scared state
    private int scaredTimeLeft;

    public GhostController(Arena arena) {
        super(arena);
        this.movementBehaviours = Map.of(
                Blinky.class, new BlinkyMovementBehaviour(),
                Pinky.class, new PinkyMovementBehaviour(),
                Inky.class, new InkyMovementBehaviour(),
                Clyde.class, new ClydeMovementBehaviour()
        );
        this.ghostsEaten = 0;
        this.scaredTimeLeft = 0;
    }

    private Position getNextPosition(Position position, Direction direction) {
        return switch (direction) {
            case UP -> position.getUp();
            case DOWN -> position.getDown();
            case LEFT -> position.getLeft();
            case RIGHT -> position.getRight();
        };
    }

    private void moveGhost(Ghost ghost, Direction newDirection) {//checks if the ghost can move in newDirection and moves it if it can
        Position newPosition = getNextPosition(ghost.getPosition(),newDirection);
        if (getModel().isEmpty(newPosition)) {
            ghost.setPosition(newPosition);
            ghost.setDirection(newDirection);
        }
        if(newPosition.equals(getModel().getGhostGate().getPosition())) ghost.setOutsideGate();
    }

    private Direction getDirectionTowards(Ghost ghost, Position targetPosition) {//choose new direction to follow (the one with the minimum linear distance from target)
        Direction currentDirection = ghost.getDirection();
        Direction nextDirection = Direction.UP;
        double minimumDistance = Double.MAX_VALUE;
        double tempDistance;
        for(Direction direction : Direction.values()){
            Position testPosition = getNextPosition(ghost.getPosition(),direction);
            if(!direction.isOpposite(currentDirection) && //can't move in opposite direction
               (tempDistance = testPosition.squaredDistance(targetPosition)) < minimumDistance && //can't move in a direction that is farther away from target
               getModel().isEmpty(testPosition) && //can't move in a direction where there is a wall
               (!testPosition.equals(getModel().getGhostGate().getPosition()) || ghost.isInsideGate() || ghost.isDead())) //can't move to the ghost gate, unless the ghost is inside
            {
                minimumDistance = tempDistance;
                nextDirection = direction;
            }
        }
        return nextDirection;
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) {
        for (Ghost ghost : getModel().getGhosts()) {
            Direction nextDirection;
            if(time%2 == 1){ //temporarily move slower
                Position targetPosition = movementBehaviours.get(ghost.getClass()).getTargetPosition(ghost, getModel());
                nextDirection = getDirectionTowards(ghost,targetPosition);
                moveGhost(ghost, nextDirection);
            }

            if (ghost.getPosition().equals(getModel().getPacman().getPosition())) { //collision with pacman
                switch (ghost.getState()){
                    case ALIVE:
                        getModel().getPacman().decreaseLife();
                        getModel().getPacman().setPosition(new Position(9,16));
                        break;
                    case SCARED:
                        ghost.setState(GhostState.DEAD);
                        getModel().incrementScore((int)(200 * Math.pow(2,ghostsEaten++)));
                        break;
                    default: break;
                }
            }

            if(ghost.isDead() && ghost.getPosition().equals(getModel().getGhostGate().getPosition())) { //dead ghost arrives at the ghost gate
                ghost.setState(GhostState.ALIVE);
                ghost.setInsideGate();
            }

            if(scaredTimeLeft > 0 && --scaredTimeLeft == 0) { //if scared time reaches 0 then all scared ghosts go back to normal
                getModel().getGhosts().forEach(ghost1 -> {
                    if (ghost1.isScared()) ghost1.setState(GhostState.ALIVE);
                });
                ghostsEaten = 0;
            }

            if(ghost.isScared() && scaredTimeLeft == 0){ //detects if scared time has started
                scaredTimeLeft = 200;
            }
        }
    }
}
