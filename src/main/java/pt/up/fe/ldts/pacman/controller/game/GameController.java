package pt.up.fe.ldts.pacman.controller.game;

import pt.up.fe.ldts.pacman.controller.Controller;
import pt.up.fe.ldts.pacman.model.game.Arena;

public abstract class GameController extends Controller<Arena> {
    public GameController(Arena arena) {
        super(arena);
    }
}
