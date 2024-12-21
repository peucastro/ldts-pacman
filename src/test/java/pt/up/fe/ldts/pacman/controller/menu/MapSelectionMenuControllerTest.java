package pt.up.fe.ldts.pacman.controller.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.up.fe.ldts.pacman.Game;
import pt.up.fe.ldts.pacman.MockAudio;
import pt.up.fe.ldts.pacman.audio.AudioManager;
import pt.up.fe.ldts.pacman.audio.AudioPlayer;
import pt.up.fe.ldts.pacman.gui.GUI;
import pt.up.fe.ldts.pacman.model.menu.MapSelectionMenu;
import pt.up.fe.ldts.pacman.states.game.GameState;
import pt.up.fe.ldts.pacman.states.menu.MainMenuState;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MapSelectionMenuControllerTest {
    private MapSelectionMenu mapSelectionMenu;
    private MapSelectionMenuController controller;
    private AudioManager audioManager;
    private GUI gui;
    private Game game;

    @BeforeEach
    void setUp() {
        // Reset the mock AudioManager
        audioManager = MockAudio.getMockAudioManager();

        // Initialize mocks
        gui = mock(GUI.class);
        game = mock(Game.class);

        // Initialize menu and controller
        mapSelectionMenu = new MapSelectionMenu("singleplayer");
        controller = new MapSelectionMenuController(mapSelectionMenu, audioManager);

        // Stub game behavior
        when(game.getAudioManager()).thenReturn(audioManager);
    }

    @Test
    void testSelectMap() throws IOException, URISyntaxException, FontFormatException {
        controller.step(game, List.of(GUI.ACTION.SELECT), 0);

        verify(game, times(1)).setState(any(GameState.class));
    }


    @Test
    void testQuitAction() throws IOException, URISyntaxException, FontFormatException {
        // Simulate the QUIT action
        when(gui.getNextAction()).thenReturn(List.of(GUI.ACTION.QUIT));

        controller.step(game, gui.getNextAction(), System.currentTimeMillis());

        // Verify that the game state changes to MainMenuState
        verify(game).setState(any(MainMenuState.class));
    }
}