package pt.up.fe.ldts.pacman.States;

import pt.up.fe.ldts.pacman.Game;
import pt.up.fe.ldts.pacman.controller.Controller;
import pt.up.fe.ldts.pacman.gui.GUI;
import pt.up.fe.ldts.pacman.viewer.Viewer;

import java.io.IOException;
import java.net.URISyntaxException;

public abstract class State<T> {
    private final T model;
    private final Viewer<T> viewer;
    private final Controller<T> controller;

    public State(T model) throws IOException, URISyntaxException {
        this.model = model;
        this.viewer = createViewer();
        this.controller = createController();
    }

    public T getModel() {
        return model;
    }

    public abstract Viewer<T> createViewer() throws IOException, URISyntaxException;

    public abstract Controller<T> createController();

    public void step(Game game, GUI gui, long frameTime) throws IOException, URISyntaxException {
        GUI.ACTION action = gui.getNextAction();
        controller.step(game, action, frameTime);
        viewer.drawElement(gui, model);
    }
}
