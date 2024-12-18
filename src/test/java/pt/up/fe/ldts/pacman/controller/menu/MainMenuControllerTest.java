package pt.up.fe.ldts.pacman.controller.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.up.fe.ldts.pacman.Game;
import pt.up.fe.ldts.pacman.states.menu.MapSelectionMenuState;
import pt.up.fe.ldts.pacman.gui.GUI;
import pt.up.fe.ldts.pacman.model.menu.MainMenu;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.mockito.Mockito.*;

class MainMenuControllerTest {
    private MainMenu mainMenu;
    private Game game;
    private MainMenuController controller;

    @BeforeEach
    void setUp() {
        mainMenu = mock(MainMenu.class);
        game = mock(Game.class);
        controller = new MainMenuController(mainMenu);
    }

    @Test
    void testSelectPreviousOption() throws IOException, URISyntaxException {
        controller.step(game, GUI.ACTION.UP, 0);
        verify(mainMenu, times(1)).selectPreviousOption();
    }

    @Test
    void testSelectNextOption() throws IOException, URISyntaxException {
        controller.step(game, GUI.ACTION.DOWN, 0);
        verify(mainMenu, times(1)).selectNextOption();
    }

    @Test
    void testStartSelected() throws Exception {
        when(mainMenu.StartSelected()).thenReturn(true);
        controller.step(game, GUI.ACTION.SELECT, 0);
        verify(game, times(1)).setState(any(MapSelectionMenuState.class));
    }

    @Test
    void testExitSelected() throws Exception {
        when(mainMenu.ExitSelected()).thenReturn(true);
        controller.step(game, GUI.ACTION.SELECT, 0);
        verify(game, times(1)).setState(null);
    }
}
