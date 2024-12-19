package pt.up.fe.ldts.pacman.model.game.element.collectibles;

import pt.up.fe.ldts.pacman.model.Element;
import pt.up.fe.ldts.pacman.model.Position;

public abstract class Collectible extends Element {
    private final int value;

    public Collectible(Position pos, int value) {
        super(pos);
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
