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


public class PacmanController extends GameController {
    private static final Position RESPAWN_POSITION = new Position(9, 16);
    private Direction desiredDirection;

    public PacmanController(Arena arena) {
        super(arena);
        this.desiredDirection = Direction.RIGHT;
    }

    private void movePacman() {
        Pacman pacman = getModel().getPacman();

        if (pacman.getCounter() > 0) {
            pacman.incrementCounter();
            return;
        }

        if(desiredDirection != null && //desired direction exists
           getModel().isEmpty(calculateNextPosition(pacman.getPosition(),desiredDirection)) && //the position where the desired direction is faced has to be empty
           getModel().getGhostGate().getPosition().equals(calculateNextPosition(pacman.getPosition(),desiredDirection))) //cannot go inside the ghost gate
                pacman.setDirection(desiredDirection);

        Position nextPosition = calculateNextPosition(pacman.getPosition(),pacman.getDirection());

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
    public void step(Game game, GUI.ACTION action, long time) {
        switch (action) {
            case UP -> desiredDirection= Direction.UP;
            case DOWN -> desiredDirection = Direction.DOWN;
            case LEFT -> desiredDirection = Direction.LEFT;
            case RIGHT -> desiredDirection = Direction.RIGHT;
            case NONE -> { }
        }

        movePacman();
        Ghost ghost;
        if((ghost = getModel().isGhost(getModel().getPacman().getPosition())) != null){ // handle collisions with ghosts
            switch (ghost.getState()){
                case ALIVE:
                    getModel().getPacman().decreaseLife();
                    getModel().getPacman().setPosition(RESPAWN_POSITION);
                    break;
                case SCARED:
                    ghost.setState(GhostState.DEAD);
                    GhostController.incrementGhostsEaten();
                    getModel().incrementScore((int)(200 * Math.pow(2,GhostController.getGhostsEaten())));
                    break;
                default: break;
            }
        }
    }
}