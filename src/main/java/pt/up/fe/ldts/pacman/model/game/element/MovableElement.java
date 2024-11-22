package pt.up.fe.ldts.pacman.model.game.element;

import pt.up.fe.ldts.pacman.model.game.Position;

public abstract class MovableElement extends Element{
    private int direction; //l for left, u for up, d for down, r for right
    public MovableElement(Position pos){
        super(pos);
        this.direction = 'l';
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}
