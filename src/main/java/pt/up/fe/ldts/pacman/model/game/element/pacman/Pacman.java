package pt.up.fe.ldts.pacman.model.game.element.pacman;

import pt.up.fe.ldts.pacman.model.game.Position;
import pt.up.fe.ldts.pacman.model.game.element.MovableElement;

public class Pacman extends MovableElement {
    private int life;

    public Pacman(Position pos) {
        super(pos);
        this.life = 3;
    }

    public void decreaseLife() {
        this.life--;
    }
}
