package pt.up.fe.ldts.pacman.model.game.element.item;

import pt.up.fe.ldts.pacman.model.game.Position;
import pt.up.fe.ldts.pacman.model.game.element.StaticElement;

public abstract class Collectible extends StaticElement {
    protected int value;
    public Collectible(Position pos){ super(pos);}
}
