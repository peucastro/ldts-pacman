package pt.up.fe.ldts.pacman.viewer.game;

import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pt.up.fe.ldts.pacman.model.game.Position;
import pt.up.fe.ldts.pacman.model.game.element.Direction;
import pt.up.fe.ldts.pacman.model.game.element.pacman.Pacman;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class PacmanViewerTest {
    @Test
    void testDrawElement() throws IOException {
        PacmanViewer a = new PacmanViewer(Map.ofEntries(
                Map.entry(Direction.LEFT, ImageIO.read(new File("src/main/resources/PNGs/pacman/pacmanleft.png"))),
                Map.entry(Direction.UP, ImageIO.read(new File("src/main/resources/PNGs/pacman/pacmanclosed.png")))));
        TextGraphics mockLeft = Mockito.mock(TextGraphics.class);
        TextGraphics mockClosed = Mockito.mock(TextGraphics.class);
        Pacman pacman = new Pacman(new Position(0,0));

        a.drawElement(mockLeft,pacman);
        pacman.setDirection(Direction.UP);
        a.drawElement(mockClosed,pacman);

        verify(mockLeft,times(136)).putString(any(),any());
        verify(mockLeft,times(136)).setBackgroundColor(any());
        verify(mockClosed,times(164)).putString(any(),any());
        verify(mockClosed,times(164)).setBackgroundColor(any());
    }
}
