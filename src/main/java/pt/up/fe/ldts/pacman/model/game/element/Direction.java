package pt.up.fe.ldts.pacman.model.game.element;

public enum Direction {
    UP,
    LEFT,
    DOWN,
    RIGHT;

    public boolean isOpposite(Direction other) {
        return (this == UP && other == DOWN) || (this == DOWN && other == UP) || (this == LEFT && other == RIGHT) || (this == RIGHT && other == LEFT);
    }

}
