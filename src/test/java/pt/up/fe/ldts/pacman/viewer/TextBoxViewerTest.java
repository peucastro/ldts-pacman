package pt.up.fe.ldts.pacman.viewer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.up.fe.ldts.pacman.gui.GUI;
import pt.up.fe.ldts.pacman.model.Position;
import pt.up.fe.ldts.pacman.model.menu.element.TextBox;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

public class TextBoxViewerTest {

    private TextBoxViewer textBoxViewer;
    private GUI mockGui;
    private TextBox mockTextBox;
    private Map<Character, BufferedImage> mockCharacters;

    @BeforeEach
    void setUp() {
        mockGui = mock(GUI.class);
        mockTextBox = mock(TextBox.class);
        mockCharacters = new HashMap<>();

        mockCharacters.put('A', mock(BufferedImage.class));
        mockCharacters.put('B', mock(BufferedImage.class));

        textBoxViewer = new TextBoxViewer(mockCharacters);
    }

    @Test
    void testDrawElement() {
        when(mockTextBox.getText()).thenReturn("AB");
        when(mockTextBox.getPosition()).thenReturn(new Position(10, 20));
        when(mockTextBox.getColor()).thenReturn(null);

        textBoxViewer.drawElement(mockGui, mockTextBox, 0);

        verify(mockGui, times(1)).drawCharacter(new Position(10, 20), mockCharacters.get('A'), null);
        verify(mockGui, times(1)).drawCharacter(new Position(15, 20), mockCharacters.get('B'), null);
    }

    @Test
    void testDrawElementWithNullCharacter() {
        when(mockTextBox.getText()).thenReturn("C");
        when(mockTextBox.getPosition()).thenReturn(new Position(10, 20));
        when(mockTextBox.getColor()).thenReturn(null);

        textBoxViewer.drawElement(mockGui, mockTextBox, 0);

        verify(mockGui, never()).drawCharacter(any(Position.class), any(BufferedImage.class), any());
    }
}
