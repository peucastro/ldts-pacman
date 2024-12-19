package pt.up.fe.ldts.pacman.controller.game.element;

import pt.up.fe.ldts.pacman.Game;
import pt.up.fe.ldts.pacman.controller.game.GameController;
import pt.up.fe.ldts.pacman.gui.GUI;
import pt.up.fe.ldts.pacman.model.Position;
import pt.up.fe.ldts.pacman.model.game.Arena;
import pt.up.fe.ldts.pacman.model.game.element.Direction;
import pt.up.fe.ldts.pacman.model.game.element.pacman.Pacman;

import java.util.Arrays;
import java.util.List;


public class PacmanController extends GameController {
    private final List<Direction> desiredDirections; //one for each pacman

    public PacmanController(Arena arena) {
        super(arena);
        this.desiredDirections = Arrays.asList(null, null);
    }

    private void movePacman(Pacman pacman, Direction desiredDirection) {
        if(desiredDirection != null && desiredDirection.isOpposite(pacman.getDirection())){
            //pacman can invert direction whenever
            pacman.invertDirection();
            desiredDirection = null;
        }

        if (pacman.getCounter() > 0) {
            pacman.incrementCounter();
            return;
        }

        if (desiredDirection != null) { //try to go in the desired direction
            Position nextDesiredPosition = calculateNextPosition(pacman.getPosition(), desiredDirection);

            boolean isPositionValid = getModel().isEmpty(nextDesiredPosition) &&
                    getModel().getPacmans().stream()
                            .filter(other -> !other.equals(pacman) && !other.isDying()) // Ignore the current Pacman
                            .noneMatch(other -> other.collidingWith(new Pacman(nextDesiredPosition)));

            if (isPositionValid &&
                    !getModel().getGhostGate().getPosition().equals(nextDesiredPosition)) {
                pacman.setDirection(desiredDirection);
                pacman.incrementCounter();
                return;
            }
        }

        //if the desired direction was invalid, try to go the in the current direction
        Position nextPosition = pacman.getNextPosition();

        // Ensure the next position is valid for movement
        if (getModel().isEmpty(nextPosition) &&
                getModel().getPacmans().stream()
                        .filter(other -> !other.equals(pacman)) // Ignore the current Pacman
                        .noneMatch(other -> other.collidingWith(new Pacman(nextPosition)))) {
            pacman.incrementCounter();
        }
    }


    private Position calculateNextPosition(Position position, Direction direction) {
        return switch (direction) {
            case UP -> position.getUp();
            case DOWN -> position.getDown();
            case LEFT -> position.getLeft();
            case RIGHT -> position.getRight();
        };
    }

    @Override
    @SuppressWarnings("MissingCasesInEnumSwitch")
    public void step(Game game, List<GUI.ACTION> actions, long time) {
        for (GUI.ACTION action : actions) {
            switch (action) {
                case UP -> desiredDirections.set(0, Direction.UP);
                case DOWN -> desiredDirections.set(0, Direction.DOWN);
                case LEFT -> desiredDirections.set(0, Direction.LEFT);
                case RIGHT -> desiredDirections.set(0, Direction.RIGHT);
                case W -> desiredDirections.set(1, Direction.UP);
                case A -> desiredDirections.set(1, Direction.LEFT);
                case S -> desiredDirections.set(1, Direction.DOWN);
                case D -> desiredDirections.set(1, Direction.RIGHT);

                case NONE -> {
                }
            }
        }
        for (int i = 0; i < getModel().getPacmans().size(); ++i) {
            Pacman pacman = getModel().getPacmans().get(i);
            if (time % pacman.getSpeed() != 1 && !pacman.isDying()) movePacman(pacman, desiredDirections.get(i));
        }
    }
}