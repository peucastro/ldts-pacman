package pt.up.fe.ldts.pacman.controller.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.up.fe.ldts.pacman.Game;
import pt.up.fe.ldts.pacman.MockAudio;
import pt.up.fe.ldts.pacman.audio.AudioManager;
import pt.up.fe.ldts.pacman.audio.AudioPlayer;
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
    private AudioPlayer mockAudio;

    @BeforeEach
    void setUp() {
        pauseMenu = mock(PauseMenu.class);
        game = mock(Game.class);
        audioManager = mock(AudioManager.class);
        mockAudio = mock(AudioPlayer.class);
        when(audioManager.getAudio(any())).thenReturn(mockAudio);

        // Mock the game's getAudioManager and getResolution methods
        when(game.getAudioManager()).thenReturn(audioManager);
        when(game.getResolution()).thenReturn(GUI.SCREEN_RESOLUTION._900p);

        controller = new PauseMenuController(pauseMenu, audioManager);
    }

    @Test
    void testSelectPreviousOption() throws IOException, URISyntaxException, FontFormatException {
        controller.step(game, List.of(GUI.ACTION.UP), 0);

        verify(pauseMenu, times(1)).selectPreviousOption();
        verify(mockAudio,times(1)).playOnce();
    }

    @Test
    void testSelectNextOption() throws IOException, URISyntaxException, FontFormatException {
        controller.step(game, List.of(GUI.ACTION.DOWN), 0);

        verify(pauseMenu, times(1)).selectNextOption();
        verify(mockAudio,times(1)).playOnce();
    }

    @Test
    void testResumeSelected() throws Exception {
        when(pauseMenu.ResumeSelected()).thenReturn(true);

        controller.step(game, List.of(GUI.ACTION.SELECT), 0);

        State<?> ps = verify(pauseMenu, times(1)).getPausedState();
        verify(game, times(1)).setState(ps);
        verify(mockAudio,times(1)).playOnce();
    }

    @Test
    void testExitSelected() throws Exception {
        when(pauseMenu.ExitSelected()).thenReturn(true);
        when(game.getAudioManager()).thenReturn(audioManager);

        controller.step(game, List.of(GUI.ACTION.SELECT), 0);
        verify(game, times(1)).setState(any(MainMenuState.class));
        verify(mockAudio,times(1)).playOnce();
    }

    @Test
    void testResolutionSelected() throws Exception {
        when(pauseMenu.ResolutionSelected()).thenReturn(true);

        when(game.getResolution()).thenReturn(GUI.SCREEN_RESOLUTION._900p);

        controller.step(game, List.of(GUI.ACTION.SELECT), 0);

        verify(game, times(1)).setResolution(GUI.SCREEN_RESOLUTION._1080p);
        verify(pauseMenu, times(1)).setResolution(GUI.SCREEN_RESOLUTION._1080p);
        verify(mockAudio,times(1)).playOnce();
    }

    @Test
    void testMasterVolumeSelected() throws Exception {
        GUI gui = mock(GUI.class);
        when(game.getGui()).thenReturn(gui);

        when(pauseMenu.MasterVolumeSelected()).thenReturn(true);

        when(audioManager.getMasterVolume()).thenReturn(0.5f);

        controller.step(game, List.of(GUI.ACTION.SELECT), 0);

        verify(pauseMenu, times(1)).setMasterVolume(anyFloat());
        verify(mockAudio,times(1)).playOnce();
    }

    @Test
    void testSetVolume(){
        verify(mockAudio, times(1)).setVolume(0.25f);
        verify(mockAudio, times(1)).setVolume(0.2f);
    }
}