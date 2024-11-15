package pt.up.fe.ldts.pacman.model.game.element.item;

import pt.up.fe.ldts.pacman.model.game.Position;
import pt.up.fe.ldts.pacman.model.game.element.Element;
import pt.up.fe.ldts.pacman.model.game.element.StaticElement;

public class PowerUp extends Element implements StaticElement {
    public PowerUp(Position pos) {
        super(pos);
    }
}
