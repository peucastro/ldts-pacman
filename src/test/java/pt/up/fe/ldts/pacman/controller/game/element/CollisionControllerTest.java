package pt.up.fe.ldts.pacman.controller.game.element;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.up.fe.ldts.pacman.Game;
import pt.up.fe.ldts.pacman.MockAudio;
import pt.up.fe.ldts.pacman.audio.AudioManager;
import pt.up.fe.ldts.pacman.audio.AudioPlayer;
import pt.up.fe.ldts.pacman.model.Position;
import pt.up.fe.ldts.pacman.model.game.Arena;
import pt.up.fe.ldts.pacman.model.game.element.collectibles.Cherry;
import pt.up.fe.ldts.pacman.model.game.element.collectibles.Collectible;
import pt.up.fe.ldts.pacman.model.game.element.collectibles.Orange;
import pt.up.fe.ldts.pacman.model.game.element.collectibles.PowerUp;
import pt.up.fe.ldts.pacman.model.game.element.ghost.Ghost;
import pt.up.fe.ldts.pacman.model.game.element.ghost.GhostState;
import pt.up.fe.ldts.pacman.model.game.element.ghost.Pinky;
import pt.up.fe.ldts.pacman.model.game.element.pacman.Pacman;
import pt.up.fe.ldts.pacman.states.game.DyingState;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CollisionControllerTest {
    private Ghost ghost;
    private Pacman pacman;
    private PowerUp powerUp;
    private Arena arena;
    private AudioPlayer mockCollectibleEaten;
    private AudioPlayer mockGhostEaten;
    private AudioPlayer mockGhostAliveSiren;
    private AudioPlayer mockGhostScaredSiren;
    private AudioManager audioManager;
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

        audioManager = mock(AudioManager.class);
        mockCollectibleEaten = mock(AudioPlayer.class);
        mockGhostEaten = mock(AudioPlayer.class);
        mockGhostAliveSiren = mock(AudioPlayer.class);
        mockGhostScaredSiren = mock(AudioPlayer.class);
        when(audioManager.getAudio("collectibleEaten")).thenReturn(mockCollectibleEaten);
        when(audioManager.getAudio("ghostEaten")).thenReturn(mockGhostEaten);
        when(audioManager.getAudio("ghostsAliveSiren")).thenReturn(mockGhostAliveSiren);
        when(audioManager.getAudio("ghostsScaredSiren")).thenReturn(mockGhostScaredSiren);

        collisionController = new CollisionController(arena, audioManager);
    }

    @Test
    void testPacmanGhostCollisionAlive() throws IOException, URISyntaxException {
        when(ghost.collidingWith(pacman)).thenReturn(true);

        when(ghost.getState()).thenReturn(GhostState.ALIVE);

        collisionController.step(game, null, 0);
        verify(pacman).decreaseLife();
        verify(pacman).setDying(true);
        verify(pacman).setSpeed(Arena.PACMAN_NORMAL_SPEED);
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
        verify(arena, times(1)).incrementScore(200);
    }

    @Test
    void testPacmanCollectibleCollisionPowerUp() throws IOException, URISyntaxException {
        when(pacman.getPosition()).thenReturn(new Position(5, 5));
        when(powerUp.getPosition()).thenReturn(new Position(5, 5));
        when(powerUp.getValue()).thenReturn(5);

        collisionController.step(game, null, 0);

        verify(ghost).setState(GhostState.SCARED);
        verify(ghost).setSpeed(Arena.GHOST_SCARED_SPEED);
        verify(ghost).invertDirection();
        verify(pacman).setSpeed(Arena.PACMAN_BOOSTED_SPEED);
        verify(arena).incrementScore(5);
        verify(arena).addBlankPosition(new Position(5, 5));
        verify(arena).incrementCollectedCollectibles();
        verify(mockCollectibleEaten).playOnce();
        verify(mockGhostAliveSiren).stopPlaying();
        verify(mockGhostScaredSiren).playInLoop();
    }

    @Test
    void testCollectibleRemoving() throws IOException, URISyntaxException {
        // Configure the mock to mimic real behavior
        Set<Collectible> initialCollectibles = new HashSet<>(Set.of(new Cherry(new Position(5, 5)), new Orange(new Position(10, 10))));
        when(arena.getCollectibles()).thenReturn(initialCollectibles);

        collisionController = new CollisionController(arena, audioManager);
        collisionController.step(game, List.of(), 0);

        // Verify that the method was called during the process
        verify(arena, times(1)).getCollectibles();

        // Assert based on the stubbed data
        assertEquals(1, initialCollectibles.size());
    }

    @Test
    void testGhostStateResetAfterScaredTime() throws IOException, URISyntaxException, NoSuchFieldException, IllegalAccessException {
        when(pacman.getPosition()).thenReturn(new Position(30, 30));
        when(ghost.getState()).thenReturn(GhostState.SCARED);
        when(ghost.isScared()).thenReturn(true);

        Field privateField = CollisionController.class.getDeclaredField("scaredTimeLeft");
        privateField.setAccessible(true);
        privateField.set(collisionController, 2);

        collisionController.step(game, null, 0);

        verify(ghost, times(0)).setState(GhostState.ALIVE);
        verify(ghost, times(0)).setSpeed(Arena.GHOST_NORMAL_SPEED);
        verify(pacman, times(0)).setSpeed(Arena.PACMAN_NORMAL_SPEED);
        verify(mockGhostScaredSiren, times(0)).stopPlaying();
        verify(mockGhostAliveSiren, times(0)).playInLoop();

        collisionController.step(game, null, 0);

        verify(ghost).setState(GhostState.ALIVE);
        verify(ghost).setSpeed(Arena.GHOST_NORMAL_SPEED);
        verify(pacman).setSpeed(Arena.PACMAN_NORMAL_SPEED);
        verify(mockGhostScaredSiren).stopPlaying();
        verify(mockGhostAliveSiren).playInLoop();
    }

    @Test
    void testDyingState() throws IOException, URISyntaxException {
        when(arena.getPacmans()).thenReturn(List.of(new Pacman(new Position(0, 0))));
        when(arena.getGhosts()).thenReturn(Set.of(new Pinky(new Position(0, 0))));
        AudioManager audioManager = MockAudio.getMockAudioManager();
        when(game.getAudioManager()).thenReturn(audioManager);

        collisionController.step(game, List.of(), 0);

        verify(game, times(1)).setState(any(DyingState.class));
        verify(audioManager, times(1)).stopAllAudios();
    }

    @Test
    void multiplayerPacmanRespawns() throws IOException, URISyntaxException {
        Pacman pacman1 = new Pacman(new Position(0, 0));
        pacman1.setCounter(1);
        pacman1.setRespawnPosition(new Position(100, 100));
        Pacman pacman2 = new Pacman(new Position(100, 100));
        when(arena.getPacmans()).thenReturn(List.of(pacman1, pacman2));
        when(arena.getGhosts()).thenReturn(Set.of(new Pinky(new Position(0, 0))));
        AudioManager audioManager = MockAudio.getMockAudioManager();
        when(game.getAudioManager()).thenReturn(audioManager);

        //simulate the wait while the pacman is dead
        for (int i = 0; i < 110; ++i) collisionController.step(game, List.of(), 0);

        assertTrue(pacman1.isDying());
        assertFalse(pacman2.isDying());

        //frame when pacman1 respawns
        collisionController.step(game, List.of(), 0);

        assertFalse(pacman1.isDying());
        assertFalse(pacman2.isDying());
        assertEquals(0, pacman1.getCounter());
    }

    @Test
    void multiplayerPacmanStaysDead() throws IOException, URISyntaxException {
        Pacman pacman1 = new Pacman(new Position(0, 0));
        pacman1.setLife(1);
        pacman1.setRespawnPosition(new Position(100, 100));
        Pacman pacman2 = new Pacman(new Position(100, 100));
        when(arena.getPacmans()).thenReturn(List.of(pacman1, pacman2));
        when(arena.getGhosts()).thenReturn(Set.of(new Pinky(new Position(0, 0))));
        AudioManager audioManager = MockAudio.getMockAudioManager();
        when(game.getAudioManager()).thenReturn(audioManager);

        //simulate the wait while the pacman is dead
        for (int i = 0; i < 110; ++i) collisionController.step(game, List.of(), 0);

        assertTrue(pacman1.isDying());
        assertFalse(pacman2.isDying());

        //frame when the timer for pacman1 to respawn ends, however it has 0 lives
        collisionController.step(game, List.of(), 0);

        assertTrue(pacman1.isDying());
        assertFalse(pacman2.isDying());
    }

    @Test
    void testAudioLoading() {
        verify(audioManager, times(1)).addAudio("ghostEaten", "Audio/ghostEaten.wav");
        verify(audioManager, times(1)).addAudio("collectibleEaten", "Audio/collectibleEaten.wav");
        verify(audioManager, times(1)).addAudio("ghostsAliveSiren", "Audio/ghostsAlive.wav");
        verify(audioManager, times(1)).addAudio("ghostsScaredSiren", "Audio/ghostsScared.wav");

        verify(audioManager, times(1)).getAudio("ghostEaten");
        verify(audioManager, times(1)).getAudio("collectibleEaten");
        verify(audioManager, times(1)).getAudio("ghostsAliveSiren");
        verify(audioManager, times(1)).getAudio("ghostsScaredSiren");

        verify(mockGhostEaten, times(1)).setVolume(0.40f);
        verify(mockCollectibleEaten, times(1)).setVolume(0.25f);
        verify(mockGhostAliveSiren, times(1)).setVolume(0.2f);
        verify(mockGhostScaredSiren, times(1)).setVolume(0.25f);
    }

    @Test
    void testAudioPlayingStartingTheGame() throws IOException, URISyntaxException {
        when(arena.getCollectibles()).thenReturn(new HashSet<>());
        when(mockGhostAliveSiren.isPlaying()).thenReturn(false);
        when(mockGhostScaredSiren.isPlaying()).thenReturn(false);

        collisionController.step(game, List.of(), 0);

        verify(mockGhostAliveSiren, times(1)).playInLoop();
        verify(mockGhostScaredSiren, times(0)).playInLoop();
    }

    @Test
    void testAudioPlayingResumingScaredState() throws IOException, URISyntaxException, NoSuchFieldException, IllegalAccessException {
        when(arena.getCollectibles()).thenReturn(new HashSet<>());
        when(mockGhostAliveSiren.isPlaying()).thenReturn(false);
        when(mockGhostScaredSiren.isPlaying()).thenReturn(false);

        Field privateField = CollisionController.class.getDeclaredField("scaredTimeLeft");
        privateField.setAccessible(true);
        privateField.set(collisionController, 100);

        collisionController.step(game, List.of(), 0);

        verify(mockGhostAliveSiren, times(0)).playInLoop();
        verify(mockGhostScaredSiren, times(1)).playInLoop();
    }
}
