package pt.up.fe.ldts.pacman.viewer;

import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pt.up.fe.ldts.pacman.model.game.Position;
import pt.up.fe.ldts.pacman.viewer.game.ElementViewer;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class ViewerTest {
    @Test
    void testLanternaDependency() throws IOException {
        Viewer a = new ElementViewer("src/main/resources/PNGs/wall.png");
        TextGraphics mock = Mockito.mock(TextGraphics.class);

        a.draw(mock,new Position(0,0),ImageIO.read(new File("src/main/resources/PNGs/wall.png")));

        verify(mock,times(14*14)).putString(any(),any());
        verify(mock,times(14*14 )).setBackgroundColor(any());
    }
}
