package pt.up.fe.ldts.pacman.controller.game.element;

import pt.up.fe.ldts.pacman.Game;
import pt.up.fe.ldts.pacman.controller.game.GameController;
import pt.up.fe.ldts.pacman.gui.GUI;
import pt.up.fe.ldts.pacman.model.Position;
import pt.up.fe.ldts.pacman.model.game.Arena;
import pt.up.fe.ldts.pacman.model.game.element.Direction;

public class PacmanController extends GameController {
    private static final Position RESPAWN_POSITION = new Position(9, 16);

    private Direction currentDirection;
    private Direction desiredDirection;
    private Position nextpos;

    public PacmanController(Arena arena) {
        super(arena);
        this.currentDirection = Direction.RIGHT;
        this.desiredDirection = Direction.RIGHT;
        this.nextpos = new Position(getModel().getPacman().getPosition().getX() + 1, getModel().getPacman().getPosition().getY());
    }

    private void movePacman() {

        if (getModel().getPacman().getCounter() < 11 && isDirectionValid(currentDirection)) {
            getModel().getPacman().setDirection(currentDirection);
            getModel().getPacman().incrementCounter();
            return;
        }
        if (getModel().isGhost(nextpos)) {
            getModel().getPacman().decreaseLife();
            getModel().getPacman().setPosition(RESPAWN_POSITION);
        } else if (getModel().isEmpty(nextpos)) {
            getModel().getPacman().setPosition(nextpos);
        }

        getModel().getPacman().setCounter(0);
        nextpos = calculateNextPosition(currentDirection);
    }

    private boolean isDirectionValid(Direction direction) {
        if (getModel().getPacman().getCounter() < 11 && direction != currentDirection) {
            return false;
        }
        Position nextPosition = calculateNextPosition(direction);
        return getModel().isEmpty(nextPosition);
    }

    public void moveInCurrentDirection() {
        movePacman();
    }


    private Position calculateNextPosition(Direction direction) {

        return switch (direction) {
            case UP -> nextpos.getUp();
            case DOWN -> nextpos.getDown();
            case LEFT -> nextpos.getLeft();
            case RIGHT -> nextpos.getRight();
        };
    }


    @Override
    @SuppressWarnings("MissingCasesInEnumSwitch")
    public void step(Game game, GUI.ACTION action, long time) {
        Direction newDirection = null;

        switch (action) {
            case UP -> newDirection = Direction.UP;
            case DOWN -> newDirection = Direction.DOWN;
            case LEFT -> newDirection = Direction.LEFT;
            case RIGHT -> newDirection = Direction.RIGHT;
            case NONE -> { }
        }

        if (newDirection != null) {
            desiredDirection = newDirection;
        }

        if (isDirectionValid(desiredDirection)) {
            currentDirection = desiredDirection;
        }

        moveInCurrentDirection();
    }
}