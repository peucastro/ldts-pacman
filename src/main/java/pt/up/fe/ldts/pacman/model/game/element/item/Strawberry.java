package pt.up.fe.ldts.pacman.model.game.element.item;

import pt.up.fe.ldts.pacman.model.game.Position;

public class Strawberry extends Collectible{
    public Strawberry(Position pos){
        super(pos);
        this.value = 1;
    }
}
