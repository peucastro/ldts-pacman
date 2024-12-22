package pt.up.fe.ldts.pacman;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.up.fe.ldts.pacman.audio.AudioManager;
import pt.up.fe.ldts.pacman.gui.GUI;
import pt.up.fe.ldts.pacman.gui.LanternaGUI;
import pt.up.fe.ldts.pacman.model.menu.MainMenu;
import pt.up.fe.ldts.pacman.states.State;
import pt.up.fe.ldts.pacman.states.game.GameState;
import pt.up.fe.ldts.pacman.states.menu.MainMenuState;

import java.awt.*;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GameTest {
    private static Game game;
    private AudioManager audioManager;
    private LanternaGUI gui;

    @BeforeEach
    void setUp() throws IOException, URISyntaxException, FontFormatException, NoSuchFieldException, IllegalAccessException {
        Field gameField = Game.class.getDeclaredField("instance");
        gameField.setAccessible(true);
        gameField.set(game, null); //set the game instance to null

        audioManager = MockAudio.getMockAudioManager();
        gui = mock(LanternaGUI.class);
        game = Game.getInstance(gui, audioManager);

        Field privateField1 = Game.class.getDeclaredField("audioManager");
        privateField1.setAccessible(true);
        privateField1.set(game, audioManager);

        Field privateField2 = Game.class.getDeclaredField("gui");
        privateField2.setAccessible(true);
        privateField2.set(game, gui);
    }

    @Test
    void testGetGUI(){
        assertEquals(gui, game.getGui());
    }

    @Test
    void testGetInitialState(){
        assertInstanceOf(MainMenuState.class, game.getState());
    }

    @Test
    void testGetResolution(){
        assertEquals(gui.getResolution(), game.getResolution());
    }

    @Test
    void testGetAudioManager(){
        assertEquals(audioManager, game.getAudioManager());
    }

    @Test
    void testSetState(){
        GameState gameState = mock(GameState.class);
        game.setState(gameState);

        assertEquals(gameState, game.getState());
    }

    @Test
    void testSetResolution() throws URISyntaxException, IOException, FontFormatException {
        when(gui.getResolution()).thenReturn(GUI.SCREEN_RESOLUTION._360p);
        game.setResolution(GUI.SCREEN_RESOLUTION._360p);

        verify(gui, times(1)).resizeScreen(anyInt(),anyInt(),any());
        assertEquals(GUI.SCREEN_RESOLUTION._360p, game.getResolution());
    }

    @Test
    void testInitializeMusic() throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        MainMenuState mockState = new MainMenuState(mock(MainMenu.class),audioManager){
            @Override
            public void step(Game game, GUI gui, long frameTime) {
                game.setState(null);
            }
        };
        game.setState(mockState);

        Game.main(gui, audioManager);

        verify(audioManager, times(1)).setMainMusic(any());
    }

    @Test
    void testExitGame() throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        List<GUI.ACTION> actions = new ArrayList<>();
        actions.add(GUI.ACTION.DOWN); actions.add(GUI.ACTION.DOWN); //go down
        actions.add(GUI.ACTION.DOWN); actions.add(GUI.ACTION.DOWN); //to exit
        actions.add(GUI.ACTION.SELECT); //select exit
        when(gui.getNextAction()).thenReturn(actions);

        Game.main(gui, audioManager);

        assertNull(game.getState());
        verify(gui, times(1)).close();
    }

    @Test
    void testThrownException() throws IOException, URISyntaxException {
        MainMenuState mockState = new MainMenuState(mock(MainMenu.class),audioManager){
            @Override
            public void step(Game game, GUI gui, long frameTime) throws IOException {
                throw new IOException("IO exception");
            }
        };
        game.setState(mockState);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> Game.main(gui, audioManager));
        assertEquals("java.io.IOException: IO exception", exception.getMessage());
    }

    @Test
    void testSingletonBehavior() throws IOException, URISyntaxException, FontFormatException {
        Game anotherInstance = Game.getInstance(gui, audioManager);
        assertSame(game, anotherInstance);
    }
    @Test
    void testStartGameLoop() throws IOException, InterruptedException, FontFormatException, URISyntaxException {
        State mockState = mock(State.class);
        game.setState(mockState);
        doAnswer(invocation -> {
            game.setState(null);
            return null;
        }).when(mockState).step(any(), any(), anyLong());

        Game.main(gui, audioManager);

        verify(mockState, atLeastOnce()).step(any(), any(), anyLong());
        assertNull(game.getState());
    }

    @Test
    void testCleanup() throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        State mockState = mock(State.class);
        game.setState(mockState);
        doAnswer(invocation -> {
            game.setState(null);
            return null;
        }).when(mockState).step(any(), any(), anyLong());

        Game.main(gui, audioManager);;

        verify(gui, times(1)).close();
    }


}