package pt.up.fe.ldts.pacman.model.game;

import java.util.Objects;

public class Position {
    int x;
    int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setPosition(Position other) {
        this.x = other.x;
        this.y = other.y;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        Position p = (Position) obj;
        return x == p.getX() && y == p.getY();
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
