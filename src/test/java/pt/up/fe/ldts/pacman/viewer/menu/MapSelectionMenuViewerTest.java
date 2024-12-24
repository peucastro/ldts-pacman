package pt.up.fe.ldts.pacman.viewer.menu;

import org.junit.jupiter.api.Test;
import pt.up.fe.ldts.pacman.gui.GUI;
import pt.up.fe.ldts.pacman.model.menu.MapSelectionMenu;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class MapSelectionMenuViewerTest {
    @Test
    void testDrawElementCallCount() throws IOException {
        GUI mockGUI = mock(GUI.class);
        MapSelectionMenu mapSelectionMenu = new MapSelectionMenu("singleplayer");
        MapSelectionMenuViewer mapSelectionMenuViewer = new MapSelectionMenuViewer();


        mapSelectionMenuViewer.drawElement(mockGUI, mapSelectionMenu, 0);


        //12 times for the title + (10 + 8 + 8 + 8 + 10) for each option respectively = 56
        verify(mockGUI, times(56)).drawCharacter(any(), any(), any());
    }
}