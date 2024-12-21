package pt.up.fe.ldts.pacman.states.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.up.fe.ldts.pacman.audio.AudioManager;
import pt.up.fe.ldts.pacman.audio.AudioPlayer;
import pt.up.fe.ldts.pacman.controller.Controller;
import pt.up.fe.ldts.pacman.controller.menu.AlertMenuController;
import pt.up.fe.ldts.pacman.model.menu.AlertMenu;
import pt.up.fe.ldts.pacman.viewer.Viewer;
import pt.up.fe.ldts.pacman.viewer.menu.AlertMenuViewer;
import pt.up.fe.ldts.pacman.viewer.game.ImageLoader;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AlertMenuStateTest {

    private AlertMenu mockAlertMenu;
    private AudioManager mockAudioManager;
    private AlertMenuState alertMenuState;

    @BeforeEach
    void setUp() throws IOException, URISyntaxException {
        // Mock dependencies
        mockAlertMenu = mock(AlertMenu.class);
        mockAudioManager = mock(AudioManager.class);

        AudioPlayer mockMenuSelect = mock(AudioPlayer.class);
        when(mockAudioManager.getAudio("menuSelect")).thenReturn(mockMenuSelect);
        when(mockAudioManager.getAudio("menuConfirmSelection")).thenReturn(mock(AudioPlayer.class));

        when(mockAlertMenu.getAlertFilePath()).thenReturn("PNGs/youwin.png");

        try (var mockedImageLoader = mockStatic(ImageLoader.class)) {
            BufferedImage mockBufferedImage = mock(BufferedImage.class);
            mockedImageLoader.when(() -> ImageLoader.loadBufferedImage(anyString()))
                    .thenReturn(mockBufferedImage);

            alertMenuState = new AlertMenuState(mockAlertMenu, mockAudioManager);
        }
    }

    @Test
    void testCreateViewer() throws IOException, URISyntaxException {
        Viewer<AlertMenu> viewer = alertMenuState.createViewer();
        assertNotNull(viewer);
        assertTrue(viewer instanceof AlertMenuViewer);
    }

    @Test
    void testCreateController() {
        Controller<AlertMenu> controller = alertMenuState.createController(mockAudioManager);
        assertNotNull(controller);
        assertTrue(controller instanceof AlertMenuController);
    }

    @Test
    void testInitialization() {
        assertNotNull(alertMenuState.getModel());
        assertNotNull(alertMenuState.getAudioManager());
    }

}
