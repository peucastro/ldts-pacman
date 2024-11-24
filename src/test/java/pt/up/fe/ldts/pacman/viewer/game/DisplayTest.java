package pt.up.fe.ldts.pacman.viewer.game;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pt.up.fe.ldts.pacman.model.game.Position;
import pt.up.fe.ldts.pacman.model.game.element.Direction;
import pt.up.fe.ldts.pacman.model.game.element.State;
import pt.up.fe.ldts.pacman.model.game.element.ghost.Pinky;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class DisplayTest {
    @Test
    void testGetScreen() throws IOException, URISyntaxException, FontFormatException {
        Display display = new Display(new TerminalSize(10,10));

        Assertions.assertEquals(TerminalScreen.class,display.getScreen().getClass());
    }

    @Test
    void testNegativeTerminalSize(){
        Assertions.assertThrows(IllegalArgumentException.class,() -> {Display display = new Display(new TerminalSize(-10,10));});
    }
}
