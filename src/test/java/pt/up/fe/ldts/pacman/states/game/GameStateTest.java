package pt.up.fe.ldts.pacman.states.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.up.fe.ldts.pacman.audio.AudioManager;
import pt.up.fe.ldts.pacman.audio.AudioPlayer;
import pt.up.fe.ldts.pacman.controller.Controller;
import pt.up.fe.ldts.pacman.controller.game.ArenaController;
import pt.up.fe.ldts.pacman.model.game.Arena;
import pt.up.fe.ldts.pacman.viewer.Viewer;
import pt.up.fe.ldts.pacman.viewer.game.ArenaViewer;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GameStateTest {

    private AudioManager mockAudioManager;
    private GameState gameState;

    @BeforeEach
    void setUp() throws IOException, URISyntaxException {
        Arena mockArena = mock(Arena.class);
        mockAudioManager = mock(AudioManager.class);
        AudioPlayer mockAudioPlayer = mock(AudioPlayer.class);

        when(mockAudioManager.getAudio("ghostEaten")).thenReturn(mockAudioPlayer);
        when(mockAudioManager.getAudio("collectibleEaten")).thenReturn(mockAudioPlayer);
        when(mockAudioManager.getAudio("ghostsAliveSiren")).thenReturn(mockAudioPlayer);
        when(mockAudioManager.getAudio("ghostsScaredSiren")).thenReturn(mockAudioPlayer);

        gameState = new GameState(mockArena, mockAudioManager);
    }

    @Test
    void testCreateViewer() throws IOException, URISyntaxException {
        Viewer<Arena> viewer = gameState.createViewer();
        assertNotNull(viewer);
        assertInstanceOf(ArenaViewer.class, viewer);
    }

    @Test
    void testCreateController() {
        Controller<Arena> controller = gameState.createController(mockAudioManager);
        assertNotNull(controller);
        assertInstanceOf(ArenaController.class, controller);
    }

    @Test
    void testInitialization() {
        assertNotNull(gameState.getModel());
        assertNotNull(gameState.getAudioManager());
    }

}
