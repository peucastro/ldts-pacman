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
import pt.up.fe.ldts.pacman.states.menu.MainMenuState;
import pt.up.fe.ldts.pacman.states.menu.MapSelectionMenuState;

import static org.mockito.Mockito.*;
import java.util.Collections;
import java.util.List;

class AlertMenuControllerTest {
    private AlertMenuController controller;
    private AlertMenu alertMenu;
    private Arena arena;
    private Game game;
    private AudioManager audioManager;

    @BeforeEach
    void setUp() {
        alertMenu = mock(AlertMenu.class);
        arena = mock(Arena.class);
        game = mock(Game.class);
        audioManager = mock(AudioManager.class);

        AudioPlayer menuSelect = mock(AudioPlayer.class);
        AudioPlayer menuConfirmSelection = mock(AudioPlayer.class);

        when(audioManager.getAudio("menuSelect")).thenReturn(menuSelect);
        when(audioManager.getAudio("menuConfirmSelection")).thenReturn(menuConfirmSelection);

        when(game.getAudioManager()).thenReturn(audioManager);

        Pacman pacman = mock(Pacman.class);
        when(arena.getPacmans()).thenReturn(Collections.singletonList(pacman));

        when(alertMenu.getArena()).thenReturn(arena);

        controller = new AlertMenuController(alertMenu, audioManager);
    }

    @Test
    void testPlayAgainSelected() throws Exception {
        when(alertMenu.PlayAgainSelected()).thenReturn(true);

        controller.step(game, List.of(GUI.ACTION.SELECT), 0);

        verify(game, times(1)).setState(any(MapSelectionMenuState.class));
    }

    @Test
    void testExitSelected() throws Exception {
        when(alertMenu.ExitSelected()).thenReturn(true);

        controller.step(game, List.of(GUI.ACTION.SELECT), 0);

        verify(game, times(1)).setState(any(MainMenuState.class));
    }
}
