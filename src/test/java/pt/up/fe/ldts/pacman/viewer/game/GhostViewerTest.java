package pt.up.fe.ldts.pacman.viewer.game;

import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pt.up.fe.ldts.pacman.model.game.Position;
import pt.up.fe.ldts.pacman.model.game.element.Direction;
import pt.up.fe.ldts.pacman.model.game.element.Element;
import pt.up.fe.ldts.pacman.model.game.element.State;
import pt.up.fe.ldts.pacman.model.game.element.ghost.Pinky;
import pt.up.fe.ldts.pacman.viewer.Renderer;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class GhostViewerTest {
    @Test
    void testDrawElement() throws IOException {
        Renderer mockRenderer = Mockito.mock(Renderer.class);
        MultipleElementViewer<Element> a = new MultipleElementViewer<>(mockRenderer, new HashMap<>(Map.ofEntries(
                Map.entry(Direction.LEFT, ImageIO.read(new File("src/main/resources/PNGs/ghosts/pinky/pinkyup.png"))))));
        TextGraphics mockLeft = Mockito.mock(TextGraphics.class);
        TextGraphics mockScared = Mockito.mock(TextGraphics.class);
        Pinky pinky = new Pinky(new Position(0,0));

        a.drawElement(mockLeft,pinky);
        pinky.setState(State.SCARED);
        a.drawElement(mockScared,pinky);

        verify(mockLeft,times(158)).putString(any(),any());
        verify(mockLeft,times(158)).setBackgroundColor(any());
        verify(mockScared,times(158)).putString(any(),any());
        verify(mockScared,times(158)).setBackgroundColor(any());
    }
}
