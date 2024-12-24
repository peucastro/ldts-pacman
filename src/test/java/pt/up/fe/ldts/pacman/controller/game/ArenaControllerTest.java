package pt.up.fe.ldts.pacman.controller.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.up.fe.ldts.pacman.Game;
import pt.up.fe.ldts.pacman.audio.AudioManager;
import pt.up.fe.ldts.pacman.audio.AudioPlayer;
import pt.up.fe.ldts.pacman.gui.GUI;
import pt.up.fe.ldts.pacman.model.Position;
import pt.up.fe.ldts.pacman.model.game.Arena;
import pt.up.fe.ldts.pacman.model.game.element.Direction;
import pt.up.fe.ldts.pacman.model.game.element.collectibles.Cherry;
import pt.up.fe.ldts.pacman.model.game.element.collectibles.Collectible;
import pt.up.fe.ldts.pacman.model.game.element.ghost.Blinky;
import pt.up.fe.ldts.pacman.model.game.element.ghost.Ghost;
import pt.up.fe.ldts.pacman.model.game.element.pacman.Pacman;
import pt.up.fe.ldts.pacman.states.menu.AlertMenuState;
import pt.up.fe.ldts.pacman.states.menu.PauseMenuState;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class ArenaControllerTest {
    private ArenaController arenaController;
    private Arena arena;
    private AudioManager audioManager;
    private Game game;

    @BeforeEach
    void setUp() {
        arena = mock(Arena.class);
        audioManager = mock(AudioManager.class);
        game = mock(Game.class);
        AudioPlayer audio = mock(AudioPlayer.class);

        when(game.getAudioManager()).thenReturn(audioManager);
        when(audioManager.getAudio(anyString())).thenReturn(audio);

        arenaController = new ArenaController(arena, audioManager);
    }

    @Test
    void testStep_ShouldPauseWhenQuitActionIsTriggered() throws IOException, URISyntaxException {
        List<GUI.ACTION> actions = List.of(GUI.ACTION.QUIT);

        arenaController.step(game, actions, 0);

        verify(audioManager).stopAllAudios();
        verify(game).setState(any(PauseMenuState.class));
    }

    @Test
    void testStep_ShouldTriggerAlertMenuWhenAllCollectiblesAreGone() throws IOException, URISyntaxException {
        when(arena.getCollectibles()).thenReturn(Set.of());

        List<GUI.ACTION> actions = List.of(GUI.ACTION.NONE);

        arenaController.step(game, actions, 0);

        verify(audioManager).stopAllAudios();
        verify(game).setState(any(AlertMenuState.class));
    }

    @Test
    void testStep_ShouldNotChangeStateIfCollectiblesExist() throws IOException, URISyntaxException {
        when(arena.getCollectibles()).thenReturn(Set.of(mock(Collectible.class)));

        List<GUI.ACTION> actions = List.of(GUI.ACTION.NONE);

        arenaController.step(game, actions, 0);

        verify(audioManager, never()).stopAllAudios();
        verify(game, never()).setState(any(AlertMenuState.class));
    }

    @Test
    void testIfControllersAreCalled() throws IOException, URISyntaxException {
        Ghost ghost = new Blinky(new Position(1, 0));
        ghost.setCounter(9);
        ghost.setDirection(Direction.LEFT); //ghost is colliding with pacman
        ghost.setSpeed(1); //ghost moves every frame, helps with testing
        Pacman pacman = new Pacman(new Position(0, 0));
        pacman.setDirection(Direction.DOWN);
        when(arena.getGhosts()).thenReturn(Set.of(ghost));
        when(arena.getPacmans()).thenReturn(List.of(pacman));
        //the collectibles set cannot be empty or the game ends
        when(arena.getCollectibles()).thenReturn(Set.of(new Cherry(new Position(10, 10))));

        arenaController.step(game, List.of(GUI.ACTION.UP), 0);

        assertEquals(Direction.UP, pacman.getDirection()); //pacman controller was called
        assertTrue(pacman.isDying()); //collision controller was called
        assertEquals(10, ghost.getCounter()); //ghost controller was called
    }

}
