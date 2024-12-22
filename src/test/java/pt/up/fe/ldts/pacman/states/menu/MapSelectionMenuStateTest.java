package pt.up.fe.ldts.pacman.states.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.up.fe.ldts.pacman.audio.AudioManager;
import pt.up.fe.ldts.pacman.audio.AudioPlayer;
import pt.up.fe.ldts.pacman.controller.Controller;
import pt.up.fe.ldts.pacman.controller.menu.MapSelectionMenuController;
import pt.up.fe.ldts.pacman.model.menu.MapSelectionMenu;
import pt.up.fe.ldts.pacman.viewer.Viewer;
import pt.up.fe.ldts.pacman.viewer.menu.MapSelectionMenuViewer;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MapSelectionMenuStateTest {

    private AudioManager mockAudioManager;
    private MapSelectionMenuState mapSelectionMenuState;

    @BeforeEach
    void setUp() throws IOException, URISyntaxException {
        MapSelectionMenu mockMapSelectionMenu = mock(MapSelectionMenu.class);
        mockAudioManager = mock(AudioManager.class);

        AudioPlayer mockMenuSelect = mock(AudioPlayer.class);
        when(mockAudioManager.getAudio("menuSelect")).thenReturn(mockMenuSelect);
        when(mockAudioManager.getAudio("menuConfirmSelection")).thenReturn(mock(AudioPlayer.class));

        mapSelectionMenuState = new MapSelectionMenuState(mockMapSelectionMenu, mockAudioManager);
    }

    @Test
    void testCreateViewer() throws IOException {
        Viewer<MapSelectionMenu> viewer = mapSelectionMenuState.createViewer();
        assertNotNull(viewer);
        assertInstanceOf(MapSelectionMenuViewer.class, viewer);
    }

    @Test
    void testCreateController() {
        Controller<MapSelectionMenu> controller = mapSelectionMenuState.createController(mockAudioManager);
        assertNotNull(controller);
        assertInstanceOf(MapSelectionMenuController.class, controller);
    }

    @Test
    void testInitialization() {
        assertNotNull(mapSelectionMenuState.getModel());
        assertNotNull(mapSelectionMenuState.getAudioManager());
    }
}
