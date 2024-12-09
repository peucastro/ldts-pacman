package pt.up.fe.ldts.pacman.model.game.element.collectibles;

import pt.up.fe.ldts.pacman.model.Position;

public class Coin extends Collectible {
    public Coin(Position pos) {
        super(pos);
        this.value = 1;
    }
}
