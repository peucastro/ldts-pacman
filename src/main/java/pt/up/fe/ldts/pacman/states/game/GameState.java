package pt.up.fe.ldts.pacman.states.game;

import pt.up.fe.ldts.pacman.audio.AudioManager;
import pt.up.fe.ldts.pacman.controller.Controller;
import pt.up.fe.ldts.pacman.controller.game.ArenaController;
import pt.up.fe.ldts.pacman.model.game.Arena;
import pt.up.fe.ldts.pacman.states.State;
import pt.up.fe.ldts.pacman.viewer.Viewer;
import pt.up.fe.ldts.pacman.viewer.game.ArenaViewer;

import java.io.IOException;
import java.net.URISyntaxException;

public class GameState extends State<Arena> {

    public GameState(Arena model, AudioManager audioManager) throws IOException, URISyntaxException {
        super(model, audioManager);
    }

    @Override
    public Viewer<Arena> createViewer() throws IOException {
        return new ArenaViewer();
    }

    @Override
    public Controller<Arena> createController(AudioManager audioManager) {
        return new ArenaController(getModel(), audioManager);
    }
}
