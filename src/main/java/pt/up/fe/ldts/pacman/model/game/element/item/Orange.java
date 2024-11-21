package pt.up.fe.ldts.pacman.model.game.element.item;

import pt.up.fe.ldts.pacman.model.game.Position;

public class Orange extends Collectible{
    public Orange(Position pos){
        super(pos);
        this.value = 1;
    }
}
