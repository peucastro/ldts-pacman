package pt.up.fe.ldts.pacman.controller.game.element;

import pt.up.fe.ldts.pacman.Game;
import pt.up.fe.ldts.pacman.controller.game.GameController;
import pt.up.fe.ldts.pacman.gui.GUI;
import pt.up.fe.ldts.pacman.model.Position;
import pt.up.fe.ldts.pacman.model.game.Arena;
import pt.up.fe.ldts.pacman.model.game.element.Direction;
import pt.up.fe.ldts.pacman.model.game.element.ghost.*;

public class GhostController extends GameController {
    private static final Position GATE_EXIT = new Position(9, 9);
    private static final int GATE_MIN_X = 7;
    private static final int GATE_MAX_X = 11;
    private static final int GATE_MIN_Y = 9;
    private static final int GATE_MAX_Y = 12;

    public GhostController(Arena arena) {
        super(arena);
    }

    private void moveGhost(Ghost ghost, Position newPosition) {
        if (getModel().isEmpty(newPosition)) {
            ghost.setPosition(newPosition);
            if (newPosition.equals(getModel().getPacman().getPosition())) {
                getModel().getPacman().decreaseLife();
                getModel().getPacman().setPosition(new Position(9,16));
            }
        }
    }

    private Position getNextPosition(Ghost ghost, Direction direction) {
        Position currentPosition = ghost.getPosition();
        return switch (direction) {
            case UP -> currentPosition.getUp();
            case DOWN -> currentPosition.getDown();
            case LEFT -> currentPosition.getLeft();
            case RIGHT -> currentPosition.getRight();
        };
    }

    private Direction getRandomDirection() {
        Direction[] directions = Direction.values();
        return directions[(int) (Math.random() * directions.length)];
    }

    private boolean isInsideGate(Position position) {
        return position.getX() >= GATE_MIN_X && position.getX() <= GATE_MAX_X &&
                position.getY() >= GATE_MIN_Y && position.getY() <= GATE_MAX_Y;
    }

    public void moveGhosts() {
        for (Ghost ghost : getModel().getGhosts()) {
            if (isInsideGate(ghost.getPosition())) {
                Position nextPosition = getNextPosition(ghost, getDirectionTowards(ghost, GATE_EXIT));
                moveGhost(ghost, nextPosition);
            } else {
                Direction currentDirection = ghost.getDirection();
                Direction randomDirection = getRandomDirection();
                Position nextPosition;
                if (currentDirection != null && currentDirection.isOpposite(randomDirection)) {
                    do {
                        randomDirection = getRandomDirection();
                    } while (currentDirection.isOpposite(randomDirection) || !getModel().isEmpty(getNextPosition(ghost, randomDirection)));
                    ghost.setDirection(randomDirection);
                }
                nextPosition = getNextPosition(ghost, ghost.getDirection());
                moveGhost(ghost, nextPosition);
            }
        }
        }

    private Direction getDirectionTowards(Ghost ghost, Position targetPos) {
        Position ghostPos = ghost.getPosition();
        if (targetPos.getX() > ghostPos.getX()) return Direction.RIGHT;
        else if (targetPos.getX() < ghostPos.getX()) return Direction.LEFT;
        else if (targetPos.getY() > ghostPos.getY()) return Direction.DOWN;
        else return Direction.UP;
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) {
        moveGhosts();
    }
}
