package pt.up.fe.ldts.pacman.model.game.element.ghost;

import pt.up.fe.ldts.pacman.model.Position;
import pt.up.fe.ldts.pacman.model.game.element.MovableElement;


public abstract class Ghost extends MovableElement {
    private GhostState state;
    private Boolean insideGate;

    public Ghost(Position pos) {
        super(pos);
        state = GhostState.ALIVE;
        this.insideGate = true;

    }

    public GhostState getState() {
        return state;
    }

    public void setState(GhostState ghostState) {
        this.state = ghostState;
    }

    public boolean isDead() {
        return state == GhostState.DEAD;
    }

    public boolean isScared() {
        return state == GhostState.SCARED;
    }

    public Boolean isInsideGate() {
        return insideGate;
    }

    public void setInsideGate(){
        this.insideGate = true;
    }

    public void setOutsideGate(){
        this.insideGate = false;
    }

    public void invertDirection(){
        setPosition(getNextPosition());
        setDirection(getDirection().getOpposite());
        setCounter(11 - getCounter());
    }
}
