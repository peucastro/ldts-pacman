package pt.up.fe.ldts.pacman.states.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.up.fe.ldts.pacman.audio.AudioManager;
import pt.up.fe.ldts.pacman.audio.AudioPlayer;
import pt.up.fe.ldts.pacman.controller.Controller;
import pt.up.fe.ldts.pacman.controller.menu.MainMenuController;
import pt.up.fe.ldts.pacman.model.menu.MainMenu;
import pt.up.fe.ldts.pacman.viewer.Viewer;
import pt.up.fe.ldts.pacman.viewer.menu.MainMenuViewer;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MainMenuStateTest {

    private MainMenu mockMainMenu;
    private AudioManager mockAudioManager;
    private MainMenuState mainMenuState;

    @BeforeEach
    void setUp() throws IOException, URISyntaxException {
        mockMainMenu = mock(MainMenu.class);
        mockAudioManager = mock(AudioManager.class);

        AudioPlayer mockMenuSelect = mock(AudioPlayer.class);
        when(mockAudioManager.getAudio("menuSelect")).thenReturn(mockMenuSelect);
        when(mockAudioManager.getAudio("menuConfirmSelection")).thenReturn(mock(AudioPlayer.class));

        mainMenuState = new MainMenuState(mockMainMenu, mockAudioManager);
    }

    @Test
    void testCreateViewer() throws IOException {
        Viewer<MainMenu> viewer = mainMenuState.createViewer();
        assertNotNull(viewer);
        assertTrue(viewer instanceof MainMenuViewer);
    }

    @Test
    void testCreateController() {
        Controller<MainMenu> controller = mainMenuState.createController(mockAudioManager);
        assertNotNull(controller);
        assertTrue(controller instanceof MainMenuController);
    }

    @Test
    void testInitialization() {
        assertNotNull(mainMenuState.getModel());
        assertNotNull(mainMenuState.getAudioManager());
    }
}
