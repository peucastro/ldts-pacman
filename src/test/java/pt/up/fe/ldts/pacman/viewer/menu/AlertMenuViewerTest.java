package pt.up.fe.ldts.pacman.viewer.menu;

import com.googlecode.lanterna.graphics.BasicTextImage;
import org.junit.jupiter.api.Test;
import pt.up.fe.ldts.pacman.gui.GUI;
import pt.up.fe.ldts.pacman.gui.LanternaGUI;
import pt.up.fe.ldts.pacman.model.Position;
import pt.up.fe.ldts.pacman.model.game.Arena;
import pt.up.fe.ldts.pacman.model.game.ArenaLoader;
import pt.up.fe.ldts.pacman.model.game.element.Direction;
import pt.up.fe.ldts.pacman.model.game.element.GhostGate;
import pt.up.fe.ldts.pacman.model.game.element.pacman.Pacman;
import pt.up.fe.ldts.pacman.model.menu.AlertMenu;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.*;

public class AlertMenuViewerTest {
    @Test
    void testDrawElementCallCountWithNoMapLoading() throws IOException {
        GUI mockGUI = mock(LanternaGUI.class);
        Arena mockArena = mock(Arena.class);

        when(mockArena.getWidth()).thenReturn(29);
        when(mockArena.getHeight()).thenReturn(16);
        when(mockArena.getWalls()).thenReturn(Set.of());
        when(mockArena.getCollectibles()).thenReturn(Set.of());
        when(mockArena.getGhosts()).thenReturn(Set.of());
        when(mockArena.getBlankPositions()).thenReturn(Set.of());

        GhostGate mockGhostGate = mock(GhostGate.class);
        when(mockGhostGate.getPosition()).thenReturn(new Position(5, 5));
        when(mockArena.getGhostGate()).thenReturn(mockGhostGate);

        Pacman mockPacman = mock(Pacman.class);
        when(mockPacman.getDirection()).thenReturn(Direction.RIGHT);
        when(mockPacman.getPosition()).thenReturn(new Position(10, 10));
        when(mockArena.getPacmans()).thenReturn(new ArrayList<>(List.of(mockPacman)));


        AlertMenu alertMenu = new AlertMenu(mockArena, "PNGs/gameover.png");
        AlertMenuViewer alertMenuViewer = new AlertMenuViewer("PNGs/gameover.png");


        alertMenuViewer.drawElement(mockGUI, alertMenu, 0);


        //7 times for the score + 7 times for the lives + (9 + 14) for each option respectively = 37
        verify(mockGUI, times(37)).drawCharacter(any(), any(), any());
        //game over image, 77 by 37 pixels, in position (121,44) to be centered
        verify(mockGUI, times(1)).drawImage(eq(new Position(121, 44)), any(), eq(77), eq(37));
        //no elements except the ghost gate and one pacman
        verify(mockGUI, times(1)).drawImage(any(), (BufferedImage) any());
        verify(mockGUI, times(1)).drawImage(any(), (BasicTextImage) any());
        //no positions erased
        verify(mockGUI, times(0)).erase(any());
    }

    @Test
    void testDrawElementCallCountWithMapLoadingSingleplayer() throws IOException {
        Arena arena = new Arena(29, 16);
        GUI mockGUI = mock(LanternaGUI.class);

        // Simulate map loading
        new ArenaLoader(arena).loadMap("src/main/resources/Maps/singleplayer/1 Normal Map.txt");

        AlertMenu alertMenu = new AlertMenu(arena, "PNGs/gameover.png");
        AlertMenuViewer alertMenuViewer = new AlertMenuViewer("PNGs/gameover.png");


        alertMenuViewer.drawElement(mockGUI, alertMenu, 0);


        //7 times for the score + 7 times for the lives + (9 + 14) for each option respectively = 37
        verify(mockGUI, times(37)).drawCharacter(any(), any(), any());
        //game over image, 77 by 37 pixels, in position (121,44) to be centered
        verify(mockGUI, times(1)).drawImage(eq(new Position(121, 44)), any(), eq(77), eq(37));
        // Verify the number of movable elements drawn (total number of movable elements = 5 (pacman + ghosts))
        verify(mockGUI, times(5)).drawImage(any(), (BufferedImage) any());
        // Verify the number of static elements drawn (total number of static elements = 29*16 - 5 (movables) - 2 (empty spaces))
        verify(mockGUI, times(29 * 16 - 5 - 2)).drawImage(any(), (BasicTextImage) any());
        //erase the movable elements' positions and the empty spaces
        verify(mockGUI, times(5 + 2)).erase(any());
        for (Position position : arena.getBlankPositions())
            verify(mockGUI).erase(new Position(position.getX() * 11, position.getY() * 11));
    }

    @Test
    void testDrawElementCallCountWithMapLoadingMultiplayer() throws IOException {
        Arena arena = new Arena(29, 16);
        GUI mockGUI = mock(LanternaGUI.class);

        // Simulate map loading
        new ArenaLoader(arena).loadMap("src/main/resources/Maps/multiplayer/1 Normal Map.txt");

        AlertMenu alertMenu = new AlertMenu(arena, "PNGs/youwin.png");
        AlertMenuViewer alertMenuViewer = new AlertMenuViewer("PNGs/youwin.png");


        alertMenuViewer.drawElement(mockGUI, alertMenu, 0);


        //7 times for the score + 9*2 times for the lives of each player + (9 + 14) for each option respectively = 48
        verify(mockGUI, times(48)).drawCharacter(any(), any(), any());
        //you win image, 200 by 34 pixels, in position (59, 44) to be centered
        verify(mockGUI, times(1)).drawImage(eq(new Position(59, 44)), any(), eq(200), eq(34));
        // Verify the number of movable elements drawn (total number of movable elements = 6 (2 pacman + 4 ghosts))
        verify(mockGUI, times(6)).drawImage(any(), (BufferedImage) any());
        // Verify the number of static elements drawn (total number of static elements = 29*16 - 6 (movables) - 2 (empty spaces))
        verify(mockGUI, times(29 * 16 - 6 - 2)).drawImage(any(), (BasicTextImage) any());
        //erase the movable elements' positions and the empty spaces
        verify(mockGUI, times(6 + 2)).erase(any());
        for (Position position : arena.getBlankPositions())
            verify(mockGUI).erase(new Position(position.getX() * 11, position.getY() * 11));
    }
}