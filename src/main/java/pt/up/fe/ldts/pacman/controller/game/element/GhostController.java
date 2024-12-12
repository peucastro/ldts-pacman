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
    private static final Position GATE_EXIT = new Position(9, 9);
    private static final int GATE_MIN_X = 7;
    private static final int GATE_MAX_X = 11;
    private static final int GATE_MIN_Y = 9;
    private static final int GATE_MAX_Y = 12;
    private final Map<Class<?>, GhostMovementBehaviour> movementBehaviours;

    public GhostController(Arena arena) {
        super(arena);
        this.movementBehaviours = Map.of(
                Blinky.class, new BlinkyMovementBehaviour(),
                Pinky.class, new PinkyMovementBehaviour(),
                Inky.class, new InkyMovementBehaviour(),
                Clyde.class, new ClydeMovementBehaviour()
        );
    }

    private Position getNextPosition(Position position, Direction direction) {
        return switch (direction) {
            case UP -> position.getUp();
            case DOWN -> position.getDown();
            case LEFT -> position.getLeft();
            case RIGHT -> position.getRight();
        };
    }

    private void moveGhost(Ghost ghost, Direction newDirection) {
        Position newPosition = getNextPosition(ghost.getPosition(),newDirection);
        if (getModel().isEmpty(newPosition)) {
            ghost.setPosition(newPosition);
            ghost.setDirection(newDirection);
            if (newPosition.equals(getModel().getPacman().getPosition())) {
                getModel().getPacman().decreaseLife();
                getModel().getPacman().setPosition(new Position(9,16));
            }
        }
    }

    private boolean isInsideGate(Position position) {
        return position.getX() >= GATE_MIN_X && position.getX() <= GATE_MAX_X &&
                position.getY() >= GATE_MIN_Y && position.getY() <= GATE_MAX_Y;
    }

    private Direction getDirectionTowards(Ghost ghost, Position targetPosition) {
        Direction currentDirection = ghost.getDirection();
        Direction nextDirection = Direction.UP;
        double minimumDistance = Double.MAX_VALUE;
        double tempDistance;
        for(Direction direction : Direction.values()){
            Position testPosition = getNextPosition(ghost.getPosition(),direction);
            if(!direction.isOpposite(currentDirection) &&
               getModel().isEmpty(testPosition) &&
               (tempDistance = testPosition.squaredDistance(targetPosition)) < minimumDistance){
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
            if (isInsideGate(ghost.getPosition())) {
                nextDirection = getDirectionTowards(ghost, GATE_EXIT);
                moveGhost(ghost, nextDirection);
            } else {
                Position targetPosition = movementBehaviours.get(ghost.getClass()).getTargetPosition(ghost,getModel());
                nextDirection = getDirectionTowards(ghost,targetPosition);
                moveGhost(ghost, nextDirection);
            }
        }
    }
}
