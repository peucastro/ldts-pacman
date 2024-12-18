package pt.up.fe.ldts.pacman.states.game;

import pt.up.fe.ldts.pacman.audio.AudioManager;
import pt.up.fe.ldts.pacman.controller.Controller;
import pt.up.fe.ldts.pacman.controller.game.DyingStateController;
import pt.up.fe.ldts.pacman.model.game.Arena;
import pt.up.fe.ldts.pacman.states.State;
import pt.up.fe.ldts.pacman.viewer.Viewer;
import pt.up.fe.ldts.pacman.viewer.game.ArenaViewer;

import java.io.IOException;
import java.net.URISyntaxException;

public class DyingState extends State<Arena> {
    public DyingState(Arena model, AudioManager audioManager) throws IOException, URISyntaxException {
        super(model, audioManager);
    }

    @Override
    public Viewer<Arena> createViewer() throws IOException, URISyntaxException {
        return new ArenaViewer();
    }

    @Override
    public Controller<Arena> createController(AudioManager audioManager) {
        return new DyingStateController(getModel(), audioManager);
    }

}
