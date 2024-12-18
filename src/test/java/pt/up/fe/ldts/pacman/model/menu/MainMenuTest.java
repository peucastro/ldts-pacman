package pt.up.fe.ldts.pacman.model.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.up.fe.ldts.pacman.gui.GUI;
import pt.up.fe.ldts.pacman.model.Position;

import static org.junit.jupiter.api.Assertions.*;

class MainMenuTest {
    private MainMenu mainMenu;

    @BeforeEach
    void setUp() {
        mainMenu = new MainMenu(GUI.SCREEN_RESOLUTION._900p, 0);
    }

    @Test
    void testCreateOptions() {
        var options = mainMenu.createOptions();
        assertEquals(3, options.size());
        assertEquals("Start", options.get(0).getText());
        assertEquals("Settings", options.get(1).getText());
        assertEquals("Exit", options.get(2).getText());
    }

    @Test
    void testCreateTitle() {
        var title = mainMenu.createTitle();
        assertEquals("PACMAN", title.getText());
        assertEquals(new Position(145, 30), title.getPosition());
    }

    @Test
    void testSinglePlayerSelected() {
        mainMenu.setSelectedOption(0);
        assertTrue(mainMenu.singlePLayerSelected());
    }

    @Test
    void testMultiplayerSelected() {
        mainMenu.setSelectedOption(1);
        assertTrue(mainMenu.multiplayerSelected());
    }

    @Test
    void testResolutionSelected() {
        mainMenu.setSelectedOption(2);
        assertTrue(mainMenu.ResolutionSelected());
    }

    @Test
    void testMasterVolumeSelected() {
        mainMenu.setSelectedOption(3);
        assertTrue(mainMenu.MasterVolumeSelected());
    }

    @Test
    void testExitSelected() {
        mainMenu.setSelectedOption(4);
        assertTrue(mainMenu.ExitSelected());
    }

    @Test
    void testGetPacman() {
        assertNotNull(mainMenu.getPacman());
        assertEquals(new Position(14, 4), mainMenu.getPacman().getPosition());
    }
}
