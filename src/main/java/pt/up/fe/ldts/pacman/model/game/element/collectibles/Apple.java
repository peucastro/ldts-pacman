package pt.up.fe.ldts.pacman.model.game.element.collectibles;

import pt.up.fe.ldts.pacman.model.game.Position;

public class Apple extends Collectible{
    public Apple(Position pos){
        super(pos);
        this.value = 1;
    }
}