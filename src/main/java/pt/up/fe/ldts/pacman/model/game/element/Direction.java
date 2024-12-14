package pt.up.fe.ldts.pacman.model.game.element;

public enum Direction {
    UP,
    LEFT,
    DOWN,
    RIGHT;

    public boolean isOpposite(Direction other) {
        return (this == UP && other == DOWN) || (this == DOWN && other == UP) || (this == LEFT && other == RIGHT) || (this == RIGHT && other == LEFT);
    }

    public Direction getOpposite(){
        return switch (this){
            case UP -> DOWN;
            case DOWN -> UP;
            case LEFT -> RIGHT;
            case RIGHT -> LEFT;
        };
    }
}
