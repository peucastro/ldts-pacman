package pt.up.fe.ldts.pacman.viewer.game;

import com.googlecode.lanterna.graphics.BasicTextImage;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pt.up.fe.ldts.pacman.gui.GUI;
import pt.up.fe.ldts.pacman.gui.LanternaGUI;
import pt.up.fe.ldts.pacman.model.Position;
import pt.up.fe.ldts.pacman.model.game.element.Wall;
import pt.up.fe.ldts.pacman.model.game.element.collectibles.Cherry;
import pt.up.fe.ldts.pacman.model.game.element.collectibles.Orange;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

class ElementViewerTest {

    @Test
    void testDrawElement() throws IOException, URISyntaxException {
        GUI mockLanternaGUI = Mockito.mock(LanternaGUI.class);

        URL cherryResource = ImageLoader.class.getClassLoader().getResource("PNGs/items/cherry.png");
        URL orangeResource = ImageLoader.class.getClassLoader().getResource("PNGs/items/orange.png");
        URL wallResource = ImageLoader.class.getClassLoader().getResource("PNGs/wall.png");

        assert cherryResource != null;
        assert orangeResource != null;
        assert wallResource != null;

        BasicTextImage cherryImage = ImageLoader.loadTextImage("PNGs/items/cherry.png");
        BasicTextImage orangeImage = ImageLoader.loadTextImage("PNGs/items/orange.png");
        BasicTextImage wallImage = ImageLoader.loadTextImage("PNGs/wall.png");

        ElementViewer cherryViewer = new ElementViewer(cherryImage);
        ElementViewer orangeViewer = new ElementViewer(orangeImage);
        ElementViewer wallViewer = new ElementViewer(wallImage);

        Cherry cherry = new Cherry(new Position(0, 0));
        Orange orange = new Orange(new Position(0, 0));
        Wall wall = new Wall(new Position(0, 0));

        // Draw Cherry
        cherryViewer.drawElement(mockLanternaGUI, cherry);

        verify(mockLanternaGUI, times(1)).drawImage(any(), (BasicTextImage) any());
        reset(mockLanternaGUI);

        // Draw Orange
        orangeViewer.drawElement(mockLanternaGUI, orange);
        verify(mockLanternaGUI, times(1)).drawImage(any(), (BasicTextImage) any());
        reset(mockLanternaGUI);

        // Draw Wall
        wallViewer.drawElement(mockLanternaGUI, wall);
        verify(mockLanternaGUI, times(1)).drawImage(any(), (BasicTextImage) any());
    }
}
