package pt.up.fe.ldts.pacman.model.game.element.pacman;

import pt.up.fe.ldts.pacman.model.Position;
import pt.up.fe.ldts.pacman.model.game.element.MovableElement;

public class Pacman extends MovableElement {
    private int life;

    public Pacman(Position pos) {
        super(pos);
        this.life = 3;
        setSpeed(4);
    }

    public void decreaseLife() {
        this.life--;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }
}
