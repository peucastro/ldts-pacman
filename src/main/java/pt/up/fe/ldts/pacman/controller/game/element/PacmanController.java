package pt.up.fe.ldts.pacman.controller.game.element;

import pt.up.fe.ldts.pacman.Game;
import pt.up.fe.ldts.pacman.gui.GUI;
import pt.up.fe.ldts.pacman.model.Position;
import pt.up.fe.ldts.pacman.model.game.Arena;
import pt.up.fe.ldts.pacman.model.game.element.Direction;

import java.io.IOException;

public class PacmanController extends GameController {
    private Direction currentDirection;

    public PacmanController(Arena arena) {
        super(arena);
        this.currentDirection = Direction.RIGHT;
    }

    private void movePacman(Position position, Direction direction) {
        if (getModel().isEmpty(position)) {
            getModel().getPacman().setPosition(position);
            getModel().getPacman().setDirection(direction);
            if (getModel().isGhost(position)) getModel().getPacman().decreaseLife();
        }
    }

    public void moveInCurrentDirection() {
        if (currentDirection == null) return;
        Position nextPosition = switch (currentDirection) {
            case UP -> getModel().getPacman().getPosition().getUp();
            case DOWN -> getModel().getPacman().getPosition().getDown();
            case LEFT -> getModel().getPacman().getPosition().getLeft();
            case RIGHT -> getModel().getPacman().getPosition().getRight();
        };
        movePacman(nextPosition, currentDirection);
    }

    private Position getNextPosition(Direction direction) {
        Position currentPosition = getModel().getPacman().getPosition();
        return switch (direction) {
            case UP -> currentPosition.getUp();
            case DOWN -> currentPosition.getDown();
            case LEFT -> currentPosition.getLeft();
            case RIGHT -> currentPosition.getRight();
        };
    }

    private boolean isDirectionValid(Direction direction) {
        Position nextPosition = getNextPosition(direction);
        return getModel().isEmpty(nextPosition);
    }


    @Override
    @SuppressWarnings("MissingCasesInEnumSwitch")
    public void step(Game game, GUI.ACTION action, long time) throws IOException {
        Direction newDirection = null;
        switch (action) {
            case UP -> newDirection = Direction.UP;
            case DOWN -> newDirection = Direction.DOWN;
            case LEFT -> newDirection = Direction.LEFT;
            case RIGHT -> newDirection = Direction.RIGHT;
            case NONE -> {
            }
        }

        if (newDirection != null && isDirectionValid(newDirection)) {
            currentDirection = newDirection;
        }

        moveInCurrentDirection();
    }
}
