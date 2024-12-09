package pt.up.fe.ldts.pacman.model.game.element.collectibles;

import pt.up.fe.ldts.pacman.model.Position;
import pt.up.fe.ldts.pacman.model.game.element.StaticElement;

public abstract class Collectible extends StaticElement {
    protected int value;

    public Collectible(Position pos) {
        super(pos);
    }
}
