package pt.up.fe.ldts.pacman.states;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.up.fe.ldts.pacman.Game;
import pt.up.fe.ldts.pacman.audio.AudioManager;
import pt.up.fe.ldts.pacman.controller.Controller;
import pt.up.fe.ldts.pacman.gui.GUI;
import pt.up.fe.ldts.pacman.viewer.Viewer;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class StateTest {

    private State<String> mockState;
    private AudioManager mockAudioManager;
    private Viewer<String> mockViewer;
    private Controller mockController;
    private Game mockGame;
    private GUI mockGui;

    @BeforeEach
    void setUp() throws IOException, URISyntaxException {
        mockAudioManager = mock(AudioManager.class);
        mockViewer = mock(Viewer.class);
        mockController = mock(Controller.class);
        mockGame = mock(Game.class);
        mockGui = mock(GUI.class);

        mockState = new State<String>("Test Model", mockAudioManager) {
            @Override
            public Viewer<String> createViewer() {
                return mockViewer;
            }

            @Override
            public Controller<String> createController(AudioManager audioManager) {
                return mockController;
            }
        };
    }

    @Test
    void testConstructor() throws IOException, URISyntaxException {
        assertNotNull(mockState.getModel());
        assertNotNull(mockState.getAudioManager());
        assertNotNull(mockState.createViewer());
        assertNotNull(mockState.createController(mockAudioManager));
    }

    @Test
    void testStep() throws IOException, URISyntaxException, FontFormatException {
        List<GUI.ACTION> mockActions = mock(List.class);
        when(mockGui.getNextAction()).thenReturn(mockActions);

        mockState.step(mockGame, mockGui, 100L);

        verify(mockController).step(mockGame, mockActions, 100L);
        verify(mockViewer).drawElement(mockGui, "Test Model", 100L);
    }

    @Test
    void testGetAudioManager() {
        assertEquals(mockAudioManager, mockState.getAudioManager());
    }
}
