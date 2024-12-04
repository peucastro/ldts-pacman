package pt.up.fe.ldts.pacman.controller.game.element;

import pt.up.fe.ldts.pacman.TempDrawFrame;
import pt.up.fe.ldts.pacman.gui.GUI;
import pt.up.fe.ldts.pacman.model.game.Arena;
import pt.up.fe.ldts.pacman.model.game.Position;

import java.io.IOException;

public class PacmanController extends GameController {

    public PacmanController(Arena arena) {
        super(arena);
    }

    public void movePacmanLeft() {
        movePacman(getModel().getPacman().getPosition().getLeft());
    }

    public void movePacmanRight() {
        movePacman(getModel().getPacman().getPosition().getRight());
    }

    public void movePacmanUp() {
        movePacman(getModel().getPacman().getPosition().getUp());
    }

    public void movePacmanDown() {
        movePacman(getModel().getPacman().getPosition().getDown());
    }

    private void movePacman(Position position) {
        if (getModel().isEmpty(position)) {
            getModel().getPacman().setPosition(position);
            if (getModel().isGhost(position)) getModel().getPacman().decreaseLife();
        }
    }

    @Override
    public void step(TempDrawFrame game, GUI.ACTION action, long time) throws IOException {
        if (action == GUI.ACTION.UP) movePacmanUp();
        if (action == GUI.ACTION.RIGHT) movePacmanRight();
        if (action == GUI.ACTION.DOWN) movePacmanDown();
        if (action == GUI.ACTION.LEFT) movePacmanLeft();
    }
}
