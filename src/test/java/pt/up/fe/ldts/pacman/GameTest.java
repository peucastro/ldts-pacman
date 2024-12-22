package pt.up.fe.ldts.pacman;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.up.fe.ldts.pacman.audio.AudioManager;
import pt.up.fe.ldts.pacman.gui.GUI;
import pt.up.fe.ldts.pacman.gui.LanternaGUI;
import pt.up.fe.ldts.pacman.states.game.GameState;
import pt.up.fe.ldts.pacman.states.menu.MainMenuState;

import java.awt.*;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GameTest {
    private static Game game;
    private AudioManager audioManager;
    private LanternaGUI mockGUI;

    @BeforeEach
    void setUp() throws IOException, URISyntaxException, FontFormatException, NoSuchFieldException, IllegalAccessException {
        game = Game.getInstance();
        game.getGui().close();
        audioManager = MockAudio.getMockAudioManager();
        mockGUI = mock(LanternaGUI.class);

        Field privateField1 = Game.class.getDeclaredField("audioManager");
        privateField1.setAccessible(true);
        privateField1.set(game, audioManager);

        Field privateField2 = Game.class.getDeclaredField("gui");
        privateField2.setAccessible(true);
        privateField2.set(game, mockGUI);
    }

    @AfterAll
    static void cleanUp(){
        game.setState(null);
    }

    @Test
    void testGetGUI(){
        assertEquals(mockGUI, game.getGui());
    }

    @Test
    void testGetInitialState(){
        assertInstanceOf(MainMenuState.class, game.getState());
    }

    @Test
    void testGetResolution(){
        assertEquals(mockGUI.getResolution(), game.getResolution());
    }

    @Test
    void testSetState(){
        GameState gameState = mock(GameState.class);
        game.setState(gameState);

        assertEquals(gameState, game.getState());
    }

    @Test
    void testSetResolution() throws URISyntaxException, IOException, FontFormatException {
        when(mockGUI.getResolution()).thenReturn(GUI.SCREEN_RESOLUTION._360p);
        game.setResolution(GUI.SCREEN_RESOLUTION._360p);

        verify(mockGUI, times(1)).resizeScreen(anyInt(),anyInt(),any());
        assertEquals(GUI.SCREEN_RESOLUTION._360p, game.getResolution());
    }
}
