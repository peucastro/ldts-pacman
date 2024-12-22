package pt.up.fe.ldts.pacman.viewer.game;

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
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

class PacmanViewerTest {
    @Test
    void testDrawElement() throws IOException {
        GUI mockLanternaGUI = Mockito.mock(LanternaGUI.class);

        MovableElementViewer pacmanViewer = new MovableElementViewer(new PacmanStrategy(), ImageLoader.loadPacmanImages());
        Pacman pacman = new Pacman(new Position(0, 0));

        // Draw Pacman (Left)
        pacmanViewer.drawElement(mockLanternaGUI, pacman,0);
        verify(mockLanternaGUI, times(1)).drawImage(any(), (BufferedImage) any());
        reset(mockLanternaGUI);

        // Draw Pacman (Up)
        pacman.setDirection(Direction.UP);
        pacmanViewer.drawElement(mockLanternaGUI, pacman,0);
        verify(mockLanternaGUI, times(1)).drawImage(any(), (BufferedImage) any());
        reset(mockLanternaGUI);

        // Draw Pacman (Down)
        pacman.setDirection(Direction.DOWN);
        pacmanViewer.drawElement(mockLanternaGUI, pacman,0);
        verify(mockLanternaGUI, times(1)).drawImage(any(), (BufferedImage) any());
        reset(mockLanternaGUI);

        // Draw Pacman (Right)
        pacman.setDirection(Direction.RIGHT);
        pacmanViewer.drawElement(mockLanternaGUI, pacman,0);
        verify(mockLanternaGUI, times(1)).drawImage(any(), (BufferedImage) any());
    }

    @Test
    void getDeadImage() throws IOException {
        PacmanStrategy pacmanStrategy = new PacmanStrategy();
        Map<Character, List<BufferedImage>> images = ImageLoader.loadPacmanImages();
        BufferedImage deadImage = images.get('X').getFirst();
        Pacman pacman = new Pacman(new Position(0,0));
        pacman.setDying(true);

        BufferedImage obtainedImage = pacmanStrategy.getCurrentImage(pacman, images, 0);

        assertEquals(deadImage, obtainedImage);
    }

    @Test
    void testAnimation() throws IOException {
        PacmanStrategy pacmanStrategy = new PacmanStrategy();
        Map<Character, List<BufferedImage>> images = ImageLoader.loadPacmanImages();
        BufferedImage pacmanRight = images.get('R').getFirst();
        BufferedImage pacmanClosed = images.get('R').get(1);
        Pacman pacman = new Pacman(new Position(0,0));
        pacman.setDirection(Direction.RIGHT);

        BufferedImage obtainedImage1 = pacmanStrategy.getCurrentImage(pacman, images, 0); //pacman has mouth open
        BufferedImage obtainedImage2 = pacmanStrategy.getCurrentImage(pacman, images, 10); //pacman has mouth closed

        assertEquals(pacmanRight, obtainedImage1);
        assertEquals(pacmanClosed, obtainedImage2);
    }
}
