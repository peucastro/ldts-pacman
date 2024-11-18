package pt.up.fe.ldts.pacman.model.game.element.pacman;

import pt.up.fe.ldts.pacman.model.game.Position;
import pt.up.fe.ldts.pacman.model.game.element.Element;
import pt.up.fe.ldts.pacman.model.game.element.MovableElement;

public class Pacman extends Element implements MovableElement {
    public Pacman(Position pos) {
        super(pos);
    }
}
