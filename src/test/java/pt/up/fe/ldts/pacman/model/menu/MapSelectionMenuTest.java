package pt.up.fe.ldts.pacman.model.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.up.fe.ldts.pacman.model.Position;
import pt.up.fe.ldts.pacman.model.menu.element.TextBox;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MapSelectionMenuTest {
    private MapSelectionMenu mapSelectionMenuSingle;
    private MapSelectionMenu mapSelectionMenuMulti;

    @BeforeEach
    void setUp() {
        mapSelectionMenuSingle = new MapSelectionMenu("singleplayer");
    }

    @Test
    void testCreateOptionsSinglePlayer() {
        List<TextBox> options = mapSelectionMenuSingle.createOptions();

        assertEquals(5, options.size());

        assertEquals("Final Boss", options.get(0).getText());
        assertEquals("islands", options.get(1).getText());
        assertEquals("Normal Map", options.get(2).getText());
        assertEquals("Spirals", options.get(3).getText());
        assertEquals("Stripes", options.get(4).getText());
    }

    @Test
    void testCreateOptionsMultiPlayer() {
        mapSelectionMenuMulti = new MapSelectionMenu("multiplayer");
        List<TextBox> options = mapSelectionMenuMulti.createOptions();

        assertEquals(5, options.size());

        assertEquals("Final Boss", options.get(0).getText());
        assertEquals("islands", options.get(1).getText());
        assertEquals("Normal Map", options.get(2).getText());
        assertEquals("Spirals", options.get(3).getText());
        assertEquals("Stripes", options.get(4).getText());
    }

    @Test
    void testCreateTitle() {
        TextBox title = mapSelectionMenuSingle.createTitle();
        assertEquals("MAP SELECTION", title.getText());
        assertEquals(new Position(128, 30), title.getPosition());
    }

    @Test
    void testGetFolderstring() {
        assertEquals("singleplayer", mapSelectionMenuSingle.getFolderstring());
    }
}
