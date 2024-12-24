package pt.up.fe.ldts.pacman.viewer.menu;

import com.googlecode.lanterna.graphics.BasicTextImage;
import org.junit.jupiter.api.Test;
import pt.up.fe.ldts.pacman.gui.GUI;
import pt.up.fe.ldts.pacman.model.Position;
import pt.up.fe.ldts.pacman.model.menu.MainMenu;

import java.awt.image.BufferedImage;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class MainMenuViewerTest {
    @Test
    void testDrawElementCallCount() throws IOException {
        GUI mockGUI = mock(GUI.class);
        MainMenu mainMenu = new MainMenu(GUI.SCREEN_RESOLUTION._900p, 0);
        MainMenuViewer mainMenuViewer = new MainMenuViewer();


        mainMenuViewer.drawElement(mockGUI, mainMenu, 0);


        //6 times for the title + (12 + 11 + 15 + 14 + 4) for each option respectively = 62
        verify(mockGUI, times(62)).drawCharacter(any(), any(), any());
        //erased positions: 5 for blinky + 6 for inky + 8 for pinky + 8 for clyde = 27
        verify(mockGUI, times(27)).erase(any());
        //4 one for each ghost
        verify(mockGUI, times(4)).drawImage(any(), (BufferedImage) any());
        //one for pacman
        verify(mockGUI, times(1)).drawImage(any(), (BasicTextImage) any());
        for (Position position : mainMenu.getBlankPositions())
            verify(mockGUI).erase(new Position(position.getX() * 11, position.getY() * 11));
    }
}