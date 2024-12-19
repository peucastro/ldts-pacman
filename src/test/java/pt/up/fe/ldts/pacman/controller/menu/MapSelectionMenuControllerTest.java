package pt.up.fe.ldts.pacman.controller.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.up.fe.ldts.pacman.Game;
import pt.up.fe.ldts.pacman.audio.AudioManager;
import pt.up.fe.ldts.pacman.states.game.GameState;
import pt.up.fe.ldts.pacman.gui.GUI;
import pt.up.fe.ldts.pacman.model.menu.MapSelectionMenu;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static org.mockito.Mockito.*;

class MapSelectionMenuControllerTest {
    private MapSelectionMenu mapSelectionMenu;
    private Game game;
    private MapSelectionMenuController controller;

    @BeforeEach
    void setUp() {
        mapSelectionMenu = mock(MapSelectionMenu.class);
        game = mock(Game.class);
        controller = new MapSelectionMenuController(mapSelectionMenu, AudioManager.getInstance());
    }

    @Test
    void testSelectPreviousOption() throws IOException, URISyntaxException, FontFormatException {
        controller.step(game, List.of(GUI.ACTION.UP), 0);
        verify(mapSelectionMenu, times(1)).selectPreviousOption();
    }

    @Test
    void testSelectNextOption() throws IOException, URISyntaxException, FontFormatException {
        controller.step(game, List.of(GUI.ACTION.DOWN), 0);
        verify(mapSelectionMenu, times(1)).selectNextOption();
    }

    @Test
    void testSelectMap1() throws Exception {
        when(mapSelectionMenu.getSelectedOption()).thenReturn(0);
        controller.step(game,  List.of(GUI.ACTION.SELECT), 0);
        verify(game, times(1)).setState(any(GameState.class));
    }

    @Test
    void testSelectMap2() throws Exception {
        when(mapSelectionMenu.getSelectedOption()).thenReturn(1);
        controller.step(game,  List.of(GUI.ACTION.SELECT), 0);
        verify(game, times(1)).setState(any(GameState.class));
    }
}
