package pt.up.fe.ldts.pacman.model.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.up.fe.ldts.pacman.model.Position;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MapSelectionMenuTest {
    private MapSelectionMenu mapSelectionMenu;

    @BeforeEach
    void setUp() {
        mapSelectionMenu = new MapSelectionMenu("singleplayer");
    }

    @Test
    void testCreateOptions() {
        var options = mapSelectionMenu.createOptions();
        assertEquals(1, options.size());
        assertEquals("Map 1", options.get(0).getText());
    }

    @Test
    void testCreateTitle() {
        var title = mapSelectionMenu.createTitle();
        assertEquals("MAP SELECTION", title.getText());
        assertEquals(new Position(128, 30), title.getPosition());
    }
}
