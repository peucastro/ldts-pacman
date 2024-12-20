package pt.up.fe.ldts.pacman.model.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.up.fe.ldts.pacman.model.Position;
import pt.up.fe.ldts.pacman.model.menu.element.TextBox;

import java.util.List;

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

        assertEquals("1 Normal Map", options.get(0).getText());
        assertEquals("2 Spirals", options.get(1).getText());
        assertEquals("3 Stripes", options.get(2).getText());
        assertEquals("4 Islands", options.get(3).getText());
        assertEquals("5 Final Boss", options.get(4).getText());
    }

    @Test
    void testCreateOptionsMultiPlayer() {
        mapSelectionMenuMulti = new MapSelectionMenu("multiplayer");
        List<TextBox> options = mapSelectionMenuMulti.createOptions();

        assertEquals(5, options.size());

        assertEquals("1 Normal Map", options.get(0).getText());
        assertEquals("2 Spirals", options.get(1).getText());
        assertEquals("3 Stripes", options.get(2).getText());
        assertEquals("4 Islands", options.get(3).getText());
        assertEquals("5 Final Boss", options.get(4).getText());
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
