package pt.up.fe.ldts.pacman.viewer.game;

import org.junit.jupiter.api.Test;
import pt.up.fe.ldts.pacman.gui.GUI;
import pt.up.fe.ldts.pacman.gui.LanternaGUI;
import pt.up.fe.ldts.pacman.model.game.Arena;
import pt.up.fe.ldts.pacman.model.game.ArenaLoader;
import pt.up.fe.ldts.pacman.model.game.Position;
import pt.up.fe.ldts.pacman.model.game.element.pacman.Pacman;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ArenaViewerTest {
    @Test
    void testDrawElementCallCountWithNoMapLoading() throws IOException, URISyntaxException {
        Arena mockArena = mock(Arena.class);
        GUI mockGUI = mock(LanternaGUI.class);

        when(mockArena.getWalls()).thenReturn(Set.of());
        when(mockArena.getCollectibles()).thenReturn(Set.of());
        when(mockArena.getGhosts()).thenReturn(Set.of());
        when(mockArena.getPacman()).thenReturn(new Pacman(new Position(0, 0)));

        ArenaViewer arenaViewer = new ArenaViewer();
        arenaViewer.drawElements(mockGUI, mockArena);

        // Verifies a Pacman is drawn
        verify(mockGUI, times(1)).drawImage(any(), any());
    }

    @Test
    void testDrawElementCallCountWithMapLoading() throws IOException, URISyntaxException {
        Arena arena = new Arena(20, 20);
        GUI mockGUI = mock(LanternaGUI.class);

        // Simulate map loading
        new ArenaLoader(arena).loadMap("src/main/resources/Maps/map.txt");
        ArenaViewer arenaViewer = new ArenaViewer();
        arenaViewer.drawElements(mockGUI, arena);

        // Verify the number of elements drawn (total number of elements = 20*20 - 2 empty spaces)
        verify(mockGUI, times(20 * 20 - 2)).drawImage(any(), any());
    }
}
