package pt.up.fe.ldts.pacman.model.game.element.item;

import pt.up.fe.ldts.pacman.model.game.Position;
import pt.up.fe.ldts.pacman.model.game.element.Element;
import pt.up.fe.ldts.pacman.model.game.element.StaticElement;

public class Coin extends Element implements StaticElement {
    public Coin(Position pos) {
        super(pos);
    }
}
