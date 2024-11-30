package pt.up.fe.ldts.pacman.viewer.game;

import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pt.up.fe.ldts.pacman.model.game.Position;
import pt.up.fe.ldts.pacman.model.game.element.ghost.GhostState;
import pt.up.fe.ldts.pacman.model.game.element.ghost.Pinky;
import pt.up.fe.ldts.pacman.viewer.Renderer;
import pt.up.fe.ldts.pacman.viewer.game.strategies.GhostStrategy;

import java.io.IOException;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class GhostViewerTest {
    @Test
    void testDrawElement() throws IOException {
        TextGraphics mockGraphics = Mockito.mock(TextGraphics.class);
        Renderer mockRenderer = Mockito.mock(Renderer.class,withSettings().useConstructor(mockGraphics).defaultAnswer(CALLS_REAL_METHODS));
        MultipleElementViewer a = new MultipleElementViewer(mockRenderer, new GhostStrategy(), ImageLoader.loadGhostImages("pinky"));
        Pinky pinky = new Pinky(new Position(0,0));

        a.drawElement(pinky); //pinky facing left has 158 drawn characters

        verify(mockGraphics,times(158)).putString(any(),any());
        verify(mockGraphics,times(158)).setBackgroundColor(any());

        pinky.setState(GhostState.DEAD);
        a.drawElement(pinky); //pinky dead facing left has 32 drawn characters

        verify(mockGraphics,times(158 + 32)).putString(any(),any());
        verify(mockGraphics,times(158 + 32)).setBackgroundColor(any());
    }
}
