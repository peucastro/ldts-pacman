package pt.up.fe.ldts.pacman.viewer.game;

import com.googlecode.lanterna.graphics.BasicTextImage;
import org.junit.jupiter.api.Test;
import pt.up.fe.ldts.pacman.gui.GUI;
import pt.up.fe.ldts.pacman.gui.LanternaGUI;
import pt.up.fe.ldts.pacman.model.game.element.Direction;
import pt.up.fe.ldts.pacman.model.game.element.GhostGate;
import pt.up.fe.ldts.pacman.model.Position;
import pt.up.fe.ldts.pacman.model.game.Arena;
import pt.up.fe.ldts.pacman.model.game.ArenaLoader;
import pt.up.fe.ldts.pacman.model.game.element.collectibles.Collectible;
import pt.up.fe.ldts.pacman.model.game.element.ghost.Ghost;
import pt.up.fe.ldts.pacman.model.game.element.pacman.Pacman;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.*;

class ArenaViewerTest {

    @Test
    void testDrawElementCallCountWithNoMapLoading() throws IOException, URISyntaxException {
        Arena mockArena = mock(Arena.class);
        GUI mockGUI = mock(LanternaGUI.class);

        when(mockArena.getWalls()).thenReturn(Set.of());
        when(mockArena.getCollectibles()).thenReturn(Set.of());
        when(mockArena.getGhosts()).thenReturn(Set.of());

        GhostGate mockGhostGate = mock(GhostGate.class);
        when(mockGhostGate.getPosition()).thenReturn(new Position(5, 5));
        when(mockArena.getGhostGate()).thenReturn(mockGhostGate);

        Pacman mockPacman = mock(Pacman.class);
        when(mockPacman.getDirection()).thenReturn(Direction.RIGHT);
        when(mockPacman.getPosition()).thenReturn(new Position(10, 10));
        when(mockArena.getPacmans()).thenReturn(new ArrayList<>(List.of(mockPacman)));

        ArenaViewer arenaViewer = new ArenaViewer();
        arenaViewer.drawElements(mockGUI, mockArena, 0);

        // Verify that the drawImage method is called with a valid Position
        verify(mockGUI, times(1)).drawImage(any(), (BufferedImage) any());
    }

    @Test
    void testDrawElementCallCountWithMapLoading() throws IOException, URISyntaxException {
        Arena arena = new Arena(29, 16);
        GUI mockGUI = mock(LanternaGUI.class);

        // Simulate map loading
        new ArenaLoader(arena).loadMap("src/main/resources/Maps/singleplayer/Normal Map.txt");
        ArenaViewer arenaViewer = new ArenaViewer();
        arenaViewer.drawElements(mockGUI, arena,0);

        // Verify the number of static elements drawn (total number of static elements = 29*15 - 5 (movables) - 2 (empty spaces))
        verify(mockGUI, times(29 * 16 - 5 - 2)).drawImage(any(), (BasicTextImage) any());
        // Verify the number of movable elements drawn (total number of movable elements = 5 (pacman + ghosts))
        verify(mockGUI, times(5)).drawImage(any(), (BufferedImage) any());

    }

}
