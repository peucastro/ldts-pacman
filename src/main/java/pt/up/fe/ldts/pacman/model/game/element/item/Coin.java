package pt.up.fe.ldts.pacman.model.game.element.item;

import pt.up.fe.ldts.pacman.model.game.Position;

public class Coin extends Collectible{
    public Coin(Position pos) {
        super(pos);
        this.value = 1;
    }
}
