package pt.up.fe.ldts.pacman.controller.game;

import pt.up.fe.ldts.pacman.Game;
import pt.up.fe.ldts.pacman.States.MainMenuState;
import pt.up.fe.ldts.pacman.controller.game.element.CollectibleController;
import pt.up.fe.ldts.pacman.controller.game.element.GhostController;
import pt.up.fe.ldts.pacman.controller.game.element.PacmanController;
import pt.up.fe.ldts.pacman.gui.GUI;
import pt.up.fe.ldts.pacman.model.game.Arena;
import pt.up.fe.ldts.pacman.model.menu.MainMenu;

import java.io.IOException;
import java.net.URISyntaxException;

public class ArenaController extends GameController{
    private final PacmanController pacmanController;
    private final CollectibleController collectibleController;
    private final GhostController ghostController;

    public ArenaController(Arena arena) {
        super(arena);
        this.pacmanController = new PacmanController(arena);
        this.collectibleController = new CollectibleController(arena);
        this.ghostController = new GhostController(arena);
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException, URISyntaxException {
        if(action == GUI.ACTION.QUIT || getModel().getPacman().getLife() == 0) game.setState(new MainMenuState(new MainMenu()));
        else{
            //all the controllers here me thinks
            ghostController.step(game,action,time);
            pacmanController.step(game,action,time);
            collectibleController.step(game,action,time);
        }
    }
}
