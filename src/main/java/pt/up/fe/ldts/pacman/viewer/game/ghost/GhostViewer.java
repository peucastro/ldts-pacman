package pt.up.fe.ldts.pacman.viewer.game.ghost;

import pt.up.fe.ldts.pacman.model.game.element.ghost.Ghost;
import pt.up.fe.ldts.pacman.viewer.Renderer;
import pt.up.fe.ldts.pacman.viewer.game.MultipleElementViewer;

import java.awt.image.BufferedImage;
import java.util.Map;


import pt.up.fe.ldts.pacman.model.game.element.ghost.GhostState;

public class GhostViewer extends MultipleElementViewer<Ghost, GhostState> {

    public GhostViewer(Renderer renderer, Map<GhostState, BufferedImage> images) {
        super(renderer, images);
    }

    @Override
    protected BufferedImage getCurrentImage(Ghost ghost) {
        return images.get(ghost.getState());
    }
}
