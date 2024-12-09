package pt.up.fe.ldts.pacman.States;

import pt.up.fe.ldts.pacman.controller.Controller;
import pt.up.fe.ldts.pacman.controller.game.element.PacmanController;
import pt.up.fe.ldts.pacman.model.game.Arena;
import pt.up.fe.ldts.pacman.viewer.Viewer;
import pt.up.fe.ldts.pacman.viewer.game.ArenaViewer;

import java.io.IOException;
import java.net.URISyntaxException;

public class GameState extends State<Arena> {

    public GameState(Arena model) throws IOException, URISyntaxException {
        super(model);
    }

    @Override
    public Viewer<Arena> createViewer() throws IOException, URISyntaxException {
        return new ArenaViewer();
    }

    @Override
    public Controller<Arena> createController() {
        return new PacmanController(getModel());
    }
}
