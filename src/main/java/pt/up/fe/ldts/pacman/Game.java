package pt.up.fe.ldts.pacman;

import pt.up.fe.ldts.pacman.States.GameState;
import pt.up.fe.ldts.pacman.States.MainMenuState;
import pt.up.fe.ldts.pacman.States.State;
import pt.up.fe.ldts.pacman.gui.GUI;
import pt.up.fe.ldts.pacman.gui.LanternaGUI;
import pt.up.fe.ldts.pacman.model.menu.MainMenu;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;


public class Game {
    private final GUI gui;
    private State state;

    public Game() throws IOException, URISyntaxException, FontFormatException {
        this.gui = new LanternaGUI(220, 220);
        this.state = new MainMenuState(new MainMenu());
    }

    public static void main(String[] args) throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        new Game().start();
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    private void start() throws IOException, InterruptedException, URISyntaxException {
        int FPS = 1000; //framerate máxima possível
        long frameTime = 1000 / FPS;
        long frameCount = 0;

        while (this.state != null) {
            long startTime = System.currentTimeMillis();

            state.step(this, gui, frameCount);

            long elapsedTime = System.currentTimeMillis() - startTime;
            long sleepTime = frameTime - elapsedTime;


            if (sleepTime > 0) Thread.sleep(sleepTime);

            ++frameCount;
        }

        gui.close();
    }
}
