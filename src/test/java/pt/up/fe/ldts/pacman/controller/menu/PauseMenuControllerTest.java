package pt.up.fe.ldts.pacman.controller.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.up.fe.ldts.pacman.Game;
import pt.up.fe.ldts.pacman.MockAudio;
import pt.up.fe.ldts.pacman.audio.AudioManager;
import pt.up.fe.ldts.pacman.states.menu.MainMenuState;
import pt.up.fe.ldts.pacman.states.State;
import pt.up.fe.ldts.pacman.gui.GUI;
import pt.up.fe.ldts.pacman.model.menu.PauseMenu;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static org.mockito.Mockito.*;

class PauseMenuControllerTest {
    private PauseMenu pauseMenu;
    private Game game;
    private PauseMenuController controller;
    private AudioManager audioManager;

    @BeforeEach
    void setUp() {
        pauseMenu = mock(PauseMenu.class);
        game = mock(Game.class);
        audioManager = MockAudio.getMockAudioManager();

        // Mock the game's getAudioManager and getResolution methods
        when(game.getAudioManager()).thenReturn(audioManager);
        when(game.getResolution()).thenReturn(GUI.SCREEN_RESOLUTION._900p);

        controller = new PauseMenuController(pauseMenu, audioManager);
    }

    @Test
    void testSelectPreviousOption() throws IOException, URISyntaxException, FontFormatException {
        controller.step(game, List.of(GUI.ACTION.UP), 0);
        verify(pauseMenu, times(1)).selectPreviousOption();
    }

    @Test
    void testSelectNextOption() throws IOException, URISyntaxException, FontFormatException {
        controller.step(game, List.of(GUI.ACTION.DOWN), 0);
        verify(pauseMenu, times(1)).selectNextOption();
    }

    @Test
    void testResumeSelected() throws Exception {
        when(pauseMenu.ResumeSelected()).thenReturn(true);
        controller.step(game, List.of(GUI.ACTION.SELECT), 0);
        State<?> ps = verify(pauseMenu, times(1)).getPausedState();
        verify(game, times(1)).setState(ps);
    }

    @Test
    void testExitSelected() throws Exception {
        when(pauseMenu.ExitSelected()).thenReturn(true);
        when(game.getAudioManager()).thenReturn(audioManager);

        controller.step(game, List.of(GUI.ACTION.SELECT), 0);
        verify(game, times(1)).setState(any(MainMenuState.class));
    }
}