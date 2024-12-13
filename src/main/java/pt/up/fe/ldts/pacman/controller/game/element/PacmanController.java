package pt.up.fe.ldts.pacman.controller.game.element;

import pt.up.fe.ldts.pacman.Game;
import pt.up.fe.ldts.pacman.controller.game.GameController;
import pt.up.fe.ldts.pacman.gui.GUI;
import pt.up.fe.ldts.pacman.model.Position;
import pt.up.fe.ldts.pacman.model.game.Arena;
import pt.up.fe.ldts.pacman.model.game.element.Direction;
import pt.up.fe.ldts.pacman.model.game.element.pacman.Pacman;

public class PacmanController extends GameController {
    private static final Position RESPAWN_POSITION = new Position(9, 16);
    private Direction currentDirection;
    private Direction desiredDirection;

    public PacmanController(Arena arena) {
        super(arena);
        this.desiredDirection = Direction.RIGHT;
        this.currentDirection = Direction.RIGHT;
    }

    private void movePacman() {
        Pacman pacman = getModel().getPacman();

        if (pacman.getCounter() > 0) {
            pacman.incrementCounter();
            return;
        }

        if(currentDirection != null && //desired direction exists
           !currentDirection.isOpposite(pacman.getDirection()) && //desired direction cannot be opposite to the current direction
           getModel().isEmpty(calculateNextPosition(pacman.getPosition(),currentDirection))) //the position where the desired direction is faced has to be empty
                pacman.setDirection(currentDirection);

        Position nextPosition = calculateNextPosition(pacman.getPosition(),pacman.getDirection());

        if (getModel().isEmpty(nextPosition)) {
            pacman.incrementCounter();
        }

        currentDirection = null;
    }

    private Position calculateNextPosition(Position position,Direction direction) {
        return switch (direction) {
            case UP -> position.getUp();
            case DOWN -> position.getDown();
            case LEFT -> position.getLeft();
            case RIGHT -> position.getRight();
        };
    }

    @Override
    @SuppressWarnings("MissingCasesInEnumSwitch")
    public void step(Game game, GUI.ACTION action, long time) {
        switch (action) {
            case UP -> desiredDirection= Direction.UP;
            case DOWN -> desiredDirection = Direction.DOWN;
            case LEFT -> desiredDirection = Direction.LEFT;
            case RIGHT -> desiredDirection = Direction.RIGHT;
            case NONE -> { }
        }

        if (desiredDirection != null && getModel().isEmpty(calculateNextPosition(getModel().getPacman().getPosition(),desiredDirection))) {
            currentDirection = desiredDirection;
        }

        movePacman();

        if(getModel().isGhost(getModel().getPacman().getPosition())){
            getModel().getPacman().decreaseLife();
            getModel().getPacman().setPosition(RESPAWN_POSITION);
        }
    }
}