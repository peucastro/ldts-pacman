package pt.up.fe.ldts.pacman.model.game.element;

import pt.up.fe.ldts.pacman.model.Element;
import pt.up.fe.ldts.pacman.model.Position;

public abstract class StaticElement extends Element {
    public StaticElement(Position pos) {
        super(pos);
    }
}
