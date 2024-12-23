package pt.up.fe.ldts.pacman.controller.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.up.fe.ldts.pacman.Game;
import pt.up.fe.ldts.pacman.audio.AudioManager;
import pt.up.fe.ldts.pacman.audio.AudioPlayer;
import pt.up.fe.ldts.pacman.gui.GUI;
import pt.up.fe.ldts.pacman.model.menu.MapSelectionMenu;
import pt.up.fe.ldts.pacman.states.game.GameState;
import pt.up.fe.ldts.pacman.states.menu.MainMenuState;
import pt.up.fe.ldts.pacman.states.menu.MapSelectionMenuState;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MapSelectionMenuControllerTest {
    private MapSelectionMenu mapSelectionMenu;
    private MapSelectionMenuController controller;
    private AudioManager audioManager;
    private AudioPlayer menuSelect;
    private AudioPlayer menuConfirmSelection;
    private Game game;

    @BeforeEach
    void setUp() {
        // Initialize mocks
        game = mock(Game.class);

        audioManager = mock(AudioManager.class);
        menuSelect = mock(AudioPlayer.class);
        menuConfirmSelection = mock(AudioPlayer.class);

        when(audioManager.getAudio("menuSelect")).thenReturn(menuSelect);
        when(audioManager.getAudio("menuConfirmSelection")).thenReturn(menuConfirmSelection);
        when(game.getAudioManager()).thenReturn(audioManager);

        // Initialize menu and controller
        mapSelectionMenu = new MapSelectionMenu("singleplayer");
        controller = new MapSelectionMenuController(mapSelectionMenu, audioManager);
    }

    @Test
    void testSelectMap() throws IOException, URISyntaxException, FontFormatException {
        // Arrange
        doAnswer(invocationOnMock -> {
            assertEquals(1, ((GameState)invocationOnMock.getArgument(0)).getModel().getPacmans().size());
            assertEquals(4, ((GameState)invocationOnMock.getArgument(0)).getModel().getGhosts().size());
            return null;
        }).when(game).setState(any(GameState.class));
        when(audioManager.getAudio(any())).thenReturn(mock(AudioPlayer.class));

        // Act
        controller.step(game, List.of(GUI.ACTION.SELECT), 0);

        // Assert
        verify(game, times(1)).setState(any(GameState.class));
        verify(menuConfirmSelection, times(1)).playOnce();
    }

    @Test
    void testQuitAction() throws IOException, URISyntaxException, FontFormatException {
        // Act
        controller.step(game, List.of(GUI.ACTION.QUIT), 0);

        // Assert
        verify(game).setState(any(MainMenuState.class));
    }

    @Test
    void testSelectNextMap() throws IOException, URISyntaxException, FontFormatException {
        // Act
        controller.step(game, List.of(GUI.ACTION.DOWN), 0);

        // Assert
        verify(menuSelect, times(1)).playOnce();
        assertEquals(1, mapSelectionMenu.getSelectedOption());
    }
}
