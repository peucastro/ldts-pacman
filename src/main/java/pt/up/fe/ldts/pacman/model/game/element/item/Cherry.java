package pt.up.fe.ldts.pacman.model.game.element.item;

import pt.up.fe.ldts.pacman.model.game.Position;

public class Cherry extends Collectible{
    public Cherry(Position pos){
        super(pos);
        this.value = 1;
    }
}
