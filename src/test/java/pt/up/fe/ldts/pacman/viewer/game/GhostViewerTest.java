package pt.up.fe.ldts.pacman.viewer.game;

import com.googlecode.lanterna.graphics.BasicTextImage;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pt.up.fe.ldts.pacman.gui.GUI;
import pt.up.fe.ldts.pacman.gui.LanternaGUI;
import pt.up.fe.ldts.pacman.model.Position;
import pt.up.fe.ldts.pacman.model.game.element.ghost.GhostState;
import pt.up.fe.ldts.pacman.model.game.element.ghost.Pinky;
import pt.up.fe.ldts.pacman.viewer.game.strategies.GhostStrategy;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

class GhostViewerTest {

    @Test
    void testDrawElement() throws IOException, URISyntaxException {
        TextGraphics mockGraphics = Mockito.mock(TextGraphics.class);
        GUI mockLanternaGUI = Mockito.mock(LanternaGUI.class);

        MultipleElementViewer ghostViewer = new MultipleElementViewer(new GhostStrategy(), ImageLoader.loadGhostImages("pinky"));
        Pinky pinky = new Pinky(new Position(0, 0));

        // Test Pinky Alive
        ghostViewer.drawElement(mockLanternaGUI, pinky);
        verify(mockLanternaGUI, times(1)).drawImage((Position) any(), (BufferedImage) any());
        reset(mockLanternaGUI);

        // Test Pinky Dead
        pinky.setState(GhostState.DEAD);
        ghostViewer.drawElement(mockLanternaGUI, pinky);
        verify(mockLanternaGUI, times(1)).drawImage((Position) any(), (BufferedImage) any());
    }
}
