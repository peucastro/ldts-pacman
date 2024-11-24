package pt.up.fe.ldts.pacman.viewer;

import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pt.up.fe.ldts.pacman.model.game.Position;
import pt.up.fe.ldts.pacman.model.game.element.Direction;
import pt.up.fe.ldts.pacman.model.game.element.State;
import pt.up.fe.ldts.pacman.viewer.game.ElementViewer;
import pt.up.fe.ldts.pacman.viewer.game.PacmanViewer;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class MultipleElementViewerTest {
    @Test
    void testLanternaDependency() throws IOException {
        MultipleElementViewer a = new PacmanViewer(new HashMap<>());
        TextGraphics mock = Mockito.mock(TextGraphics.class);

        a.draw(mock,new Position(0,0), ImageIO.read(new File("src/main/resources/PNGs/pacman/pacmanclosed.png")));

        //number of colored pixels = 14*14 - 32
        verify(mock,times(14*14 - 32)).putString(any(),any());
        verify(mock,times(14*14 - 32)).setBackgroundColor(any());
    }

    @Test
    void testImageMap() throws IOException {
        MultipleElementViewer a = new PacmanViewer(Map.ofEntries(
                Map.entry(Direction.LEFT, ImageIO.read(new File("src/main/resources/PNGs/pacman/pacmanleft.png"))),
                Map.entry(State.SCARED, ImageIO.read(new File("src/main/resources/PNGs/ghosts/common/scaredghost.png")))));

        Assertions.assertTrue(a.images.containsKey(State.SCARED));
        Assertions.assertTrue(a.images.containsKey(Direction.LEFT));
    }
}
