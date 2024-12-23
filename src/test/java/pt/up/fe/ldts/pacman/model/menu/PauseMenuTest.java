package pt.up.fe.ldts.pacman.model.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.up.fe.ldts.pacman.gui.GUI;
import pt.up.fe.ldts.pacman.states.State;
import pt.up.fe.ldts.pacman.model.Position;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class PauseMenuTest {
    private PauseMenu pauseMenu;
    private State<?> mockState;

    @BeforeEach
    void setUp() {
        mockState = mock(State.class);
        pauseMenu = new PauseMenu(mockState, GUI.SCREEN_RESOLUTION._1080p, 0);
    }

    @Test
    void testCreateOptions() {
        var options = pauseMenu.getOptions();
        assertEquals(4, options.size());
        assertEquals("Resume", options.get(0).getText());
        assertEquals("Resolution: 1080p", options.get(1).getText());
        assertEquals("Master Volume: 0", options.get(2).getText());
        assertEquals("Exit to main menu", options.get(3).getText());
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
        pauseMenu.setSelectedOption(1);
        assertFalse(pauseMenu.ResumeSelected());
    }

    @Test
    void testResolutionSelected() {
        pauseMenu.setSelectedOption(1);
        assertTrue(pauseMenu.ResolutionSelected());
        pauseMenu.setSelectedOption(0);
        assertFalse(pauseMenu.ResolutionSelected());
    }

    @Test
    void testMasterVolumeSelected() {
        pauseMenu.setSelectedOption(2);
        assertTrue(pauseMenu.MasterVolumeSelected());
        pauseMenu.setSelectedOption(0);
        assertFalse(pauseMenu.MasterVolumeSelected());
    }

    @Test
    void testExitSelected() {
        pauseMenu.setSelectedOption(3);
        assertTrue(pauseMenu.ExitSelected());
        pauseMenu.setSelectedOption(0);
        assertFalse(pauseMenu.ExitSelected());
    }

    @Test
    void testSetResolution() {
        pauseMenu.setResolution(GUI.SCREEN_RESOLUTION._1440p);
        assertEquals("Resolution: 1440p", pauseMenu.getOptions().get(1).getText());
    }

    @Test
    void testSetMasterVolume() {
        pauseMenu.setMasterVolume(0.7f);
        assertEquals("Master Volume: 7", pauseMenu.getOptions().get(2).getText());
    }
}
