package pt.up.fe.ldts.pacman.model.menu;

import com.googlecode.lanterna.TextColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.up.fe.ldts.pacman.model.Position;
import pt.up.fe.ldts.pacman.model.menu.element.TextBox;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestMenu extends Menu {
    @Override
    protected List<TextBox> createOptions() {
        return List.of(
                new TextBox("Option 1", new Position(100, 100), new TextColor.RGB(255, 255, 255)),
                new TextBox("Option 2", new Position(100, 110), new TextColor.RGB(255, 255, 255)),
                new TextBox("Option 3", new Position(100, 120), new TextColor.RGB(255, 255, 255))
        );
    }

    @Override
    protected TextBox createTitle() {
        return new TextBox("Test Menu", new Position(100, 50), new TextColor.RGB(255, 255, 255));
    }
}

class MenuTest {
    private TestMenu menu;

    @BeforeEach
    void setUp() {
        menu = new TestMenu();
        menu.initializeOptions();
    }

    @Test
    void testInitializeOptions() {
        assertNotNull(menu.getOptions());
        assertEquals(3, menu.getOptions().size());

        var firstOption = menu.getOptions().getFirst();
        assertEquals("Option 1", firstOption.getText());
        assertEquals(new TextColor.RGB(255, 255, 0), firstOption.getColor());

        var otherOption = menu.getOptions().get(1);
        assertEquals("Option 2", otherOption.getText());
        assertEquals(new TextColor.RGB(255, 255, 255), otherOption.getColor());
    }

    @Test
    void testGetAndSetSelectedOption() {
        assertEquals(0, menu.getSelectedOption());

        menu.setSelectedOption(2);
        assertEquals(2, menu.getSelectedOption());
    }

    @Test
    void testSelectNextOption() {
        menu.selectNextOption();
        assertEquals(1, menu.getSelectedOption());
        assertEquals(new TextColor.RGB(255, 255, 255), menu.getOptions().get(0).getColor());
        assertEquals(new TextColor.RGB(255, 255, 0), menu.getOptions().get(1).getColor());

        menu.selectNextOption();
        assertEquals(2, menu.getSelectedOption());

        menu.selectNextOption();
        assertEquals(0, menu.getSelectedOption());
    }

    @Test
    void testSelectPreviousOption() {
        menu.selectPreviousOption();
        assertEquals(2, menu.getSelectedOption());
        assertEquals(new TextColor.RGB(255, 255, 255), menu.getOptions().get(0).getColor());
        assertEquals(new TextColor.RGB(255, 255, 0), menu.getOptions().get(2).getColor());

        menu.selectPreviousOption();
        assertEquals(1, menu.getSelectedOption());
    }

    @Test
    void testCreateTitle() {
        var title = menu.getTitle();
        assertNotNull(title);
        assertEquals("Test Menu", title.getText());
        assertEquals(new Position(100, 50), title.getPosition());
        assertEquals(new TextColor.RGB(255, 255, 255), title.getColor());
    }
}
