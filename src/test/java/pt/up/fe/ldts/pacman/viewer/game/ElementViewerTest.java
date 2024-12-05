package pt.up.fe.ldts.pacman.viewer.game;

import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pt.up.fe.ldts.pacman.model.game.Position;
import pt.up.fe.ldts.pacman.model.game.element.Wall;
import pt.up.fe.ldts.pacman.model.game.element.collectibles.Cherry;
import pt.up.fe.ldts.pacman.model.game.element.collectibles.Orange;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class ElementViewerTest {
    @Test
    void testDrawElement() throws IOException, URISyntaxException {
        TextGraphics mockGraphics = Mockito.mock(TextGraphics.class);
        Renderer mockRenderer = Mockito.mock(Renderer.class,withSettings().useConstructor(mockGraphics).defaultAnswer(CALLS_REAL_METHODS));
        ElementViewer a = new ElementViewer(mockRenderer, "PNGs/items/cherry.png");
        ElementViewer b = new ElementViewer(mockRenderer, "PNGs/items/orange.png");
        ElementViewer c = new ElementViewer(mockRenderer, "PNGs/wall.png");
        Cherry cherry = new Cherry(new Position(0, 0));
        Orange orange = new Orange(new Position(0, 0));
        Wall wall = new Wall(new Position(0, 0));

        a.drawElement(cherry);//cherry has 101 drawn characters

        verify(mockGraphics, times(101)).putString(any(), any());
        verify(mockGraphics, times(101)).setBackgroundColor(any());

        b.drawElement(orange);//orange has 146 drawn characters

        verify(mockGraphics, times(146 + 101)).putString(any(), any());
        verify(mockGraphics, times(146 + 101)).setBackgroundColor(any());

        c.drawElement(wall);//wall is a full 14*14 drawn space, or 196 characters

        verify(mockGraphics, times(196 + 146 + 101)).putString(any(), any());
        verify(mockGraphics, times(196 + 146 + 101)).setBackgroundColor(any());
    }
}
