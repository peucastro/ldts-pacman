package pt.up.fe.ldts.pacman.viewer.game;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.BasicTextImage;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ImageLoaderTests {
    @Test
    void testLoadGhostImages() throws IOException {
        Map<Character, List<BufferedImage>> blinkyImages = ImageLoader.loadGhostImages("blinky");
        Map<Character, List<BufferedImage>> pinkyImages = ImageLoader.loadGhostImages("pinky");
        Map<Character, List<BufferedImage>> inkyImages = ImageLoader.loadGhostImages("inky");
        Map<Character, List<BufferedImage>> clydeImages = ImageLoader.loadGhostImages("clyde");

        //for each ghost, there are 4 images for each side while alive, 4 while dead and 1 while scared in any direction
        assertNotNull(blinkyImages);
        assertEquals(9, blinkyImages.size());
        assertNotNull(pinkyImages);
        assertEquals(9, pinkyImages.size());
        assertNotNull(inkyImages);
        assertEquals(9, inkyImages.size());
        assertNotNull(clydeImages);
        assertEquals(9, clydeImages.size());

    }

    @Test
    void testLoadPacmanImages() throws IOException {
        Map<Character, List<BufferedImage>> images = ImageLoader.loadPacmanImages();

        //4 images one for each direction + 1 when pacman is closed
        assertNotNull(images);
        assertEquals(5, images.size());
    }

    @Test
    void loadImages() throws IOException {
        BufferedImage bufferedImage = ImageLoader.loadBufferedImage("PNGs/wall.png");
        BasicTextImage basicTextImage = ImageLoader.loadTextImage("PNGs/wall.png");

        assertNotNull(bufferedImage);
        assertNotNull(basicTextImage);

        for (int x = 0; x < 11; ++x) {
            for (int y = 0; y < 11; ++y) {
                TextColor color = basicTextImage.getCharacterAt(x, y).getBackgroundColor();
                assertEquals(0xff1c1cb7, bufferedImage.getRGB(x, y));
                assertEquals(0x1c, color.getRed());
                assertEquals(0x1c, color.getGreen());
                assertEquals(0xb7, color.getBlue());
            }
        }
    }

    @Test
    void testFontImages() throws IOException {
        Map<Character, BufferedImage> images = ImageLoader.loadFontImages();

        assertNotNull(images);
        assertEquals(39, images.size());
    }
}
