package pt.up.fe.ldts.pacman.model.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.up.fe.ldts.pacman.model.Position;

import static org.junit.jupiter.api.Assertions.*;

class MainMenuTest {
    private MainMenu mainMenu;

    @BeforeEach
    void setUp() {
        mainMenu = new MainMenu();
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
    void testStartSelected() {
        mainMenu.setSelectedOption(0);
        assertTrue(mainMenu.StartSelected());
    }

    @Test
    void testSettingsSelected() {
        mainMenu.setSelectedOption(1);
        assertTrue(mainMenu.SettingsSelected());
    }

    @Test
    void testExitSelected() {
        mainMenu.setSelectedOption(2);
        assertTrue(mainMenu.ExitSelected());
    }

    @Test
    void testGetPacman() {
        assertNotNull(mainMenu.getPacman());
        assertEquals(new Position(14, 4), mainMenu.getPacman().getPosition());
    }
}
