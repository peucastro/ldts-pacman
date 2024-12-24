package pt.up.fe.ldts.pacman.model.menu.element;

import com.googlecode.lanterna.TextColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.up.fe.ldts.pacman.model.Position;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TextBoxTest {
    private TextBox textBox;

    @BeforeEach
    void setUp() {
        textBox = new TextBox("Test", new Position(50, 50), new TextColor.RGB(255, 0, 0));
    }

    @Test
    void testGetText() {
        assertEquals("Test", textBox.getText());
    }

    @Test
    void testSetText() {
        textBox.setText("Updated");
        assertEquals("Updated", textBox.getText());
    }

    @Test
    void testGetColor() {
        assertEquals(new TextColor.RGB(255, 0, 0), textBox.getColor());
    }

    @Test
    void testSetColor() {
        textBox.setColor(new TextColor.RGB(0, 255, 0));
        assertEquals(new TextColor.RGB(0, 255, 0), textBox.getColor());
    }

    @Test
    void testGetPosition() {
        assertEquals(new Position(50, 50), textBox.getPosition());
    }
}
