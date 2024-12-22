package pt.up.fe.ldts.pacman.controller.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.up.fe.ldts.pacman.Game;
import pt.up.fe.ldts.pacman.MockAudio;
import pt.up.fe.ldts.pacman.audio.AudioManager;
import pt.up.fe.ldts.pacman.model.Position;
import pt.up.fe.ldts.pacman.model.game.Arena;
import pt.up.fe.ldts.pacman.model.game.element.ghost.Blinky;
import pt.up.fe.ldts.pacman.model.game.element.ghost.GhostState;
import pt.up.fe.ldts.pacman.model.game.element.pacman.Pacman;
import pt.up.fe.ldts.pacman.states.game.GameState;
import pt.up.fe.ldts.pacman.states.menu.AlertMenuState;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DyingStateControllerTest {
    private DyingStateController controller;
    private Arena arena;
    private AudioManager audioManager;
    private Game game;

    @BeforeEach
    void setUp() {
        arena = mock(Arena.class);
        audioManager = MockAudio.getMockAudioManager();
        game = mock(Game.class);
        when(game.getAudioManager()).thenReturn(audioManager);

        controller = new DyingStateController(arena, audioManager);
    }

    @Test
    void returnToGame() throws IOException, URISyntaxException {
        Pacman pacman = new Pacman(new Position(0,0));
        pacman.setRespawnPosition(new Position(20,20));
        pacman.setSpeed(Arena.PACMAN_BOOSTED_SPEED);
        when(arena.getPacmans()).thenReturn(List.of(pacman));

        Blinky blinky = new Blinky(new Position(0,0));
        blinky.setRespawnPosition(new Position(10,10));
        blinky.setState(GhostState.DEAD);
        blinky.setSpeed(Arena.GHOST_DEAD_SPEED);
        when(arena.getGhosts()).thenReturn(Set.of(blinky));

        for (int i = 0; i < 109; ++i) controller.step(game, List.of(), 0);

        //one frame before returning to the game
        verify(game, times(0)).setState(any());

        controller.step(game, List.of(), 0);

        //return to the game
        verify(game, times(1)).setState(any(GameState.class));

        assertEquals(GhostState.ALIVE, blinky.getState());
        assertTrue(blinky.isInsideGate());
        assertEquals(0, blinky.getCounter());
        assertEquals(new Position(10,10), blinky.getPosition());
        assertEquals(Arena.GHOST_NORMAL_SPEED, blinky.getSpeed());

        assertEquals(new Position(20,20), pacman.getPosition());
        assertEquals(Arena.PACMAN_NORMAL_SPEED, pacman.getSpeed());
        assertFalse(pacman.isDying());
    }

    @Test
    void toGameOverMenu() throws IOException, URISyntaxException {
        Pacman pacman = new Pacman(new Position(0,0));
        pacman.setRespawnPosition(new Position(20,20));
        pacman.setLife(0);
        when(arena.getPacmans()).thenReturn(List.of(pacman));

        for (int i = 0; i < 109; ++i) controller.step(game, List.of(), 0);

        //one frame before game over
        verify(game, times(0)).setState(any());

        controller.step(game, List.of(), 0);

        //game over
        verify(game, times(1)).setState(any(AlertMenuState.class));
        verify(audioManager, times(1)).stopAllAudios();
    }
}
