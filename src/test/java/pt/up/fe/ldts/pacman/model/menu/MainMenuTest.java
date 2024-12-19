package pt.up.fe.ldts.pacman.model.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.up.fe.ldts.pacman.gui.GUI;
import pt.up.fe.ldts.pacman.model.Position;
import pt.up.fe.ldts.pacman.model.game.element.Direction;

import java.util.Set;

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
        assertEquals(5, options.size());
        assertEquals("Single player", options.get(0).getText());
        assertEquals("Multi player", options.get(1).getText());
        assertEquals("Resolution: 900p", options.get(2).getText());
        assertEquals("Master Volume: 10", options.get(3).getText());
        assertEquals("Exit", options.get(4).getText());

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

    @Test
    void testSetResolution() {
        mainMenu.setResolution(GUI.SCREEN_RESOLUTION._1080p);
        assertEquals("Resolution: 1080p", mainMenu.getOptions().get(2).getText());
    }

    @Test
    void testSetMasterVolume() {
        mainMenu.setMasterVolume(0.7f);
        assertEquals("Master Volume: 7", mainMenu.getOptions().get(3).getText());
    }

    @Test
    void testGetBlinky() {
        assertNotNull(mainMenu.getBlinky());
        assertEquals(new Position(7, 4), mainMenu.getBlinky().getPosition());
        assertEquals(Direction.LEFT, mainMenu.getBlinky().getDirection());
    }

    @Test
    void testGetInky() {
        assertNotNull(mainMenu.getInky());
        assertEquals(new Position(5, 8), mainMenu.getInky().getPosition());
        assertEquals(Direction.DOWN, mainMenu.getInky().getDirection());
    }

    @Test
    void testGetPinky() {
        assertNotNull(mainMenu.getPinky());
        assertEquals(new Position(21, 13), mainMenu.getPinky().getPosition());
        assertEquals(Direction.RIGHT, mainMenu.getPinky().getDirection());
    }

    @Test
    void testGetClyde() {
        assertNotNull(mainMenu.getClyde());
        assertEquals(new Position(24, 10), mainMenu.getClyde().getPosition());
        assertEquals(Direction.UP, mainMenu.getClyde().getDirection());
    }

    @Test
    void testGetBlankPositions() {
        Set<Position> blankPositions = mainMenu.getBlankPositions();
        assertNotNull(blankPositions);

        assertTrue(blankPositions.contains(new Position(7, 4))); // Blinky's path
        assertTrue(blankPositions.contains(new Position(5, 8))); // Inky's path
        assertTrue(blankPositions.contains(new Position(21, 13))); // Pinky's path
        assertTrue(blankPositions.contains(new Position(24, 10))); // Clyde's path
    }

}
