package pt.up.fe.ldts.pacman.viewer.game;

import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pt.up.fe.ldts.pacman.model.game.Position;
import pt.up.fe.ldts.pacman.model.game.element.Direction;
import pt.up.fe.ldts.pacman.model.game.element.pacman.Pacman;
import pt.up.fe.ldts.pacman.viewer.Renderer;
import pt.up.fe.ldts.pacman.viewer.game.strategies.PacmanStrategy;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class PacmanViewerTest {
    @Test
    void testDrawElement() throws IOException {
        TextGraphics mockGraphics = Mockito.mock(TextGraphics.class);
        Renderer mockRenderer = Mockito.mock(Renderer.class,withSettings().useConstructor(mockGraphics).defaultAnswer(CALLS_REAL_METHODS));
        MultipleElementViewer a = new MultipleElementViewer(mockRenderer, new PacmanStrategy(), ImageLoader.loadPacmanImages());
        Pacman pacman = new Pacman(new Position(0,0));

        a.drawElement(pacman); //pacman facing left has 136 drawn characters

        verify(mockGraphics,times(136)).putString(any(),any());
        verify(mockGraphics,times(136)).setBackgroundColor(any());

        pacman.setDirection(Direction.UP);//pacman facing up also has 136 drawn characters
        a.drawElement(pacman);

        verify(mockGraphics,times(136 + 136)).putString(any(),any());
        verify(mockGraphics,times(136 + 136)).setBackgroundColor(any());
    }
}
