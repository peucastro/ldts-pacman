package pt.up.fe.ldts.pacman.model.game.element;

import pt.up.fe.ldts.pacman.model.Element;
import pt.up.fe.ldts.pacman.model.Position;

public abstract class MovableElement extends Element {
    private Direction direction;
    private int counter;

    public MovableElement(Position pos) {
        super(pos);
        this.direction = Direction.LEFT;
        this.counter = 0;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getCounter() { return counter; }

    public void setCounter(int counter) { this.counter = counter; }

    public void incrementCounter() {this.counter++;}
}
