package pt.up.fe.ldts.pacman.model.game.element;

import pt.up.fe.ldts.pacman.model.Element;
import pt.up.fe.ldts.pacman.model.Position;

public abstract class MovableElement extends Element {
    private Direction direction;
    private int counter;
    private int speed;

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

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public void incrementCounter() {
        if (counter < 10)
            this.counter++;
        else {
            counter = 0;
            setPosition(getNextPosition());
        }
    }

    public int getCounterX() {
        if (this.direction == Direction.LEFT) return -counter;
        if (this.direction == Direction.RIGHT) return counter;
        return 0;
    }

    public int getCounterY() {
        if (this.direction == Direction.UP) return -counter;
        if (this.direction == Direction.DOWN) return counter;
        return 0;
    }

    public Position getNextPosition() {
        return switch (direction) {
            case UP -> getPosition().getUp();
            case DOWN -> getPosition().getDown();
            case LEFT -> getPosition().getLeft();
            case RIGHT -> getPosition().getRight();
        };
    }

    public void invertDirection() {
        if (counter != 0) {
            setPosition(getNextPosition());
            setDirection(getDirection().getOpposite());
            setCounter(11 - getCounter());
        } else setDirection(getDirection().getOpposite());
    }

    public Position getRealPosition() {
        return new Position(getPosition().getX() * 11 + getCounterX(), getPosition().getY() * 11 + getCounterY());
    }

    public boolean collidingWith(MovableElement other) {
        return getRealPosition().squaredDistance(other.getRealPosition()) < 121;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
