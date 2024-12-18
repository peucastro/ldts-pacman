package pt.up.fe.ldts.pacman.controller.menu;

import com.googlecode.lanterna.TextColor;
import pt.up.fe.ldts.pacman.Game;
import pt.up.fe.ldts.pacman.States.GameState;
import pt.up.fe.ldts.pacman.audio.AudioManager;
import pt.up.fe.ldts.pacman.gui.GUI;
import pt.up.fe.ldts.pacman.model.Position;
import pt.up.fe.ldts.pacman.model.game.Arena;
import pt.up.fe.ldts.pacman.model.game.ArenaLoader;
import pt.up.fe.ldts.pacman.model.menu.MapSelectionMenu;
import pt.up.fe.ldts.pacman.model.menu.element.TextBox;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

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

                int currentOption = 0;
                File mapFolder = new File("src/main/resources/Maps");
                for (final File fileEntry : Objects.requireNonNull(mapFolder.listFiles())) {
                    if (!fileEntry.isDirectory() && fileEntry.getName().endsWith(".txt")) {
                        if(currentOption == getModel().getSelectedOption()){
                            arenaLoader.loadMap("src/main/resources/Maps/" + fileEntry.getName());
                        }
                        ++currentOption;
                    }
                }

                game.setState(new GameState(arena, game.getAudioManager()));
            }
        }
    }
}
