package pt.up.fe.ldts.pacman.model.game.element.collectibles;

import pt.up.fe.ldts.pacman.model.game.Position;

public class Key extends Collectible{
    public Key(Position pos){
        super(pos);
        this.value = 1;
    }
}