package pt.up.fe.ldts.pacman.model.game.element.ghost;

import pt.up.fe.ldts.pacman.model.game.Position;
import pt.up.fe.ldts.pacman.model.game.element.Element;
import pt.up.fe.ldts.pacman.model.game.element.MovableElement;

public class Ghost extends Element implements MovableElement {
    protected Ghost(Position pos) {
        super(pos);
    }
}
