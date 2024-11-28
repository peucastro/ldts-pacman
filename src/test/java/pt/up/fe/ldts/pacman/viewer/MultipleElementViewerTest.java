package pt.up.fe.ldts.pacman.viewer;

import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pt.up.fe.ldts.pacman.model.game.element.pacman.Pacman;
import pt.up.fe.ldts.pacman.viewer.game.MultipleElementViewer;

import java.io.IOException;
import java.util.HashMap;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class MultipleElementViewerTest {
    @Test
    void testLanternaDependency() throws IOException {
        Renderer mockRenderer = Mockito.mock(Renderer.class);
        Pacman mockPacman = Mockito.mock(Pacman.class);
        MultipleElementViewer a = new MultipleElementViewer(mockRenderer, new HashMap<>());
        TextGraphics mock = Mockito.mock(TextGraphics.class);

        a.drawElement(mock,mockPacman);

        //number of colored pixels = 14*14 - 32
        verify(mock,times(14*14 - 32)).putString(any(),any());
        verify(mock,times(14*14 - 32)).setBackgroundColor(any());
    }
}
