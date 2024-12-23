package pt.up.fe.ldts.pacman;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.up.fe.ldts.pacman.audio.AudioManager;
import pt.up.fe.ldts.pacman.audio.AudioPlayer;
import pt.up.fe.ldts.pacman.gui.GUI;
import pt.up.fe.ldts.pacman.gui.LanternaGUI;
import pt.up.fe.ldts.pacman.model.menu.MainMenu;
import pt.up.fe.ldts.pacman.states.State;
import pt.up.fe.ldts.pacman.states.game.GameState;
import pt.up.fe.ldts.pacman.states.menu.MainMenuState;

import java.awt.*;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GameTest {
    private AudioPlayer mainMusic;
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
    void testGetGUI() {
        assertEquals(gui, game.getGui());
    }

    @Test
    void testGetInitialState() {
        assertInstanceOf(MainMenuState.class, game.getState());
    }

    @Test
    void testGetResolution() {
        when(gui.getResolution()).thenReturn(GUI.SCREEN_RESOLUTION._900p);

        assertEquals(GUI.SCREEN_RESOLUTION._900p, game.getResolution());
    }

    @Test
    void testGetAudioManager() {
        assertEquals(audioManager, game.getAudioManager());
    }

    @Test
    void testSetState() {
        GameState gameState = mock(GameState.class);
        game.setState(gameState);

        assertEquals(gameState, game.getState());
    }

    @Test
    void testSetResolution() throws URISyntaxException, IOException, FontFormatException {
        when(gui.getResolution()).thenReturn(GUI.SCREEN_RESOLUTION._360p);
        game.setResolution(GUI.SCREEN_RESOLUTION._360p);

        verify(gui, times(1)).resizeScreen(anyInt(), anyInt(), any());
        assertEquals(GUI.SCREEN_RESOLUTION._360p, game.getResolution());
    }

    @Test
    void testInitializeMusic() throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        MainMenuState mockState = new MainMenuState(mock(MainMenu.class), audioManager) {
            @Override
            public void step(Game game, GUI gui, long frameTime) {
                game.setState(null);
            }
        };

        doAnswer(invocationOnMock -> {
            AudioPlayer mainMusic = invocationOnMock.getArgument(0);
            assertEquals(0.05f, mainMusic.getVolume()); //assert the volume was set
            assertTrue(mainMusic.isPlaying()); //assert main music is playing
            return null;
        }).when(audioManager).setMainMusic(any());

        game.setState(mockState);

        Game.main(gui, audioManager);

        verify(audioManager, times(1)).setMainMusic(any());
    }

    @Test
    void testExitGame() throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        List<GUI.ACTION> actions = new ArrayList<>();
        actions.add(GUI.ACTION.DOWN);
        actions.add(GUI.ACTION.DOWN); //go down
        actions.add(GUI.ACTION.DOWN);
        actions.add(GUI.ACTION.DOWN); //to exit
        actions.add(GUI.ACTION.SELECT); //select exit
        when(gui.getNextAction()).thenReturn(actions);

        Game.main(gui, audioManager);

        assertNull(game.getState());
        verify(gui, times(1)).close();
    }

    @Test
    void testThrownException() throws IOException, URISyntaxException {
        MainMenuState mockState = new MainMenuState(mock(MainMenu.class), audioManager) {
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
    void testCleanup() throws IOException, URISyntaxException, FontFormatException, InterruptedException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        State mockState = mock(State.class);
        game.setState(mockState);
        mainMusic = mock(AudioPlayer.class);

        doAnswer(invocation -> {
            game.setState(null);
            return null;
        }).when(mockState).step(any(), any(), anyLong());

        Method privateMethod = Game.class.getDeclaredMethod("cleanup", AudioPlayer.class);
        privateMethod.setAccessible(true);


        Game.main(gui, audioManager);

        verify(gui, times(1)).close();


        privateMethod.invoke(game, mainMusic);

        verify(mainMusic).stopPlaying();
    }

    @Test
    void testMasterVolumeSetAtInitialization() {
        verify(audioManager, times(1)).setMasterVolume(1f);
    }

    @Test
    void testGameLoopGetFrameCount() throws InterruptedException {
        GameLoop gameLoop = new GameLoop(60);
        Runnable mockLogic = mock(Runnable.class);

        assertEquals(0, gameLoop.getFrameCount());

        gameLoop.update(mockLogic);

        assertEquals(1, gameLoop.getFrameCount());
    }

    @Test
    void testGameLoopRunnableExecution() throws InterruptedException {
        GameLoop gameLoop = new GameLoop(60);
        Runnable mockLogic = mock(Runnable.class);

        gameLoop.update(mockLogic);
        verify(mockLogic, times(1)).run();
    }

    @Test
    void testThreadSleepInGameLoop() throws InterruptedException {
        GameLoop gameLoop = new GameLoop(60);

        long startTime = System.currentTimeMillis();
        gameLoop.update(() -> {});
        long endTime = System.currentTimeMillis();

        long elapsed = endTime - startTime;
        assertTrue(elapsed >= 16);
    }

    @Test
    void testGameLoopInitialState() throws NoSuchFieldException, IllegalAccessException {
        Field privateField = GameLoop.class.getDeclaredField("frameTime");
        privateField.setAccessible(true);

        GameLoop gameLoop = new GameLoop(60);
        Long frameTime = (Long) privateField.get(gameLoop);

        assertEquals(0, gameLoop.getFrameCount());
        assertEquals(1000/60, frameTime);
    }

    @Test
    void testGameLoopUpdate() throws InterruptedException {
        GameLoop gameLoop = new GameLoop(100); //only 10 milliseconds per frame

        long start = System.currentTimeMillis();
        gameLoop.update(() -> {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        long timeEllapsed = System.currentTimeMillis() - start;


        assertEquals((long) 1,  gameLoop.getFrameCount()); //assert the frame count was incremented
        assertTrue(10 < timeEllapsed); //frame took too long

        start = System.currentTimeMillis();
        gameLoop.update(() -> {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        timeEllapsed = System.currentTimeMillis() - start;

        assertEquals((long) 2, gameLoop.getFrameCount()); //assert the frame count was incremented
        assertTrue(10 <= timeEllapsed); //frame was too fast so it got delayed
    }

    @Test
    void testAudioPlayerSetVolumeAndPlayInLoop() throws Exception {
        AudioManager mockAudioManager = mock(AudioManager.class);
        Field audioManagerField = Game.class.getDeclaredField("audioManager");
        audioManagerField.setAccessible(true);
        audioManagerField.set(game, mockAudioManager);

        Method initializeMusicMethod = Game.class.getDeclaredMethod("initializeMusic");
        initializeMusicMethod.setAccessible(true);
        AudioPlayer result = (AudioPlayer) initializeMusicMethod.invoke(game);

        verify(mockAudioManager, times(1)).setMainMusic(result);

        assertNotNull(result);
        assertEquals(0.05f, result.getVolume(), 0.01f);
        assertTrue(result.isPlaying());
    }

    @Test
    void testFrameCountIncrementsCorrectly() throws InterruptedException {
        GameLoop gameLoop = new GameLoop(60);
        Runnable mockLogic = mock(Runnable.class);

        for (int i = 0; i < 5; i++) {
            gameLoop.update(mockLogic);
        }

        assertEquals(5, gameLoop.getFrameCount());
    }

    @Test
    void testGameLoopTimingWithSubtraction() throws InterruptedException {
        GameLoop gameLoop = new GameLoop(60);

        long startTime = System.currentTimeMillis();
        gameLoop.update(() -> {});
        long elapsedTime = System.currentTimeMillis() - startTime;

        assertTrue(elapsedTime >= 16); // Ensures at least 16 ms (60 FPS)
    }



}