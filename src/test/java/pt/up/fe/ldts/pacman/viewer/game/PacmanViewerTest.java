package pt.up.fe.ldts.pacman.viewer.game;

import com.googlecode.lanterna.graphics.BasicTextImage;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pt.up.fe.ldts.pacman.gui.GUI;
import pt.up.fe.ldts.pacman.gui.LanternaGUI;
import pt.up.fe.ldts.pacman.model.Position;
import pt.up.fe.ldts.pacman.model.game.element.Direction;
import pt.up.fe.ldts.pacman.model.game.element.pacman.Pacman;
import pt.up.fe.ldts.pacman.viewer.game.strategies.PacmanStrategy;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

class PacmanViewerTest {

    @Test
    void testDrawElement() throws IOException, URISyntaxException {
        GUI mockLanternaGUI = Mockito.mock(LanternaGUI.class);

        MultipleElementViewer pacmanViewer = new MultipleElementViewer(new PacmanStrategy(), ImageLoader.loadPacmanImages());
        Pacman pacman = new Pacman(new Position(0, 0));

        // Draw Pacman (Left)
        pacmanViewer.drawElement(mockLanternaGUI, pacman);
        verify(mockLanternaGUI, times(1)).drawImage((Position) any(), (BufferedImage) any());
        reset(mockLanternaGUI);

        // Draw Pacman (Up)
        pacman.setDirection(Direction.UP);
        pacmanViewer.drawElement(mockLanternaGUI, pacman);
        verify(mockLanternaGUI, times(1)).drawImage((Position) any(), (BufferedImage) any());
    }
}
