package pt.up.fe.ldts.pacman.model.game.element.collectibles;

import pt.up.fe.ldts.pacman.model.Position;
import pt.up.fe.ldts.pacman.model.game.element.StaticElement;

public abstract class Collectible extends StaticElement {
    private final int value;

    public Collectible(Position pos, int value) {
        super(pos);
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
