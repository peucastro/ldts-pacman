package pt.up.fe.ldts.pacman.viewer.game;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pt.up.fe.ldts.pacman.model.game.Arena;
import pt.up.fe.ldts.pacman.model.game.ArenaLoader;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ArenaViewerTest {
    @Test
    void testDrawElementCallCountWithNoMapLoading() throws IOException, URISyntaxException, FontFormatException {
        Arena arena =  new Arena(20,20);
        Renderer mockRenderer = Mockito.mock(Renderer.class);
        ArenaViewer mockArenaViewer = Mockito.mock(ArenaViewer.class,withSettings().useConstructor(mockRenderer,arena).defaultAnswer(CALLS_REAL_METHODS));

        mockArenaViewer.drawElements();

        //if a map in not loaded into arena, the only element present is a Pacman at position (0,0)
        verify(mockArenaViewer,times(1)).drawElement(any());
        verify(mockRenderer,times(1)).drawImage(any(),any());
    }

    @Test
    void testDrawElementCallCountWithMapLoading() throws IOException, URISyntaxException, FontFormatException {
        Arena arena =  new Arena(20,20);
        Renderer mockRenderer = Mockito.mock(Renderer.class);
        new ArenaLoader(arena).loadMap("src/main/resources/Maps/testmap.txt");
        ArenaViewer mockArenaViewer = Mockito.mock(ArenaViewer.class,withSettings().useConstructor(mockRenderer,arena).defaultAnswer(CALLS_REAL_METHODS));

        mockArenaViewer.drawElements();

        //pacman + ghosts + collectibles + walls or 20*20 - 2spaces - 1unkownElement
        verify(mockArenaViewer,times(397)).drawElement(any());
        verify(mockRenderer,times(397)).drawImage(any(),any());
    }
}
