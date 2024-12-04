package pt.up.fe.ldts.pacman.controller;

import pt.up.fe.ldts.pacman.TempDrawFrame;
import pt.up.fe.ldts.pacman.gui.GUI;

import java.io.IOException;

public abstract class Controller<T> {
    private final T model;

    public Controller(T model) {
        this.model = model;
    }

    public T getModel() {
        return model;
    }

    public abstract void step(TempDrawFrame game, GUI.ACTION action, long time) throws IOException;
}
