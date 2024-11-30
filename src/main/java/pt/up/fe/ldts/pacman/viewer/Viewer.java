package pt.up.fe.ldts.pacman.viewer;

import pt.up.fe.ldts.pacman.model.game.element.Element;


public abstract class Viewer {
    protected final Renderer renderer;

    public Viewer(Renderer renderer) {
        this.renderer = renderer;
    }

    public abstract void drawElement(Element element);
}

