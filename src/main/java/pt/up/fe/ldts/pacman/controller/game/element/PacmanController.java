package pt.up.fe.ldts.pacman.controller.game.element;

import pt.up.fe.ldts.pacman.Game;
import pt.up.fe.ldts.pacman.controller.game.GameController;
import pt.up.fe.ldts.pacman.gui.GUI;
import pt.up.fe.ldts.pacman.model.Position;
import pt.up.fe.ldts.pacman.model.game.Arena;
import pt.up.fe.ldts.pacman.model.game.element.Direction;
import pt.up.fe.ldts.pacman.model.game.element.ghost.Ghost;
import pt.up.fe.ldts.pacman.model.game.element.ghost.GhostState;
import pt.up.fe.ldts.pacman.model.game.element.pacman.Pacman;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class PacmanController extends GameController {
    private List<Direction> desiredDirections; //one for each pacman

    public PacmanController(Arena arena) {
        super(arena);
        this.desiredDirections = Arrays.asList(null,null);
    }

    private void movePacman(Pacman pacman, Direction desiredDirection) {

        if (pacman.getCounter() > 0) {
            pacman.incrementCounter();
            return;
        }

        if(desiredDirection != null && //desired direction exists
           getModel().isEmpty(calculateNextPosition(pacman.getPosition(),desiredDirection)) && //the position where the desired direction is faced has to be empty
           !getModel().getGhostGate().getPosition().equals(calculateNextPosition(pacman.getPosition(),desiredDirection))) //cannot go inside the ghost gate
                pacman.setDirection(desiredDirection);

        Position nextPosition = pacman.getNextPosition();

        if (getModel().isEmpty(nextPosition)) {
            pacman.incrementCounter();
        }
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
    public void step(Game game, List<GUI.ACTION> actions, long time) {
        for(GUI.ACTION action : actions) {
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
        for(int i = 0; i < getModel().getPacmans().size(); ++i) {
            Pacman pacman = getModel().getPacmans().get(i);
            pacman.updateMouthState();
            if (time % pacman.getSpeed() != 1 && !pacman.isDying()) movePacman(pacman, desiredDirections.get(i));
        }
    }
}