package pt.up.fe.ldts.pacman.viewer.game;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import pt.up.fe.ldts.pacman.model.game.Arena;
import pt.up.fe.ldts.pacman.model.game.ArenaLoader;
import pt.up.fe.ldts.pacman.model.game.element.pacman.Pacman;
import pt.up.fe.ldts.pacman.viewer.MultipleElementViewerTest;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URISyntaxException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ArenaViewerTest {
    @Test
    void testDrawElementCallCountWithNoMapLoading() throws IOException, URISyntaxException, FontFormatException {
        Arena arena =  new Arena(20,20);
        ArenaViewer mock = Mockito.mock(ArenaViewer.class,withSettings().useConstructor(arena).defaultAnswer(CALLS_REAL_METHODS));
        TextGraphics graphics =  new Display(new TerminalSize(20,20)).getScreen().newTextGraphics();

        mock.drawElements(graphics);

        //if a map in not loaded into arena, the only element present is a Pacman at position (0,0)
        verify(mock,times(1)).drawElement(any(),any());
    }

    @Test
    void testDrawElementCallCountWithMapLoading() throws IOException, URISyntaxException, FontFormatException {
        Arena arena =  new Arena(20,20);
        new ArenaLoader(arena).loadMap("src/main/resources/Maps/testmap.txt");
        ArenaViewer mock = Mockito.mock(ArenaViewer.class,withSettings().useConstructor(arena).defaultAnswer(CALLS_REAL_METHODS));
        TextGraphics graphics =  new Display(new TerminalSize(20,20)).getScreen().newTextGraphics();

        mock.drawElements(graphics);

        //pacman + ghosts + collectibles + walls or 20*20 - 2spaces - 1unkownElement
        verify(mock,times(397)).drawElement(any(),any());
    }
}
