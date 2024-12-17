package pt.up.fe.ldts.pacman.controller.menu;

import pt.up.fe.ldts.pacman.Game;
import pt.up.fe.ldts.pacman.States.MapSelectionMenuState;
import pt.up.fe.ldts.pacman.audio.AudioManager;
import pt.up.fe.ldts.pacman.gui.GUI;
import pt.up.fe.ldts.pacman.model.Position;
import pt.up.fe.ldts.pacman.model.game.element.Direction;
import pt.up.fe.ldts.pacman.model.game.element.ghost.Blinky;
import pt.up.fe.ldts.pacman.model.game.element.ghost.Clyde;
import pt.up.fe.ldts.pacman.model.game.element.ghost.Inky;
import pt.up.fe.ldts.pacman.model.game.element.ghost.Pinky;
import pt.up.fe.ldts.pacman.model.menu.MainMenu;
import pt.up.fe.ldts.pacman.model.menu.MapSelectionMenu;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class MainMenuController extends MenuController<MainMenu> {
    public MainMenuController(MainMenu model, AudioManager audioManager) {
        super(model, audioManager);
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws URISyntaxException, IOException, FontFormatException {
        super.step(game, action, time);
        if (action == GUI.ACTION.SELECT) {
            menuConfirmSelection.playOnce();
            if (getModel().StartSelected()) {
                MapSelectionMenu mapSelectionMenu = new MapSelectionMenu(); // Create a new map selection menu model
                game.setState(new MapSelectionMenuState(mapSelectionMenu, game.getAudioManager())); // Switch to map selection menu
            } else if (getModel().ExitSelected()) {
                game.setState(null);
            } else if (getModel().ResolutionSelected()) {
                GUI.SCREEN_RESOLUTION newResolution = super.handleResolutionChange(game);
                getModel().setResolution(newResolution);
            } else if (getModel().MasterVolumeSelected()) {
                float newVolume = super.handleVolumeChange(game);
                getModel().setMasterVolume(newVolume);
            }
        }

        Blinky blinky = getModel().getBlinky(); blinky.incrementCounter();
        if(blinky.getPosition().equals(new Position(3,4))) blinky.setDirection(Direction.RIGHT);
        else if(blinky.getPosition().equals(new Position(7,4))) blinky.setDirection(Direction.LEFT);

        Inky inky = getModel().getInky(); inky.incrementCounter();
        if(inky.getPosition().equals(new Position(5,8))) inky.setDirection(Direction.DOWN);
        else if(inky.getPosition().equals(new Position(5,13))) inky.setDirection(Direction.UP);

        Pinky pinky = getModel().getPinky(); pinky.incrementCounter();
        if(pinky.getPosition().equals(new Position(19,13))) pinky.setDirection(Direction.RIGHT);
        else if(pinky.getPosition().equals(new Position(26,13))) pinky.setDirection(Direction.LEFT);

        Clyde clyde = getModel().getClyde(); clyde.incrementCounter();
        if(clyde.getPosition().equals(new Position(24,3))) clyde.setDirection(Direction.DOWN);
        else if(clyde.getPosition().equals(new Position(24,10))) clyde.setDirection(Direction.UP);
    }
}