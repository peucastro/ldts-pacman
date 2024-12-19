package pt.up.fe.ldts.pacman.controller.menu;

import pt.up.fe.ldts.pacman.Game;
import pt.up.fe.ldts.pacman.audio.AudioManager;
import pt.up.fe.ldts.pacman.gui.GUI;
import pt.up.fe.ldts.pacman.model.game.Arena;
import pt.up.fe.ldts.pacman.model.game.ArenaLoader;
import pt.up.fe.ldts.pacman.model.menu.MainMenu;
import pt.up.fe.ldts.pacman.model.menu.MapSelectionMenu;
import pt.up.fe.ldts.pacman.states.game.GameState;
import pt.up.fe.ldts.pacman.states.menu.MainMenuState;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class MapSelectionMenuController extends MenuController<MapSelectionMenu> {
    public MapSelectionMenuController(MapSelectionMenu model, AudioManager audioManager) {
        super(model, audioManager);
    }

    @Override
    public void step(Game game, List<GUI.ACTION> actions, long time) throws IOException, URISyntaxException, FontFormatException {
        super.step(game, actions, time);
        for (GUI.ACTION action : actions) {
            if (action == GUI.ACTION.SELECT) {
                menuConfirmSelection.playOnce();
                Arena arena = new Arena(29, 16);
                ArenaLoader arenaLoader = new ArenaLoader(arena);

                arenaLoader.loadMap("src/main/resources/Maps/" + model.getFolderstring() + "/" +
                        model.getOptions().get(model.getSelectedOption()).getText() + ".txt");

                game.setState(new GameState(arena, game.getAudioManager()));
            } else if (action == GUI.ACTION.QUIT) {
                game.setState(new MainMenuState(new MainMenu(game.getResolution(), game.getAudioManager().getMasterVolume()), game.getAudioManager()));
            }
        }
    }
}
