package pt.up.fe.ldts.pacman.viewer.game;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pt.up.fe.ldts.pacman.gui.GUI;
import pt.up.fe.ldts.pacman.gui.LanternaGUI;
import pt.up.fe.ldts.pacman.model.Position;
import pt.up.fe.ldts.pacman.model.game.element.Direction;
import pt.up.fe.ldts.pacman.model.game.element.ghost.Blinky;
import pt.up.fe.ldts.pacman.model.game.element.ghost.Ghost;
import pt.up.fe.ldts.pacman.model.game.element.ghost.GhostState;
import pt.up.fe.ldts.pacman.model.game.element.ghost.Pinky;
import pt.up.fe.ldts.pacman.viewer.game.strategies.GhostStrategy;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

class GhostViewerTest {

    @Test
    void testDrawElement() throws IOException {
        GUI mockLanternaGUI = Mockito.mock(LanternaGUI.class);

        MovableElementViewer ghostViewer = new MovableElementViewer(new GhostStrategy(), ImageLoader.loadGhostImages("pinky"));
        Pinky pinky = new Pinky(new Position(0, 0));

        // Test Pinky Alive
        ghostViewer.drawElement(mockLanternaGUI, pinky,0);
        verify(mockLanternaGUI, times(1)).drawImage(any(), (BufferedImage) any());
        reset(mockLanternaGUI);

        // Test Pinky Dead
        pinky.setState(GhostState.DEAD);
        ghostViewer.drawElement(mockLanternaGUI, pinky,0);
        verify(mockLanternaGUI, times(1)).drawImage(any(), (BufferedImage) any());
    }

    @Test
    void getScaredImage() throws IOException {
        GhostStrategy ghostStrategy = new GhostStrategy();
        Map<Character, List<BufferedImage>> images = ImageLoader.loadGhostImages("blinky");
        BufferedImage scaredImage = images.get('S').getFirst(); //get the example scared image
        Ghost ghost = new Blinky(new Position(0,0));
        ghost.setState(GhostState.SCARED);

        BufferedImage obtainedImage1 = ghostStrategy.getCurrentImage(ghost, images, 0);
        ghost.setDirection(Direction.DOWN);
        BufferedImage obtainedImage2 = ghostStrategy.getCurrentImage(ghost, images, 0);

        assertEquals(scaredImage, obtainedImage1);
        assertEquals(scaredImage, obtainedImage2);
    }

    @Test
    void getDeadImage() throws IOException {
        GhostStrategy ghostStrategy = new GhostStrategy();
        Map<Character, List<BufferedImage>> images = ImageLoader.loadGhostImages("blinky");
        BufferedImage deadImageRight = images.get('r').getFirst();
        BufferedImage deadImageLeft = images.get('l').getFirst();
        BufferedImage deadImageUp = images.get('u').getFirst();
        BufferedImage deadImageDown = images.get('d').getFirst();
        Ghost ghost = new Blinky(new Position(0,0));
        ghost.setState(GhostState.DEAD);

        //dead images in all directions
        ghost.setDirection(Direction.RIGHT);
        BufferedImage obtainedImage1 = ghostStrategy.getCurrentImage(ghost, images, 0);
        ghost.setDirection(Direction.LEFT);
        BufferedImage obtainedImage2 = ghostStrategy.getCurrentImage(ghost, images, 0);
        ghost.setDirection(Direction.UP);
        BufferedImage obtainedImage3 = ghostStrategy.getCurrentImage(ghost, images, 0);
        ghost.setDirection(Direction.DOWN);
        BufferedImage obtainedImage4 = ghostStrategy.getCurrentImage(ghost, images, 0);

        assertEquals(deadImageRight, obtainedImage1);
        assertEquals(deadImageLeft, obtainedImage2);
        assertEquals(deadImageUp, obtainedImage3);
        assertEquals(deadImageDown, obtainedImage4);
    }

    @Test
    void testAnimation() throws IOException {
        GhostStrategy ghostStrategy = new GhostStrategy();
        Map<Character, List<BufferedImage>> images = ImageLoader.loadGhostImages("blinky");
        BufferedImage frame1 = images.get('R').getFirst();
        BufferedImage frame2 = images.get('R').get(1);
        Ghost ghost = new Blinky(new Position(0,0));
        ghost.setDirection(Direction.RIGHT);

        BufferedImage obtainedImage1 = ghostStrategy.getCurrentImage(ghost, images, 5); //animation frame 1
        BufferedImage obtainedImage2 = ghostStrategy.getCurrentImage(ghost, images, 10); //animation frame 2

        assertEquals(frame1, obtainedImage1);
        assertEquals(frame2, obtainedImage2);
    }
}
