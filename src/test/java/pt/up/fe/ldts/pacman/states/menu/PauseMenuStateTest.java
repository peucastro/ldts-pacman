package pt.up.fe.ldts.pacman.states.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.up.fe.ldts.pacman.audio.AudioManager;
import pt.up.fe.ldts.pacman.audio.AudioPlayer;
import pt.up.fe.ldts.pacman.controller.Controller;
import pt.up.fe.ldts.pacman.controller.menu.PauseMenuController;
import pt.up.fe.ldts.pacman.model.menu.PauseMenu;
import pt.up.fe.ldts.pacman.viewer.Viewer;
import pt.up.fe.ldts.pacman.viewer.menu.PauseMenuViewer;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PauseMenuStateTest {

    private AudioManager mockAudioManager;
    private PauseMenuState pauseMenuState;

    @BeforeEach
    void setUp() throws IOException, URISyntaxException {
        PauseMenu mockPauseMenu = mock(PauseMenu.class);
        mockAudioManager = mock(AudioManager.class);

        AudioPlayer mockMenuSelect = mock(AudioPlayer.class);
        when(mockAudioManager.getAudio("menuSelect")).thenReturn(mockMenuSelect);
        when(mockAudioManager.getAudio("menuConfirmSelection")).thenReturn(mock(AudioPlayer.class));

        pauseMenuState = new PauseMenuState(mockPauseMenu, mockAudioManager);
    }

    @Test
    void testCreateViewer() throws IOException {
        Viewer<PauseMenu> viewer = pauseMenuState.createViewer();
        assertNotNull(viewer);
        assertInstanceOf(PauseMenuViewer.class, viewer);
    }

    @Test
    void testCreateController() {
        Controller<PauseMenu> controller = pauseMenuState.createController(mockAudioManager);
        assertNotNull(controller);
        assertInstanceOf(PauseMenuController.class, controller);
    }

    @Test
    void testInitialization() {
        assertNotNull(pauseMenuState.getModel());
        assertNotNull(pauseMenuState.getAudioManager());
    }
}
