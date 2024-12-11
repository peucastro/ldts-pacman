package pt.up.fe.ldts.pacman.controller.game.element;

import pt.up.fe.ldts.pacman.Game;
import pt.up.fe.ldts.pacman.gui.GUI;
import pt.up.fe.ldts.pacman.model.Position;
import pt.up.fe.ldts.pacman.model.game.Arena;
import pt.up.fe.ldts.pacman.model.game.element.Direction;

import java.io.IOException;

public class PacmanController extends GameController {
    Direction currentDirection;

    public PacmanController(Arena arena) {
        super(arena);
        this.currentDirection = Direction.RIGHT;
    }

    public void movePacmanLeft() {
        movePacman(getModel().getPacman().getPosition().getLeft(), Direction.LEFT);
    }

    public void movePacmanRight() {
        movePacman(getModel().getPacman().getPosition().getRight(), Direction.RIGHT);
    }

    public void movePacmanUp() {
        movePacman(getModel().getPacman().getPosition().getUp(), Direction.UP);
    }

    public void movePacmanDown() {
        movePacman(getModel().getPacman().getPosition().getDown(), Direction.DOWN);
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

    @Override
    @SuppressWarnings("MissingCasesInEnumSwitch")
    public void step(Game game, GUI.ACTION action, long time) throws IOException {
        switch (action) {
            case UP -> currentDirection = Direction.UP;
            case DOWN -> currentDirection = Direction.DOWN;
            case LEFT -> currentDirection = Direction.LEFT;
            case RIGHT -> currentDirection = Direction.RIGHT;
            case NONE -> {
            }
        }
        moveInCurrentDirection();
    }
}
