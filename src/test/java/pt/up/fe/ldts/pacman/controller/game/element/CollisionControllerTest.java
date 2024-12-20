package pt.up.fe.ldts.pacman.controller.game.element;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.up.fe.ldts.pacman.Game;
import pt.up.fe.ldts.pacman.audio.AudioManager;
import pt.up.fe.ldts.pacman.audio.AudioPlayer;
import pt.up.fe.ldts.pacman.model.Position;
import pt.up.fe.ldts.pacman.model.game.Arena;
import pt.up.fe.ldts.pacman.model.game.element.collectibles.Collectible;
import pt.up.fe.ldts.pacman.model.game.element.collectibles.PowerUp;
import pt.up.fe.ldts.pacman.model.game.element.ghost.Ghost;
import pt.up.fe.ldts.pacman.model.game.element.ghost.GhostState;
import pt.up.fe.ldts.pacman.model.game.element.pacman.Pacman;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.*;

public class CollisionControllerTest {
    private Ghost ghost;
    private Pacman pacman;
    private PowerUp powerUp;
    private Arena arena;
    private AudioPlayer mockAudioPlayer;
    private Game game;
    private CollisionController collisionController;

    @BeforeEach
    void setUp() {
        pacman = mock(Pacman.class);
        powerUp = mock(PowerUp.class);
        ghost = mock(Ghost.class);

        when(pacman.isDying()).thenReturn(false);
        when(pacman.getPosition()).thenReturn(new Position(5, 5));
        when(powerUp.getPosition()).thenReturn(new Position(5, 5));

        arena = mock(Arena.class);
        when(arena.getPacmans()).thenReturn(List.of(pacman));
        Set<Ghost> ghosts = new HashSet<>();
        ghosts.add(ghost);
        when(arena.getGhosts()).thenReturn(ghosts);
        Set<Collectible> collectibles = new HashSet<>();
        collectibles.add(powerUp);
        when(arena.getCollectibles()).thenReturn(collectibles);

        game = mock(Game.class);

        AudioManager audioManager = mock(AudioManager.class);
        mockAudioPlayer = mock(AudioPlayer.class);
        when(audioManager.getAudio("collectibleEaten")).thenReturn(mockAudioPlayer);
        when(audioManager.getAudio("ghostEaten")).thenReturn(mock(AudioPlayer.class));
        when(audioManager.getAudio("ghostsAliveSiren")).thenReturn(mock(AudioPlayer.class));
        when(audioManager.getAudio("ghostsScaredSiren")).thenReturn(mock(AudioPlayer.class));

        collisionController = new CollisionController(arena, audioManager);
    }

    @Test
    void testPacmanGhostCollisionAlive() throws IOException, URISyntaxException {
        when(ghost.collidingWith(pacman)).thenReturn(true);

        when(ghost.getState()).thenReturn(GhostState.ALIVE);

        collisionController.step(game, null, 0);
        verify(pacman).decreaseLife();
        verify(pacman).setDying(true);
    }

    @Test
    void testPacmanGhostCollisionScared() throws IOException, URISyntaxException {
        when(pacman.isDying()).thenReturn(false);
        when(powerUp.getPosition()).thenReturn(new Position(6, 5));
        when(ghost.collidingWith(pacman)).thenReturn(true);
        when(ghost.getState()).thenReturn(GhostState.SCARED);

        collisionController.step(game, null, 0);

        verify(ghost).setState(GhostState.DEAD);
        verify(ghost).setSpeed(Arena.GHOST_DEAD_SPEED);
        verify(arena).incrementScore(anyLong());
    }

    @Test
    void testPacmanCollectibleCollisionPowerUp() throws IOException, URISyntaxException {
        when(pacman.getPosition()).thenReturn(new Position(5, 5));
        when(powerUp.getPosition()).thenReturn(new Position(5, 5));

        collisionController.step(game, null, 0);

        verify(ghost).setState(GhostState.SCARED);
        verify(arena).incrementScore(powerUp.getValue());
        verify(mockAudioPlayer).playOnce();
    }

    @Test
    void testGhostStateResetAfterScaredTime() throws IOException, URISyntaxException {
        when(ghost.getState()).thenReturn(GhostState.SCARED);
        when(ghost.isScared()).thenReturn(true);

        collisionController.scaredTimeLeft = 1;
        collisionController.step(game, null, 0);

        verify(ghost).setState(GhostState.ALIVE);
        verify(ghost).setSpeed(Arena.GHOST_NORMAL_SPEED);
        verify(pacman).setSpeed(Arena.PACMAN_NORMAL_SPEED);
    }
}
