package pt.up.fe.ldts.pacman.model.game.element.ghost;

import pt.up.fe.ldts.pacman.model.game.Position;
import pt.up.fe.ldts.pacman.model.game.element.MovableElement;
import pt.up.fe.ldts.pacman.model.game.element.Direction;


public class Ghost extends MovableElement {
    private GhostState state;

    public Ghost(Position pos) {
        super(pos);
        state = GhostState.RIGHT;

    }

    public GhostState getState() {
        return state;
    }

    public void setDirection(Direction direction) {
        switch (direction) {
            case LEFT -> state = GhostState.LEFT;
            case UP -> state = GhostState.UP;
            case DOWN -> state = GhostState.DOWN;
            case RIGHT -> state = GhostState.RIGHT;
        }
    }

    public boolean isDead() {
        return state == GhostState.DEAD;
    }

    public void setState(GhostState ghostState) {
        this.state = ghostState;
    }
}
