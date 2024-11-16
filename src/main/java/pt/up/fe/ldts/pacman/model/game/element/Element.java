package pt.up.fe.ldts.pacman.model.game.element;

import pt.up.fe.ldts.pacman.model.game.Position;

import java.util.Objects;

public abstract class Element {
    private final Position position;
    // private int direction;

    protected Element(Position pos) {
        position = pos;
    }

    public void setPosition(Position other) {
        position.setPosition(other);
    }

    public Position getPosition() {
        return position;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Element element = (Element) obj;
        return this.position.equals(element.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position.getX(), position.getX(), getClass());
    }
}
