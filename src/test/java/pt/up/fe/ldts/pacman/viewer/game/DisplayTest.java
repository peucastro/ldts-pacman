package pt.up.fe.ldts.pacman.viewer.game;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.TerminalScreen;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;


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
