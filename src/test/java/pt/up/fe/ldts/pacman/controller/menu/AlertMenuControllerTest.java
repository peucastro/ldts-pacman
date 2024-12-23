package pt.up.fe.ldts.pacman.controller.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.up.fe.ldts.pacman.Game;
import pt.up.fe.ldts.pacman.audio.AudioManager;
import pt.up.fe.ldts.pacman.model.game.Arena;
import pt.up.fe.ldts.pacman.model.game.element.pacman.Pacman;
import pt.up.fe.ldts.pacman.model.menu.AlertMenu;
import pt.up.fe.ldts.pacman.gui.GUI;
import pt.up.fe.ldts.pacman.audio.AudioPlayer;
import pt.up.fe.ldts.pacman.model.menu.MapSelectionMenu;
import pt.up.fe.ldts.pacman.states.menu.MainMenuState;
import pt.up.fe.ldts.pacman.states.menu.MapSelectionMenuState;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;

class AlertMenuControllerTest {
    private AlertMenuController controller;
    private AlertMenu alertMenu;
    private AudioManager audioManager;
    private AudioPlayer menuSelect;
    private AudioPlayer menuConfirmSelection;
    private Game game;

    @BeforeEach
    void setUp() {
        alertMenu = mock(AlertMenu.class);
        Arena arena = mock(Arena.class);
        game = mock(Game.class);
        audioManager = mock(AudioManager.class);

        menuSelect = mock(AudioPlayer.class);
        menuConfirmSelection = mock(AudioPlayer.class);

        when(audioManager.getAudio("menuSelect")).thenReturn(menuSelect);
        when(audioManager.getAudio("menuConfirmSelection")).thenReturn(menuConfirmSelection);

        when(menuSelect.getVolume()).thenReturn(0.25f);
        when(menuConfirmSelection.getVolume()).thenReturn(0.2f);

        when(game.getAudioManager()).thenReturn(audioManager);

        Pacman pacman = mock(Pacman.class);
        when(arena.getPacmans()).thenReturn(Collections.singletonList(pacman));

        when(alertMenu.getArena()).thenReturn(arena);

        controller = new AlertMenuController(alertMenu, audioManager);
    }

    @Test
    void testPlayAgainSelected() throws Exception {
        doAnswer(invocationOnMock -> {
            assertEquals("singleplayer", ((MapSelectionMenuState)invocationOnMock.getArgument(0)).getModel().getFolderstring());
            return null;
        }).when(game).setState(any(MapSelectionMenuState.class));
        when(alertMenu.PlayAgainSelected()).thenReturn(true);

        controller.step(game, List.of(GUI.ACTION.SELECT), 0);

        verify(game, times(1)).setState(any(MapSelectionMenuState.class));

        verify(menuConfirmSelection, times(1)).playOnce();
        verify(audioManager, times(1)).stopAllAudios();
    }

    @Test
    void testExitSelected() throws Exception {
        when(alertMenu.ExitSelected()).thenReturn(true);

        controller.step(game, List.of(GUI.ACTION.SELECT), 0);

        verify(game, times(1)).setState(any(MainMenuState.class));
        verify(menuConfirmSelection, times(1)).playOnce();
        verify(audioManager, times(1)).stopAllAudios();
    }

    @Test
    void testSelectNextOption() throws IOException, URISyntaxException, FontFormatException {
        controller.step(game, List.of(GUI.ACTION.DOWN),0);

        verify(menuSelect,times(1)).playOnce();
        verify(menuSelect, times(1)).playOnce();
        verify(alertMenu, times(1)).selectNextOption();
    }
}
