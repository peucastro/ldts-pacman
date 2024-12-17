package pt.up.fe.ldts.pacman.controller.menu;

import pt.up.fe.ldts.pacman.Game;
import pt.up.fe.ldts.pacman.States.GameState;
import pt.up.fe.ldts.pacman.audio.AudioManager;
import pt.up.fe.ldts.pacman.gui.GUI;
import pt.up.fe.ldts.pacman.model.game.Arena;
import pt.up.fe.ldts.pacman.model.game.ArenaLoader;
import pt.up.fe.ldts.pacman.model.menu.MapSelectionMenu;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class MapSelectionMenuController extends MenuController<MapSelectionMenu> {
    public MapSelectionMenuController(MapSelectionMenu model, AudioManager audioManager) {
        super(model, audioManager);
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException, URISyntaxException, FontFormatException {
        super.step(game, action, time);
        if (action == GUI.ACTION.SELECT) {
            menuConfirmSelection.playOnce();
            Arena arena = new Arena(29, 16);
            ArenaLoader arenaLoader = new ArenaLoader(arena);
            if (getModel().getSelectedOption() == 0) {
                arenaLoader.loadMap("src/main/resources/Maps/map1.txt");
            }

            game.setState(new GameState(arena, game.getAudioManager()));
        }
    }
}
