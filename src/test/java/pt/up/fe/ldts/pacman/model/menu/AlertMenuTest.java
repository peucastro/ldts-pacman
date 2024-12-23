package pt.up.fe.ldts.pacman.model.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.up.fe.ldts.pacman.model.Position;
import pt.up.fe.ldts.pacman.model.game.Arena;
import pt.up.fe.ldts.pacman.model.menu.element.TextBox;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class AlertMenuTest {
    private AlertMenu alertMenu;
    private Arena mockArena;

    @BeforeEach
    void setUp() {
        mockArena = mock(Arena.class);
        alertMenu = new AlertMenu(mockArena, "PNGs/gameover.png");
    }

    @Test
    void testCreateOptions() {
        List<TextBox> options = alertMenu.getOptions();

        assertEquals(2, options.size());
        assertEquals("Play Again", options.get(0).getText());
        assertEquals(new Position(135, 99), options.get(0).getPosition());
        assertEquals("Back to main menu", options.get(1).getText());
        assertEquals(new Position(118, 110), options.get(1).getPosition());
    }

    @Test
    void testPlayAgainSelected() {
        alertMenu.setSelectedOption(0);
        assertTrue(alertMenu.PlayAgainSelected());
        alertMenu.setSelectedOption(1);
        assertFalse(alertMenu.PlayAgainSelected());
    }

    @Test
    void testExitSelected() {
        alertMenu.setSelectedOption(1);
        assertTrue(alertMenu.ExitSelected());
        alertMenu.setSelectedOption(0);
        assertFalse(alertMenu.ExitSelected());
    }

    @Test
    void testGetArena() {
        assertEquals(mockArena, alertMenu.getArena());
    }

    @Test
    void testGetAlertFilePath() {
        assertEquals("PNGs/gameover.png", alertMenu.getAlertFilePath());
    }
}
