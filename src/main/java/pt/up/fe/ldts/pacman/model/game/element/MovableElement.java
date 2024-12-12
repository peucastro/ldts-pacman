package pt.up.fe.ldts.pacman.model.game.element;

import pt.up.fe.ldts.pacman.model.Element;
import pt.up.fe.ldts.pacman.model.Position;

public abstract class MovableElement extends Element {
    private Direction direction;

    public MovableElement(Position pos) {
        super(pos);
        this.direction = Direction.LEFT;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Position getNextPosition() {
        return switch (direction) {
            case UP -> getPosition().getUp();
            case DOWN -> getPosition().getDown();
            case LEFT -> getPosition().getLeft();
            case RIGHT -> getPosition().getRight();
        };
    }
}
