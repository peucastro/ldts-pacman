package pt.up.fe.ldts.pacman;

import pt.up.fe.ldts.pacman.audio.AudioManager;
import pt.up.fe.ldts.pacman.audio.AudioPlayer;
import pt.up.fe.ldts.pacman.gui.GUI;
import pt.up.fe.ldts.pacman.gui.LanternaGUI;
import pt.up.fe.ldts.pacman.model.menu.MainMenu;
import pt.up.fe.ldts.pacman.states.State;
import pt.up.fe.ldts.pacman.states.menu.MainMenuState;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Game {
    private static final int SCREEN_WIDTH = 320;
    private static final int SCREEN_HEIGHT = 180;
    private static Game instance;
    private final GUI gui;
    private final AudioManager audioManager;
    private State state;
    private GUI.SCREEN_RESOLUTION resolution;


    private Game() throws IOException, URISyntaxException, FontFormatException {
        this.resolution = GUI.SCREEN_RESOLUTION._900p;
        this.audioManager = AudioManager.getInstance();
        this.audioManager.setMasterVolume(0.5f);
        this.gui = new LanternaGUI(SCREEN_WIDTH, SCREEN_HEIGHT, resolution);
        this.state = createInitialState();
    }

    public static Game getInstance() throws IOException, URISyntaxException, FontFormatException {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    public static void main(String[] args) throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        Game game = Game.getInstance();
        game.start();
    }

    private State createInitialState() throws IOException, URISyntaxException {
        return new MainMenuState(
                new MainMenu(resolution, audioManager.getMasterVolume()),
                audioManager
        );
    }

    public GUI getGui() {
        return gui;
    }

    public AudioManager getAudioManager() {
        return audioManager;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public GUI.SCREEN_RESOLUTION getResolution() {
        return resolution;
    }

    public void setResolution(GUI.SCREEN_RESOLUTION newResolution) throws URISyntaxException, IOException, FontFormatException {
        this.resolution = newResolution;
        gui.resizeScreen(SCREEN_WIDTH, SCREEN_HEIGHT, newResolution);
    }

    private void start() throws IOException, InterruptedException {
        GameLoop gameLoop = new GameLoop(60);
        AudioPlayer mainMusic = initializeMusic();

        try {
            while (this.state != null) {
                gameLoop.update(() -> {
                    try {
                        state.step(this, gui, gameLoop.getFrameCount());
                    } catch (IOException | URISyntaxException | FontFormatException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        } finally {
            cleanup(mainMusic);
        }
    }

    private AudioPlayer initializeMusic() {
        AudioPlayer mainMusic = new AudioPlayer("Audio/music.wav");
        mainMusic.setVolume(0.05f);
        mainMusic.playInLoop();
        audioManager.setMainMusic(mainMusic);
        return mainMusic;
    }

    private void cleanup(AudioPlayer mainMusic) throws IOException {
        mainMusic.stopPlaying();
        gui.close();
    }
}

class GameLoop {
    private final long frameTime;
    private long frameCount;

    public GameLoop(int fps) {
        this.frameTime = 1000 / fps;
        this.frameCount = 0;
    }

    public void update(Runnable updateLogic) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        updateLogic.run();

        long elapsedTime = System.currentTimeMillis() - startTime;
        long sleepTime = frameTime - elapsedTime;

        if (sleepTime > 0) Thread.sleep(sleepTime);
        ++frameCount;
    }

    public long getFrameCount() {
        return frameCount;
    }
}
