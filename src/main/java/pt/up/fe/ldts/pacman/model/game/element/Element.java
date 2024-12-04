package pt.up.fe.ldts.pacman.model.game.element;

import pt.up.fe.ldts.pacman.model.game.Position;

import java.util.Objects;

public abstract class Element {
    private final Position position;

    protected Element(Position pos) {
        if (pos.getY() < 0 || pos.getX() < 0) {
            throw new IllegalArgumentException("Element position cannot have negatives values: " + pos);
        }
        position = pos;
    }

    public void setPosition(Position other) {
        if (other.getY() < 0 || other.getX() < 0) {
            throw new IllegalArgumentException("Element position cannot have negatives values: " + other);
        }
        position.setPosition(other);
    }

    public Position getPosition() {
        return position;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Element element)) return false;
        return this.position.equals(element.position) && this.getClass() == element.getClass();
    }

    @Override
    public int hashCode() {
        return Objects.hash(position.getX(), position.getX(), getClass());
    }
}
