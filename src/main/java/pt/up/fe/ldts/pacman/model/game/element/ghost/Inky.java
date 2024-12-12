package pt.up.fe.ldts.pacman.model.game.element.ghost;

import pt.up.fe.ldts.pacman.model.Position;

public class Inky extends Ghost {
    public Inky(Position pos) {
        super(pos);
    }

    @Override
    public Class<? extends Ghost> getGhostClass() {
        return this.getClass();
    }
}