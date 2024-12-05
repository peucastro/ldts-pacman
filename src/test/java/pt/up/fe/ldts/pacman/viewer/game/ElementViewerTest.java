package pt.up.fe.ldts.pacman.viewer.game;

import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pt.up.fe.ldts.pacman.gui.GUI;
import pt.up.fe.ldts.pacman.gui.LanternaGUI;
import pt.up.fe.ldts.pacman.model.game.Position;
import pt.up.fe.ldts.pacman.model.game.element.Wall;
import pt.up.fe.ldts.pacman.model.game.element.collectibles.Cherry;
import pt.up.fe.ldts.pacman.model.game.element.collectibles.Orange;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

class ElementViewerTest {

    @Test
    void testDrawElement() throws IOException, URISyntaxException {
        TextGraphics mockGraphics = Mockito.mock(TextGraphics.class);
        GUI mockLanternaGUI = Mockito.mock(LanternaGUI.class);

        URL cherryResource = ImageLoader.class.getClassLoader().getResource("PNGs/items/cherry.png");
        URL orangeResource = ImageLoader.class.getClassLoader().getResource("PNGs/items/orange.png");
        URL wallResource = ImageLoader.class.getClassLoader().getResource("PNGs/wall.png");

        assert cherryResource != null;
        assert orangeResource != null;
        assert wallResource != null;

        ElementViewer cherryViewer = new ElementViewer(ImageIO.read(new File(cherryResource.toURI())));
        ElementViewer orangeViewer = new ElementViewer(ImageIO.read(new File(orangeResource.toURI())));
        ElementViewer wallViewer = new ElementViewer(ImageIO.read(new File(wallResource.toURI())));

        Cherry cherry = new Cherry(new Position(0, 0));
        Orange orange = new Orange(new Position(0, 0));
        Wall wall = new Wall(new Position(0, 0));

        // Draw Cherry
        cherryViewer.drawElement(mockLanternaGUI, cherry);

        verify(mockLanternaGUI, times(1)).drawImage(any(), any());
        reset(mockLanternaGUI);

        // Draw Orange
        orangeViewer.drawElement(mockLanternaGUI, orange);
        verify(mockLanternaGUI, times(1)).drawImage(any(), any());
        reset(mockLanternaGUI);

        // Draw Wall
        wallViewer.drawElement(mockLanternaGUI, wall);
        verify(mockLanternaGUI, times(1)).drawImage(any(), any());
    }
}
