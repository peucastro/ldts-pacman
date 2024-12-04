package pt.up.fe.ldts.pacman.viewer;

public abstract class Viewer<T> {
    protected final Renderer renderer;

    public Viewer(Renderer renderer) {
        this.renderer = renderer;
    }

    public abstract void drawElement(T model);
}

