package pt.up.fe.ldts.pacman.model.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.up.fe.ldts.pacman.model.Position;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MapSelectionMenuTest {
    private MapSelectionMenu mapSelectionMenu;

    @BeforeEach
    void setUp() {
        mapSelectionMenu = new MapSelectionMenu();
    }

    @Test
    void testCreateOptions() {
        var options = mapSelectionMenu.createOptions();
        assertEquals(2, options.size());
        assertEquals("Map 1", options.get(0).getText());
        assertEquals("Map 2", options.get(1).getText());
    }

    @Test
    void testCreateTitle() {
        var title = mapSelectionMenu.createTitle();
        assertEquals("MAP SELECTION", title.getText());
        assertEquals(new Position(80, 30), title.getPosition());
    }
}
