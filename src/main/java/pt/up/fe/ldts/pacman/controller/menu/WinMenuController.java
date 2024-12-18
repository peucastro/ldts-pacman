package pt.up.fe.ldts.pacman.controller.menu;

import pt.up.fe.ldts.pacman.Game;
import pt.up.fe.ldts.pacman.audio.AudioManager;
import pt.up.fe.ldts.pacman.gui.GUI;
import pt.up.fe.ldts.pacman.model.menu.MainMenu;
import pt.up.fe.ldts.pacman.model.menu.MapSelectionMenu;
import pt.up.fe.ldts.pacman.model.menu.WinMenu;
import pt.up.fe.ldts.pacman.states.menu.MainMenuState;
import pt.up.fe.ldts.pacman.states.menu.MapSelectionMenuState;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class WinMenuController extends MenuController<WinMenu> {
    public WinMenuController(WinMenu model, AudioManager audioManager) {
        super(model, audioManager);
    }

    @Override
    public void step(Game game, List<GUI.ACTION> actions, long time) throws URISyntaxException, IOException, FontFormatException {
        super.step(game, actions, time);
        for (GUI.ACTION action : actions) {
            if (action == GUI.ACTION.SELECT) {
                menuConfirmSelection.playOnce();
                if(getModel().PlayAgainSelected()){
                    game.getAudioManager().stopAllAudios();
                    String mode = (getModel().getArena().getPacmans().size() == 1 ? "singleplayer" : "multiplayer");
                    game.setState(new MapSelectionMenuState(new MapSelectionMenu(mode), game.getAudioManager()));
                }
                else if(getModel().ExitSelected()){
                    game.getAudioManager().stopAllAudios();
                    game.setState(new MainMenuState(new MainMenu(game.getResolution(),game.getAudioManager().getMasterVolume()),game.getAudioManager()));
                }
            }
        }
    }
}
