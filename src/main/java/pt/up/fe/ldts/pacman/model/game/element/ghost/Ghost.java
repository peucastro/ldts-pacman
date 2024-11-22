package pt.up.fe.ldts.pacman.model.game.element.ghost;

import pt.up.fe.ldts.pacman.model.game.Position;
import pt.up.fe.ldts.pacman.model.game.element.Element;
import pt.up.fe.ldts.pacman.model.game.element.MovableElement;
import pt.up.fe.ldts.pacman.model.game.element.State;

public abstract class Ghost extends MovableElement {
    private State state;
    public Ghost(Position pos) {
        super(pos);
        this.state = State.ALIVE;
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }
}
