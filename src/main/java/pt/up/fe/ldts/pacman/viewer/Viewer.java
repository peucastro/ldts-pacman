package pt.up.fe.ldts.pacman.viewer;

import pt.up.fe.ldts.pacman.gui.GUI;

public abstract class Viewer<T> {
    public abstract void drawElement(GUI gui, T model);
}

