package pt.up.fe.ldts.pacman.model.game.element.ghost;

import pt.up.fe.ldts.pacman.model.game.Position;
import pt.up.fe.ldts.pacman.model.game.element.Element;
import pt.up.fe.ldts.pacman.model.game.element.MovableElement;

public class Ghost extends MovableElement {
    private int state; //a for alive, s for scared, d for dead
    public Ghost(Position pos) {
        super(pos);
        this.state = 'a';
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
