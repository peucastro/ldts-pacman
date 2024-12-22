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
import pt.up.fe.ldts.pacman.audio.AudioPlayer;

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
    private LanternaGUI mockGUI;

    @BeforeEach
    void setUp() throws IOException, URISyntaxException, FontFormatException, NoSuchFieldException, IllegalAccessException {
        Field gameField = Game.class.getDeclaredField("instance");
        gameField.setAccessible(true);
        gameField.set(game, null); //set the game instance to null

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
        when(mockGUI.getResolution()).thenReturn(GUI.SCREEN_RESOLUTION._360p);
        game.setResolution(GUI.SCREEN_RESOLUTION._360p);

        verify(mockGUI, times(1)).resizeScreen(anyInt(),anyInt(),any());
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

        String[] args = {};
        Game.main(args);

        verify(audioManager, times(1)).setMainMusic(any());
    }

    @Test
    void testExitGame() throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        List<GUI.ACTION> actions = new ArrayList<>();
        actions.add(GUI.ACTION.DOWN); actions.add(GUI.ACTION.DOWN); //go down
        actions.add(GUI.ACTION.DOWN); actions.add(GUI.ACTION.DOWN); //to exit
        actions.add(GUI.ACTION.SELECT); //select exit
        when(mockGUI.getNextAction()).thenReturn(actions);

        String[] args = {};
        Game.main(args);

        assertNull(game.getState());
        verify(mockGUI, times(1)).close();
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

        String[] args = {};
        RuntimeException exception = assertThrows(RuntimeException.class, () -> Game.main(args));
        assertEquals("java.io.IOException: IO exception", exception.getMessage());
    }

    @Test
    void testSingletonBehavior() throws IOException, URISyntaxException, FontFormatException {
        Game anotherInstance = Game.getInstance();
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
        game.start();
        verify(mockState, atLeastOnce()).step(any(), any(), anyLong());
        assertNull(game.getState());
    }

    @Test
    void testCleanup() throws IOException {
        AudioPlayer mockAudioPlayer = mock(AudioPlayer.class);
        game.cleanup(mockAudioPlayer);
        verify(mockAudioPlayer, times(1)).stopPlaying();
        verify(mockGUI, times(1)).close();
    }


}