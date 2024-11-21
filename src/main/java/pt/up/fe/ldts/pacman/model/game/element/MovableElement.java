package pt.up.fe.ldts.pacman.model.game.element;

import pt.up.fe.ldts.pacman.model.game.Position;

public abstract class MovableElement extends Element{
    private int direction;
    public MovableElement(Position pos){super(pos);}
}
