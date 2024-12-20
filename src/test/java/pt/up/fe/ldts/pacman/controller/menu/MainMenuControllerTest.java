package pt.up.fe.ldts.pacman.controller.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.up.fe.ldts.pacman.Game;
import pt.up.fe.ldts.pacman.audio.AudioManager;
import pt.up.fe.ldts.pacman.audio.AudioPlayer;
import pt.up.fe.ldts.pacman.model.game.element.Direction;
import pt.up.fe.ldts.pacman.model.menu.MapSelectionMenu;
import pt.up.fe.ldts.pacman.states.menu.MapSelectionMenuState;
import pt.up.fe.ldts.pacman.gui.GUI;
import pt.up.fe.ldts.pacman.model.Position;
import pt.up.fe.ldts.pacman.model.game.element.ghost.Blinky;
import pt.up.fe.ldts.pacman.model.game.element.ghost.Clyde;
import pt.up.fe.ldts.pacman.model.game.element.ghost.Inky;
import pt.up.fe.ldts.pacman.model.game.element.ghost.Pinky;
import pt.up.fe.ldts.pacman.model.menu.MainMenu;

import static org.mockito.Mockito.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.awt.FontFormatException;
import java.util.List;

class MainMenuControllerTest {
    private MainMenu mainMenu;
    private Game game;
    private MainMenuController controller;
    AudioManager audioManager;
    private Blinky blinky;
    private Inky inky;
    private Pinky pinky;
    private Clyde clyde;

    @BeforeEach
    void setUp() {
        mainMenu = mock(MainMenu.class);
        game = mock(Game.class);

        audioManager = mock(AudioManager.class);

        AudioPlayer menuSelect = mock(AudioPlayer.class);
        AudioPlayer menuConfirmSelection = mock(AudioPlayer.class);

        when(audioManager.getAudio("menuSelect")).thenReturn(menuSelect);
        when(audioManager.getAudio("menuConfirmSelection")).thenReturn(menuConfirmSelection);

        when(menuSelect.getVolume()).thenReturn(0.25f);
        when(menuConfirmSelection.getVolume()).thenReturn(0.2f);

        when(game.getAudioManager()).thenReturn(audioManager);

        controller = new MainMenuController(mainMenu, audioManager);

        blinky = mock(Blinky.class);
        inky = mock(Inky.class);
        pinky = mock(Pinky.class);
        clyde = mock(Clyde.class);

        Position blinkyPosition = new Position(3, 4);
        Position inkyPosition = new Position(5, 8);
        Position pinkyPosition = new Position(19, 13);
        Position clydePosition = new Position(24, 3);

        when(blinky.getPosition()).thenReturn(blinkyPosition);
        when(inky.getPosition()).thenReturn(inkyPosition);
        when(pinky.getPosition()).thenReturn(pinkyPosition);
        when(clyde.getPosition()).thenReturn(clydePosition);

        when(mainMenu.getBlinky()).thenReturn(blinky);
        when(mainMenu.getInky()).thenReturn(inky);
        when(mainMenu.getPinky()).thenReturn(pinky);
        when(mainMenu.getClyde()).thenReturn(clyde);
    }


    @Test
    void testSelectPreviousOption() throws IOException, URISyntaxException, FontFormatException {
        controller.step(game, List.of(GUI.ACTION.UP), 0);
        verify(mainMenu, times(1)).selectPreviousOption();
    }

    @Test
    void testSelectNextOption() throws IOException, URISyntaxException, FontFormatException {
        controller.step(game, List.of(GUI.ACTION.DOWN), 0);
        verify(mainMenu, times(1)).selectNextOption();
    }

    @Test
    void testStartSelected() throws Exception {
        when(mainMenu.singlePLayerSelected()).thenReturn(true);
        controller.step(game, List.of(GUI.ACTION.SELECT), 0);
        verify(game, times(1)).setState(any(MapSelectionMenuState.class));
    }

    @Test
    void testExitSelected() throws Exception {
        when(mainMenu.ExitSelected()).thenReturn(true);
        controller.step(game, List.of(GUI.ACTION.SELECT), 0);
        verify(game, times(1)).setState(null);
    }

    @Test
    void testResolutionSelected() throws Exception {
        when(mainMenu.ResolutionSelected()).thenReturn(true);

        when(game.getResolution()).thenReturn(GUI.SCREEN_RESOLUTION._900p);

        controller.step(game, List.of(GUI.ACTION.SELECT), 0);

        verify(game, times(1)).setResolution(GUI.SCREEN_RESOLUTION._1080p);
        verify(mainMenu, times(1)).setResolution(GUI.SCREEN_RESOLUTION._1080p);
    }

    @Test
    void testMasterVolumeSelected() throws Exception {
        GUI gui = mock(GUI.class);
        when(game.getGui()).thenReturn(gui);

        when(mainMenu.MasterVolumeSelected()).thenReturn(true);

        when(audioManager.getMasterVolume()).thenReturn(0.5f);

        controller.step(game, List.of(GUI.ACTION.SELECT), 0);

        verify(mainMenu, times(1)).setMasterVolume(anyFloat());
    }

    @Test
    void testGhostMovementBlinky() throws Exception {
        when(blinky.getPosition()).thenReturn(new Position(3, 4));

        controller.step(game, List.of(), 0);

        verify(blinky, times(1)).setDirection(Direction.RIGHT);

        when(blinky.getPosition()).thenReturn(new Position(7, 4));

        controller.step(game, List.of(), 0);

        verify(blinky, times(1)).setDirection(Direction.LEFT);
    }

    @Test
    void testGhostMovementInky() throws Exception {
        when(inky.getPosition()).thenReturn(new Position(5, 8));

        controller.step(game, List.of(), 0);

        verify(inky, times(1)).setDirection(Direction.DOWN);

        when(inky.getPosition()).thenReturn(new Position(5, 13));

        controller.step(game, List.of(), 0);

        verify(inky, times(1)).setDirection(Direction.UP);
    }

    @Test
    void testGhostMovementPinky() throws Exception {
        when(pinky.getPosition()).thenReturn(new Position(19, 13));

        controller.step(game, List.of(), 0);

        verify(pinky, times(1)).setDirection(Direction.RIGHT);

        when(pinky.getPosition()).thenReturn(new Position(26, 13));

        controller.step(game, List.of(), 0);

        verify(pinky, times(1)).setDirection(Direction.LEFT);
    }

    @Test
    void testGhostMovementClyde() throws Exception {
        when(clyde.getPosition()).thenReturn(new Position(24, 3));

        controller.step(game, List.of(), 0);

        verify(clyde, times(1)).setDirection(Direction.DOWN);

        when(clyde.getPosition()).thenReturn(new Position(24, 10));

        controller.step(game, List.of(), 0);

        verify(clyde, times(1)).setDirection(Direction.UP);
    }

}
