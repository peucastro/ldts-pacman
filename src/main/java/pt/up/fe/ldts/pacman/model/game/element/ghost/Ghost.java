package pt.up.fe.ldts.pacman.model.game.element.ghost;

import pt.up.fe.ldts.pacman.model.Position;
import pt.up.fe.ldts.pacman.model.game.element.MovableElement;


public abstract class Ghost extends MovableElement {
    private GhostState state;

    public Ghost(Position pos) {
        super(pos);
        state = GhostState.ALIVE;

    }

    public GhostState getState() {
        return state;
    }


    public boolean isDead() {
        return state == GhostState.DEAD;
    }

    public boolean isScared() {
        return state == GhostState.SCARED;
    }

    public void setState(GhostState ghostState) {
        this.state = ghostState;
    }
}
