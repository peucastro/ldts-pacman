package pt.up.fe.ldts.pacman.model.game.element;

import pt.up.fe.ldts.pacman.model.game.Position;

abstract class Element {
    private Position position;
    //private int direction;

    Element(Position pos){
        position = pos;
    }

    public void setPosition(Position other){
        position.setPosition(other);
    }
    public Position getPosition(){
        return position;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        Position p = ((Element) obj).getPosition();
        return this.position.equals(p);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
