package pt.up.fe.ldts.pacman.model.game.element.collectibles;

import pt.up.fe.ldts.pacman.model.Position;

public class Cherry extends Collectible {
    public Cherry(Position pos) {
        super(pos);
        this.value = 1;
    }
}
