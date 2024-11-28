package pt.up.fe.ldts.pacman.viewer.game;

import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pt.up.fe.ldts.pacman.model.game.Position;
import pt.up.fe.ldts.pacman.model.game.element.collectibles.Cherry;
import pt.up.fe.ldts.pacman.model.game.element.collectibles.Orange;
import pt.up.fe.ldts.pacman.viewer.Renderer;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class ElementViewerTest {
    @Test
    void testDrawElement() throws IOException {
        Renderer mockRenderer = Mockito.mock(Renderer.class);
        ElementViewer a = new ElementViewer(mockRenderer, "src/main/resources/PNGs/items/cherry.png");
        ElementViewer b = new ElementViewer(mockRenderer, "src/main/resources/PNGs/items/orange.png");
        TextGraphics mockA = Mockito.mock(TextGraphics.class);
        Cherry cherry = new Cherry(new Position(0, 0));
        TextGraphics mockB = Mockito.mock(TextGraphics.class);
        Orange orange = new Orange(new Position(0, 0));

        a.drawElement(mockA, cherry);
        b.drawElement(mockB, orange);

        verify(mockA, times(101)).putString(any(), any());
        verify(mockA, times(101)).setBackgroundColor(any());
        verify(mockB, times(146)).putString(any(), any());
        verify(mockB, times(146)).setBackgroundColor(any());
    }
}
