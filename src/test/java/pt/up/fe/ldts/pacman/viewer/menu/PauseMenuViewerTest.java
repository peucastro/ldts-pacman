package pt.up.fe.ldts.pacman.viewer.menu;

import org.junit.jupiter.api.Test;
import pt.up.fe.ldts.pacman.gui.GUI;
import pt.up.fe.ldts.pacman.model.menu.PauseMenu;
import pt.up.fe.ldts.pacman.states.State;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class PauseMenuViewerTest {
    @Test
    void testDrawElementCallCount() throws IOException {
        GUI mockGUI = mock(GUI.class);
        State<?> mockState = mock(State.class);
        PauseMenu pauseMenu = new PauseMenu(mockState, GUI.SCREEN_RESOLUTION._900p, 0);
        PauseMenuViewer pauseMenuViewer = new PauseMenuViewer();


        pauseMenuViewer.drawElement(mockGUI, pauseMenu, 0);


        //5 times for the title + 2 times for the pause sign + (6 + 15 + 15 + 14) for each option respectively = 57
        verify(mockGUI, times(57)).drawCharacter(any(), any(), any());
    }
}
