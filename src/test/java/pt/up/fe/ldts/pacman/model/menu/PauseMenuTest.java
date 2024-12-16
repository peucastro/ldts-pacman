package pt.up.fe.ldts.pacman.model.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.up.fe.ldts.pacman.States.State;
import pt.up.fe.ldts.pacman.model.Position;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

class PauseMenuTest {
    private PauseMenu pauseMenu;
    private State<?> mockState;

    @BeforeEach
    void setUp() {
        mockState = mock(State.class);
        pauseMenu = new PauseMenu(mockState);
    }

    @Test
    void testCreateOptions() {
        var options = pauseMenu.createOptions();
        assertEquals(3, options.size());
        assertEquals("Resume", options.get(0).getText());
        assertEquals("Settings", options.get(1).getText());
        assertEquals("Exit to main menu", options.get(2).getText());
    }

    @Test
    void testCreateTitle() {
        var title = pauseMenu.createTitle();
        assertEquals("Paused", title.getText());
        assertEquals(new Position(145, 30), title.getPosition());
    }

    @Test
    void testGetPausedState() {
        assertEquals(mockState, pauseMenu.getPausedState());
    }

    @Test
    void testGetPauseSign() {
        var pauseSign = pauseMenu.getPauseSign();
        assertEquals("||", pauseSign.getText());
        assertEquals(new Position(155, 41), pauseSign.getPosition());
    }

    @Test
    void testResumeSelected() {
        pauseMenu.setSelectedOption(0);
        assertTrue(pauseMenu.ResumeSelected());
    }

    @Test
    void testSettingsSelected() {
        pauseMenu.setSelectedOption(1);
        assertTrue(pauseMenu.SettingsSelected());
    }

    @Test
    void testExitSelected() {
        pauseMenu.setSelectedOption(2);
        assertTrue(pauseMenu.ExitSelected());
    }
}
