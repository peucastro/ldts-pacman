package pt.up.fe.ldts.pacman.states.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pt.up.fe.ldts.pacman.audio.AudioManager;
import pt.up.fe.ldts.pacman.audio.AudioPlayer;
import pt.up.fe.ldts.pacman.controller.game.DyingStateController;
import pt.up.fe.ldts.pacman.model.game.Arena;
import pt.up.fe.ldts.pacman.viewer.game.ArenaViewer;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

public class DyingStateTest {

    private Arena mockArena;
    private AudioManager mockAudioManager;
    private DyingState dyingState;
    private AudioPlayer mockAudioPlayer;

    @BeforeEach
    void setUp() throws IOException, URISyntaxException {
        mockArena = mock(Arena.class);
        mockAudioManager = mock(AudioManager.class);
        mockAudioPlayer = mock(AudioPlayer.class);

        Mockito.when(mockAudioManager.getAudio("deathAudio")).thenReturn(mockAudioPlayer);
        dyingState = new DyingState(mockArena, mockAudioManager);
    }

    @Test
    void testCreateViewer() throws IOException, URISyntaxException {
        ArenaViewer viewer = (ArenaViewer) dyingState.createViewer();

        assertNotNull(viewer);
    }

    @Test
    void testCreateController() {
        DyingStateController controller = (DyingStateController) dyingState.createController(mockAudioManager);

        assertNotNull(controller);
    }

    @Test
    void testInitialization() {
        assertNotNull(dyingState.getModel(), "The model should be properly initialized");
        assertNotNull(dyingState.getAudioManager(), "The audio manager should be properly initialized");
    }
}
